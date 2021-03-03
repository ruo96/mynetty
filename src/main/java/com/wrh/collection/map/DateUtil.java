package com.wrh.collection.map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrBuilder;
import org.joda.time.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;


/**
 * 日期工具类
 */
public class DateUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String HOUR_MINUTES_FORMAT = "HH:mm";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd";
    public static final String SIMPLE_DATE_FORMAT2 = "yyyy/MM/dd";
    public static final String SIMPLE_DATE_FORMAT_ZH = "yyyy年MM月dd日";
    public static final String SIMPLE_DATE_WEEK_FORMAT_ZH = "yyyy年MM月dd日 EEEE";

    /** 查询类别  按年  按月*/
    public static final String DIMENSION_YEAR = "year";
    public static final String DIMENSION_MONTH = "month";
    public static final String DIMENSION_DAY = "day";
    public static final String DIMENSION_WEEK = "week";

    private DateUtil() {}

    public static Date parse(String dateString, String datePattern) {
        if (StringUtils.isBlank(datePattern)) {
            datePattern = DateUtil.DATE_FORMAT;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
        try {
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public static <T extends Date> T parse(String dateString, String datePattern, Class<T> targetResultType) {
        if (StringUtils.isEmpty(dateString)) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(datePattern, Locale.CHINA);
        try {
            T t = (T) targetResultType.getConstructor(new Class[]{Long.TYPE})
                    .newInstance(
                            new Object[]{Long.valueOf(df.parse(dateString)
                                    .getTime())});
            return t;
        } catch (ParseException e) {
            String errorInfo = "cannot use dateformat:" + datePattern
                    + " parse datestring:" + dateString;
            throw new IllegalArgumentException(errorInfo, e);
        } catch (Exception e) {
            throw new IllegalArgumentException("error targetResultType:"
                    + targetResultType.getName(), e);
        }
    }

    public static String format(Date date, String datePattern) {
        if (date == null) {
            return null;
        }
        if (StringUtils.isBlank(datePattern)) {
            datePattern = DateUtil.DATE_FORMAT;
        }

        return new SimpleDateFormat(datePattern, Locale.CHINA).format(date);
    }

    public static String format(String datePattern) {
        return DateUtil.format(new Date(), datePattern);
    }

    public static String getDefaultDateString() {
        return DateUtil.format(new Date(), DateUtil.DATE_FORMAT);
    }

    public static String getDefaultDateTimeString() {
        return DateUtil.format(new Date(), DateUtil.DATE_TIME_FORMAT);
    }

    public static String getYesterdayString(String datePattern) {
        return DateUtil.getBeforeNDaysDateString(1, datePattern);
    }

    public static String getBeforeNDaysDateString(int n, String datePattern) {
        return DateUtil.getBeforeNDaysDateString(new Date(), n, datePattern);
    }

    public static String getBeforeNDaysDateString(Date date, int n, String datePattern) {
        if (StringUtils.isBlank(datePattern)) {
            datePattern = DateUtil.DATE_FORMAT;
        }
        DateTime today = new DateTime(date);
        DateTime beforeDay = today.minusDays(n);
        return beforeDay.toString(datePattern);
    }

    public static String getAfterNDaysDateString(int n, String datePattern) {
        if (StringUtils.isBlank(datePattern)) {
            datePattern = DateUtil.DATE_FORMAT;
        }
        DateTime today = new DateTime();
        DateTime afterDay = today.plusDays(n);
        return afterDay.toString(datePattern);
    }

    public static Date add(int field, Date date, int value) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int fieldNewValue = (c.get(field) + value);
        c.set(field, fieldNewValue);
        return c.getTime();
    }

    public static Period dateDiff(long time1, long time2) {
        DateTime dateTime1 = new DateTime(time1);
        DateTime dateTime2 = new DateTime(time2);
        return new Interval(dateTime1, dateTime2).toPeriod(PeriodType.days());
    }

    public static int dateDiff(Date time1, Date time2) {
        DateTime dateTime1 = new DateTime(time1);
        DateTime dateTime2 = new DateTime(time2);
        return new Interval(dateTime1, dateTime2).toPeriod(PeriodType.days()).getDays();
    }

    public static int weekDiff(Date time1, Date time2) {
        DateTime dateTime1 = new DateTime(time1);
        DateTime dateTime2 = new DateTime(time2);
        return new Interval(dateTime1, dateTime2).toPeriod(PeriodType.weeks()).getWeeks();
    }

    public static int monthDiff(Date time1, Date time2) {
        DateTime dateTime1 = new DateTime(time1);
        DateTime dateTime2 = new DateTime(time2);
        return new Interval(dateTime1, dateTime2).toPeriod(PeriodType.months()).getMonths();
    }

    public static String getFirstDayOfMonth(String datePattern) {
        return DateUtil.getFirstDayOfMonth(new Date(), datePattern);
    }

    public static String getLastDayOfMonth(String datePattern) {
        return DateUtil.getLastDayOfMonth(new Date(), datePattern);
    }

    public static String getFirstDayOfMonth(Date date, String datePattern) {
        return new DateTime(date).dayOfMonth().withMinimumValue().toString(datePattern);
    }

    public static String getLastDayOfMonth(Date date, String datePattern) {
        return new DateTime(date).dayOfMonth().withMaximumValue().toString(datePattern);
    }

    public static String getFirstDayOfLastMonth(Date date, String datePattern) {
        return new DateTime(date).withFieldAdded(DurationFieldType.months(), -1).dayOfMonth().withMinimumValue().toString(datePattern);
    }

    public static String getLastDayOfLastMonth(Date date, String datePattern) {
        return new DateTime(date).withFieldAdded(DurationFieldType.months(), -1).dayOfMonth().withMaximumValue().toString(datePattern);
    }

    public static String getFirstDayOfWeek(String datePattern) {
        return DateUtil.getFirstDayOfWeek(new Date(), datePattern);
    }

    public static String getLastDayOfWeek(String datePattern) {
        return DateUtil.getLastDayOfWeek(new Date(), datePattern);
    }

    public static String getFirstDayOfWeek(Date date, String datePattern) {
        return DateUtil.format(DateUtil.getDate(date, Calendar.DAY_OF_WEEK, Calendar.MONDAY), datePattern);
    }

    public static String getLastDayOfWeek(Date date, String datePattern) {
        return DateUtil.format(DateUtil.getDate(date, Calendar.DAY_OF_WEEK, Calendar.SUNDAY), datePattern);
    }

    public static String getFirstDayOfLastWeek(Date date, String datePattern) {
        DateTime dateTime = new DateTime(date);
        return DateUtil.format(DateUtil.getDate(dateTime.minusDays(7).toDate(), Calendar.DAY_OF_WEEK, Calendar.MONDAY), datePattern);
    }

    public static String getLastDayOfLastWeek(Date date, String datePattern) {
        DateTime dateTime = new DateTime(date);
        return DateUtil.format(DateUtil.getDate(dateTime.minusDays(7).toDate(), Calendar.DAY_OF_WEEK, Calendar.SUNDAY), datePattern);
    }

    public static boolean isFirstDayOfWeek(Date date) {
        return new DateTime(date).dayOfWeek().get() == 1;
    }

    public static boolean isFirstDayOfMonth(Date date) {
        return new DateTime(date).dayOfMonth().get() == 1;
    }

    private static final Date getDate(int field, int value) {
        Calendar calendar = Calendar.getInstance();
        Locale.setDefault(Locale.US);
        //此处设置周一为一个星期的开始
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(new Date());
        calendar.set(field, value);
        return calendar.getTime();
    }

    private static final Date getDate(Date date, int field, int value) {
        Calendar calendar = Calendar.getInstance();
        Locale.setDefault(Locale.US);
        //此处设置周一为一个星期的开始
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        if (date == null) {
            date = new Date();
        }
        calendar.setTime(date);
        calendar.set(field, value);
        return calendar.getTime();
    }

    public static List<String> getDayIntervalListDesc(String startTimeStr, String endTimeStr, String datePattern, String dateType) {
        if (startTimeStr == null || endTimeStr == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(datePattern);

        try {
            Date startTime = df.parse(startTimeStr);
            Date endTime = df.parse(endTimeStr);
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(startTime);
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(endTime);

            int dateField = Calendar.DAY_OF_YEAR;
            if ("week".equals(dateType)) {
                dateField = Calendar.WEEK_OF_YEAR;
                endCalendar.setTime(DateUtil.getDate(endCalendar.getTime(), Calendar.DAY_OF_WEEK, Calendar.MONDAY));
            } else if ("month".equals(dateType)) {
                dateField = Calendar.MONTH;
                endCalendar.setTime(DateUtil.parse(DateUtil.getFirstDayOfMonth(endTime, DateUtil.DATE_FORMAT), DateUtil.DATE_FORMAT));
            }

            List<String> results = new LinkedList<String>();
            //确保起始日期被添加到结果中 （即使的startTime在endTime之后）
            results.add(df.format(endCalendar.getTime()));
            endCalendar.add(dateField, -1);

            while (startCalendar.getTimeInMillis() <= endCalendar.getTimeInMillis()) {
                results.add(df.format(endCalendar.getTime()));
                endCalendar.add(dateField, -1);
            }
            return results;
        } catch (ParseException e) {
            return null;
        }
    }

    public static List<String> getDayIntervalList(String startTimeStr, String endTimeStr, String datePattern, String dateType) {
        if (startTimeStr == null || endTimeStr == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(datePattern);

        try {
            Date startTime = df.parse(startTimeStr);
            Date endTime = df.parse(endTimeStr);
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(startTime);
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(endTime);

            int dateField = Calendar.DAY_OF_YEAR;
            if ("week".equals(dateType)) {
                dateField = Calendar.WEEK_OF_YEAR;
            } else if ("month".equals(dateType)) {
                dateField = Calendar.MONTH;
            }

            List<String> results = new LinkedList<String>();
            //确保起始日期被添加到结果中 （即使的startTime在endTime之后）
            results.add(df.format(startCalendar.getTime()));
            startCalendar.add(dateField, 1);

            while (startCalendar.getTimeInMillis() <= endCalendar.getTimeInMillis()) {
                results.add(df.format(startCalendar.getTime()));
                startCalendar.add(dateField, 1);
            }
            return results;
        } catch (ParseException e) {
            return null;
        }
    }

    public static String timestampToKey(Long timeLong,Integer slidWindow)
    {
        //获取日期字符串
        String dataYMD=getDateYMD(timeLong);

        //或取传入是时间在当天的slot
        Long daySlot=getSlotByDatetime(timeLong,slidWindow);

        return dataYMD+"_"+daySlot;

    }


    /**
     * 获取传入时间戳的 年月日 字符串 20160624
     * @param serverTime
     * @return
     */
    public static String getDateYMD(Long serverTime) {

        SimpleDateFormat formatYMD = new SimpleDateFormat("yyyyMMdd");
        String dataYMD=formatYMD.format(serverTime);

        return dataYMD;

    }


    /**
     *
     * @param timeLong  当前需要计算槽位的时间戳
     * @param slidWindow 计算槽位的时间窗口的长度,单位:分钟
     * @return
     */
    public static Long getSlotByDatetime(Long timeLong, Integer slidWindow){

        //取得传入时间当天00:00:00时的时间戳
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(timeLong);//设置服务器接受时间
        c1.set(Calendar.HOUR_OF_DAY, 0);//调整小时
        c1.set(Calendar.MINUTE,0);//调整分钟
        c1.set(Calendar.SECOND, 0);//调整秒
        long Day0time=c1.getTime().getTime();


        long diff =timeLong-Day0time;//算出当前时间与今天凌晨时间差值,毫秒

        if(diff<0){
            diff=0;
        }

        long minute=diff/1000/60;//将时差换算为分钟

        long  timeSlot= minute/slidWindow;//计算出时差所在的槽位 ,5分钟为例,从0开始,287结尾, 分钟数为0-4在0槽位,5-9在1槽位,10-14在2槽位

        return timeSlot;
    }
    
    public static String getLastSlot(Long timeLong, Integer slidWindow) {
        Long currentSlot = DateUtil.getSlotByDatetime(timeLong, slidWindow);
        Long lastSlot = currentSlot - 1;
        if (lastSlot < 0) {
            lastSlot = 287L;
        }
        
        return Long.toString(lastSlot);
    }

    public static Date getDateTimeBySlot(Integer slidWindow, Integer slotNum) {
        //取得传入时间当天00:00:00时的时间戳
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(System.currentTimeMillis());//设置服务器接受时间
        c1.set(Calendar.HOUR_OF_DAY, 0);//调整小时
        c1.set(Calendar.MINUTE,0);//调整分钟
        c1.set(Calendar.SECOND, 0);//调整秒
        long Day0time=c1.getTime().getTime();

        long diff = slotNum * slidWindow * 60 * 1000;

        return new Date(Day0time + diff);
    }

    public static String getDateYMD2(String str) {
        String year = str.substring(0,4);
        String month = str.substring(5,7);
        String day = str.substring(8,10);
        StrBuilder result = new StrBuilder();
        
        return result.append(year).append("/").append(month).append("/").append(day).toString();

    }

    public static String getAfterNDaysDateString(Date date, int n, String datePattern) {
        if (StringUtils.isBlank(datePattern)) {
            datePattern = DateUtil.DATE_FORMAT;
        }
        DateTime today = new DateTime(date);
        DateTime afterDay = today.plusDays(n);
        return afterDay.toString(datePattern);
    }
    
    private static String getBeforeNdays(int n) {
        return LocalDate.now().minusDays(n).format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    private static String getBeforeNdaysWithDsEnd(int n,String date) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.parse(date, pattern);
        return now.minusDays(n).format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
    
    public static List<String> getDsList() {
        String ds = getBeforeNdays(0);
        String yesterday = getBeforeNdays(1);
        String before7day = getBeforeNdays(7);
        
        return Arrays.asList(ds, yesterday, before7day);
    }

    public static List<String> getDsListWithDsEnd(String date) {

        if(StringUtils.isEmpty(date)) {
            LOGGER.info(">>> getDsListWithDsEnd date: {}", date);
            return getDsList();
        }

        String ds = getBeforeNdaysWithDsEnd(0,date);
        String yesterday = getBeforeNdaysWithDsEnd(1,date);
        String before7day = getBeforeNdaysWithDsEnd(7,date);

        return Arrays.asList(ds, yesterday, before7day);
    }
    
    public static List<String> getLastWeekDsList(){
        List<String> dsList = new ArrayList<>();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        dsList.add(now.format(pattern));
        dsList.add(now.minusDays(1).format(pattern));
        dsList.add(now.minusDays(2).format(pattern));
        dsList.add(now.minusDays(3).format(pattern));
        dsList.add(now.minusDays(4).format(pattern));
        dsList.add(now.minusDays(5).format(pattern));
        dsList.add(now.minusDays(6).format(pattern));
        dsList.add(now.minusDays(7).format(pattern));
        return dsList;
    }

    public static List<String> getLastWeekDsListByDate(String date){
        if(StringUtils.isEmpty(date)) {
            LOGGER.info(">>> getLastWeekDsListByDate date: {}", date);
            return getLastWeekDsList();
        }
        List<String> dsList = new ArrayList<>();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.parse(date, pattern);
        dsList.add(now.format(pattern));
        dsList.add(now.minusDays(1).format(pattern));
        dsList.add(now.minusDays(2).format(pattern));
        dsList.add(now.minusDays(3).format(pattern));
        dsList.add(now.minusDays(4).format(pattern));
        dsList.add(now.minusDays(5).format(pattern));
        dsList.add(now.minusDays(6).format(pattern));
        dsList.add(now.minusDays(7).format(pattern));
        return dsList;
    }
    
    public static Long getSlotByAll(Integer slidWindow) {
        //取得传入时间当天00:00:00时的时间戳
        LocalDateTime currentMinTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        long minTime = currentMinTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        
        LocalDateTime currentMaxTime = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        long maxTime = currentMaxTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        
        //算出当前时间与今天凌晨时间差值,毫秒
        long diff = Math.subtractExact(maxTime, minTime);
        if (diff < 0) {
            diff = 0;
        }
        
        // 将时差换算为分钟
        long minute = Math.floorDiv(diff, 60000);
        
        // 计算出时差所在的槽位,1分钟为例, 从0开始,1439结尾,分钟数为0-1在0槽位
        long timeSlot = Math.floorDiv(minute, slidWindow);
        return Math.addExact(timeSlot, 1);
    }
    
    public static boolean isToday(String ds) {
        return StringUtils.startsWith(ds,DateUtil.format(DateUtil.DATE_FORMAT));
    }

    public static String getYesterdayByDs(String ds) {
        LocalDate date = LocalDate.parse(ds, DateTimeFormatter.ofPattern(DATE_FORMAT));
        return date.minusDays(1).toString();
    }

    /**
     * 获取实时分钟数
     * @return
     */
    public static Integer getNowMinute(){
        LocalDateTime now = LocalDateTime.now();
        return now.getHour() * 60 + now.getMinute() + 1;
    }

    /**
     * 判断是否是当天
     * @param dsEnd
     * @return
     */
    /*public static boolean isToday(String dsEnd) {
        return dsEnd.equals(LocalDate.now().toString());
    }*/

    /**
     * 类型转换， 是用于数据库查询结果转换
     * @param date
     * @return
     */
    public static LocalDateTime Date2LocalDateTime(Date date){
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * 判断是否是本日
     * @param date
     * @return
     */
    public static boolean isDateToday(Date date){
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate().equals(LocalDate.now());
    }

    /**
     * 判断是否是本年
     * @param ds
     * @return
     */
    public static boolean isThisYear(String ds){
        return LocalDate.parse(ds,DateTimeFormatter.ofPattern(DATE_FORMAT)).getYear() == LocalDate.now().getYear();
    }

    /**
     * 判断是否本年本月
     * @param ds
     * @return
     */
    public static boolean isThisYearMonth(String ds) {
        LocalDate date = LocalDate.parse(ds,DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDate now = LocalDate.now();
        return date.getYear() == now.getYear() && date.getMonthValue() == now.getMonthValue();
    }

    /**
     * 判断是否是昨天
     * @param ds
     * @return
     */
    public static boolean isYesterday(String ds) {
        return LocalDate.parse(ds,DateTimeFormatter.ofPattern(DATE_FORMAT)).equals(LocalDate.now().minusDays(1));
    }

    /**
     * 判断是否是第二天
     * @param ds
     * @param dimension
     * @return
     */
    public static boolean isSecondDayOfDimension(String ds, String dimension) {
        LocalDate date = LocalDate.parse(ds, DateTimeFormatter.ofPattern(DATE_FORMAT));
        if (dimension.equals(DIMENSION_YEAR)) {
            return date.getDayOfYear() == 2;
        }else if (dimension.equals(DIMENSION_MONTH)) {
            return date.getDayOfMonth() == 2;
        }else {
            return false;
        }
    }

    public static boolean isFirstDayOfDimension(String dsEnd, String dimension) {
        LocalDate date = LocalDate.parse(dsEnd, DateTimeFormatter.ofPattern(DATE_FORMAT));
        if (dimension.equals(DIMENSION_YEAR)) {
            return date.getDayOfYear() == 1;
        }else if (dimension.equals(DIMENSION_MONTH)) {
            return date.getDayOfMonth() == 1;
        }else {
            return date.equals(LocalDate.now());
        }
    }

    public static int getTotalDaysByYearMonth(String dsEnd) {
        LocalDate date = LocalDate.parse(dsEnd, DateTimeFormatter.ofPattern(DATE_FORMAT));
        return date.lengthOfMonth();
    }

    /**
     * 获取同样维度的第一天
     * @param dsEnd
     * @param dimension
     * @return
     */
    public static LocalDate getFirstDayOfDimension(String dsEnd, String dimension) {
        LocalDate date = LocalDate.parse(dsEnd, DateTimeFormatter.ofPattern(DATE_FORMAT));
        if(dimension.equals(DIMENSION_YEAR)){
            return LocalDate.of(date.getYear(), 1, 1);
        }else if (dimension.equals(DIMENSION_MONTH)) {
            return LocalDate.of(date.getYear(), date.getMonth(), 1);
        }else {
            return null;
        }
    }

    /**
     * 获取同样维度的最后一天
     * @param ds
     * @param dimension
     * @return
     */
    public static LocalDate getLastDayOfDimension(String ds, String dimension) {
        LocalDate date = LocalDate.parse(ds, DateTimeFormatter.ofPattern(DATE_FORMAT));
        if(dimension.equals(DIMENSION_YEAR)){
            return date.with(TemporalAdjusters.lastDayOfYear());
        }else if (dimension.equals(DIMENSION_MONTH)) {
            return date.with(TemporalAdjusters.lastDayOfMonth());
        }else {
            return null;
        }
    }

    /**
     * 查找上周同一天
     * @param ds
     * @return
     */
    public static String getSameDayOfLastWeedByDs(String ds) {
        LocalDate date = LocalDate.parse(ds, DateTimeFormatter.ofPattern(DATE_FORMAT));
        return date.minusWeeks(1).toString();
    }
    public static final String TIME_BEGIN = "00:00:00";
    /**
     * 格式化整点数据
     * @param i
     * @param ds
     * @return
     */
    public static String getSharpTime(int i, String ds) {
        LocalDateTime time = LocalDateTime.parse(ds+" "+TIME_BEGIN, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)).plusHours(i);
        if(time.getDayOfYear() != LocalDate.parse(ds,DateTimeFormatter.ofPattern(DATE_FORMAT)).getDayOfYear()){
            return ds + " 24:00:00";
        }
        return time.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }

    /**
     * 查看是否是前天
     * @param ds
     * @return
     */
    public static boolean isTheDayBeforeYesterday(String ds) {
        return LocalDate.parse(ds,DateTimeFormatter.ofPattern(DATE_FORMAT)).equals(LocalDate.now().minusDays(2));
    }

    public static boolean isIn3Days(String ds) {
        return LocalDate.parse(ds,DateTimeFormatter.ofPattern(DATE_FORMAT)).isAfter(LocalDate.now().minusDays(4));
    }

    public static boolean isIn7Days(String ds) {
        return LocalDate.parse(ds,DateTimeFormatter.ofPattern(DATE_FORMAT)).isAfter(LocalDate.now().minusDays(8));
    }

    /**
     * 获取上个月
     * @param dsEnd  yyyy-MM
     *               YearMonth yearMonth = YearMonth.parse("202008", DateTimeFormatter.ofPattern("yyyyMM"));
     *         LocalDate localDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
     * @return
     */
    public static String getLastMonthByMs(String dsEnd) {
        LocalDate date = LocalDate.parse(dsEnd + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LOGGER.info(">>> dsEnd: {} date: {}", dsEnd, date);
        return date.minusMonths(1).toString().substring(0,7);
    }

    /**
     * 获取去年同月
     * @param dsEnd  yyyy-MM
     * @return
     */
    public static String getSameMonthOfLastYearByDs(String dsEnd) {
        LocalDate date = LocalDate.parse(dsEnd + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LOGGER.info(">>> dsEnd: {} date: {}", dsEnd, date);
        return date.minusYears(1).toString().substring(0,7);
    }
}