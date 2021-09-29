package com.wrh.TimeCalculate;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类
 *
 * @author Fred.Chung 2014/10/14
 */
public final class DateTimeUtil {

    public static final String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
    public static final String yyyyMMddHHmmssSSS2 = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyyMMddHHmmss2 = "yyyyMMddHHmmss";
    public static final String yyyyMMdd = "yyyy-MM-dd";
    public static final String yyyyMMdd2 = "yyyyMMdd";
    public static final String yyyyMM = "yyyyMM";
    public static final String HHMMSSSSS = "HHmmssSSS";
    public static final String HHMMSS = "HHmmss";
    public static final String HHMM = "HH:mm";

    public static final String DIMENSION_YEAR = "year";
    public static final String DIMENSION_MONTH = "month";
    public static final String DIMENSION_SEASON = "season";
    public static final String DIMENSION_WEEK = "week";
    public static final String DIMENSION_DAY = "day";

    private DateTimeUtil() {
    }

    /**
     * 校验日期字符串是否符合指定的日期格式
     *
     * @param dateFormat 日期格式
     * @param str        格式化之后的日期字符串
     * @return
     */
    public static Boolean isValidDate(String dateFormat, String str) {
        boolean res = false;

        if (dateFormat.length() != str.length()) {
            return res;
        }

        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        format.setLenient(false);
        try {
            format.parse(str);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static final String getFormatTime(String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(new GregorianCalendar().getTime());
    }

    /**
     * 返回给定日期的前days[-]天或者后days[+]天的日期
     *
     * @param date
     * @param days
     * @return
     */
    public static Date addDays(Date date, int days) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    /**
     * 返回给定日期的前days[-]天或者后days[+]天的日期
     *
     * @param date
     * @param days
     * @return
     */
    public static String addDays(Date date, int days, String pattern) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return formatTime(calendar.getTime(), pattern);
    }

    /**
     * 返回给定日期的前days[-]天或者后days[+]天的日期
     *
     * @param date
     * @param days
     * @return
     */
    public static String addDays(String date, int days, String pattern) {
        Date newDate = toTime(date, pattern);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(newDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return formatTime(calendar.getTime(), pattern);
    }

    /**
     * 返回给定日期的第N分钟对应时间
     *
     * @param date
     * @param minute -0～1440
     * @return
     */
    public static Date getDateTimeByMinute(Date date, int minute) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, minute / 60);
        calendar.set(Calendar.MINUTE, minute % 60);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date StringToDate(String strDate, String pattern) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date StringToTime(String str, String pattern) {
        String strDate = str.substring(0, 10) + " " + str.substring(10, 12)
                + ":" + str.substring(12) + ":" + "00";
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getDaysBetween(Date date1, Date date2) {
        Calendar d1 = Calendar.getInstance();
        d1.setTime(date1);
        Calendar d2 = Calendar.getInstance();
        d2.setTime(date2);
        if (d1.after(d2)) {
            Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {
            d1 = (Calendar) d1.clone();
            do {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
                d1.add(Calendar.YEAR, 1);

            } while (d1.get(Calendar.YEAR) != y2);
        }
        return days;
    }

    public static int compareDate(String date1, String date2, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		int result = 0;
		try {
			result = compareDate(sdf.parse(date1), sdf.parse(date2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

    public static int compareDate(Date date1, Date date2) {
        if (null == date1) {
            if (null == date2) {
                return 0;
            } else {
                return -1;
            }
        } else {
            if (null == date2) {
                return -1;
            } else {
                return date1.compareTo(date2);
            }
        }
    }

    public static Date getCurrentTime() {
        Calendar calendar = new GregorianCalendar();
        return calendar.getTime();
    }

    public static Date getCurrentDate() {
        Calendar calendar = new GregorianCalendar();
        Date time = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String s = format.format(time);
        try {
            return format.parse(s);
        } catch (Exception e) {
            return null;
        }
    }

    public static int getCurrentYear() {
        Calendar calendar = new GregorianCalendar();
        return calendar.get(Calendar.YEAR);
    }

    public static int getCurrentMonth() {
        Calendar calendar = new GregorianCalendar();
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static String getWeekOfDate(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        switch (week) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                break;
        }
        return "Unknown";
    }

    public static String getDatePart(Date date, int part) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        switch(part){
            case Calendar.YEAR:
                return String.valueOf(calendar.get(Calendar.YEAR));
            case Calendar.MONTH:
                return String.valueOf(calendar.get(Calendar.MONTH) + 1);
            case Calendar.DAY_OF_WEEK:
                return getWeekOfDate(date);
            case Calendar.DAY_OF_YEAR:
                return String.valueOf(calendar.get(Calendar.DAY_OF_YEAR) + 1);
            default:
                String.valueOf(calendar.get(part));
        }

        return "";
    }

    public static String formatTime(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static Date toTime(String time, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = new Date();
		try {
			date = format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

    public static Date getFirstDayOfCurrentWeek() {
        Calendar calendar = new GregorianCalendar();
        int day = calendar.getActualMinimum(Calendar.DAY_OF_WEEK);
        calendar.set(Calendar.DAY_OF_WEEK, day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getFirstDayOfCurrentMonth() {
        Calendar calendar = new GregorianCalendar();
        int day = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getFirstDayOfCurrentSeason() {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(new Date());
        int curMonth = cDay.get(Calendar.MONTH);
        if (curMonth >= Calendar.JANUARY && curMonth <= Calendar.MARCH) {
            cDay.set(Calendar.MONTH, Calendar.JANUARY);
        }
        if (curMonth >= Calendar.APRIL && curMonth <= Calendar.JUNE) {
            cDay.set(Calendar.MONTH, Calendar.APRIL);
        }
        if (curMonth >= Calendar.JULY && curMonth <= Calendar.SEPTEMBER) {
            cDay.set(Calendar.MONTH, Calendar.JULY);
        }
        if (curMonth >= Calendar.OCTOBER && curMonth <= Calendar.DECEMBER) {
            cDay.set(Calendar.MONTH, Calendar.OCTOBER);
        }
        cDay.set(Calendar.DAY_OF_MONTH,
                cDay.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cDay.getTime();
    }

    public static Date getFirstDayOfCurrentYear() {
        Calendar calendar = new GregorianCalendar();
//        int day = calendar.getActualMinimum(Calendar.DAY_OF_YEAR);
//        calendar.set(Calendar.DAY_OF_YEAR, day);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getLastDayOfCurrentMonth() {
        Calendar calendar = new GregorianCalendar();
        int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getFirstDayOfDate(String date) throws Exception {
        String[] dates = date.split("-");
        int year = Integer.parseInt(dates[0]);
        int month = Integer.parseInt(dates[1]);
        String nowDate = year + "-" + month + "-" + 1;
        return toTime(nowDate, "yyyy-MM-dd");
    }

    public static Date getLastDayOfDate(String date) throws Exception {
        String[] dates = date.split("-");
        int year = Integer.parseInt(dates[0]);
        int month = Integer.parseInt(dates[1]);
        int day = Integer.parseInt(dates[2]);

        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        int maxDate = cal.getActualMaximum(Calendar.DATE);
        String nowDate = year + "-" + month + "-" + maxDate;
        return toTime(nowDate, "yyyy-MM-dd");
    }

    /**
     * 获取指定日期的所属月份的最后一天
     *
     * @param date
     * @return
     * @throws Exception
     */
    @SuppressWarnings("static-access")
    public static Date getLastDayOfDate(Date date) throws Exception {
        Calendar cal = Calendar.getInstance();
        if (date == null) {
            cal.setTime(new Date());
        } else {
            cal.setTime(date);
        }

        int maxDate = cal.getActualMaximum(Calendar.DATE);
        String nowDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1)
                + "-" + maxDate;
        return toTime(nowDate, "yyyy-MM-dd");
    }

    /**
     * 获取指定2个日期相差的月份数
     *
     * @param beginDate
     * @param endDate
     * @return
     * @throws Exception
     */
    public static int getDiffMonthsOfTwoDate(Date beginDate, Date endDate)
            throws Exception {
        int result = 0;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(beginDate);
        c2.setTime(endDate);
        result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        return Math.abs(result + 1);
    }

    /**
     * 获取指定日期的星期信息
     *
     * @param date
     * @return
     */
    public static String getWeek(Date date) {
        String[] weeks = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return weeks[week_index];
    }

    /**
     * 返回标准格式的日期字符串。格式:yyyy-MM-dd HH:mm:ss
     *
     * @param str
     * @return
     */
    public static String getStandardTimeFormat(String str) {
        if (StringUtil.isEmpty(str)) {
            return null;
        }
        String year = null;
        String month = null;
        String day = null;
        if (str.indexOf("月") > 0) {
            String[] datetimeSplit = str.replaceAll("月 -", "月-").split(" ");
            if (datetimeSplit.length == 1) {// 04-11月-15
                System.out.println("datetimeSplit=" + datetimeSplit[0]);
                String[] dateSplit = datetimeSplit[0].split("-");
                year = "20" + dateSplit[2].trim();
                month = StringUtil.fixStr(Integer.parseInt(dateSplit[1]
                        .replaceAll("月", "").trim()), 2);
                day = dateSplit[0].trim();
                return (year + "-" + month + "-" + day);
            } else if (datetimeSplit.length == 3) {// 04-11月-15 09.52.17.000000
                // 上午
                String time = null;
                String[] dateSplit = datetimeSplit[0].split("-");
                year = "20" + dateSplit[2].trim();
                month = StringUtil.fixStr(Integer.parseInt(dateSplit[1]
                        .replaceAll("月", "").trim()), 2);
                day = dateSplit[0].trim();

                String[] timeSplit = datetimeSplit[1].split("\\.");
                if ("下午".equals(datetimeSplit[2])) {
                    time = " " + (Integer.parseInt(timeSplit[0].trim()) + 12)
                            + ":" + timeSplit[1].trim() + ":"
                            + timeSplit[2].trim();
                } else if ("上午".equals(datetimeSplit[2])) {
                    time = " " + timeSplit[0].trim() + ":"
                            + timeSplit[1].trim() + ":" + timeSplit[2].trim();
                }
                return (year + "-" + month + "-" + day + time);
            }
        }
        return str;
    }

    public static Date getStartTime(Date startTime) throws Exception {
        String str = formatTime(startTime, yyyyMMdd);
        str = str + " " + "00:00:00";
        return toTime(str, yyyyMMddHHmmss);
    }

    public static Date getEndTime(Date startTime) throws Exception {
        String str = formatTime(startTime, yyyyMMdd);
        str = str + " " + "23:59:59";
        return toTime(str, yyyyMMddHHmmss);
    }

    public static Date getAfterOfManyDayTime(Date date, int days) throws Exception {
        return toTime(formatTime(addDays(date, days), yyyyMMdd2) + formatTime(date, HHMMSS), yyyyMMddHHmmss2);
    }

    public static Date getAfterOfManyDaysTime(Date date, int days) throws Exception {
        return new Date(date.getTime() + days * 24 * 3600 * 1000);
    }

    public static String getStartTimeStr(Date startTime) throws Exception {
        String str = formatTime(startTime, yyyyMMdd);
        return str + " " + "00:00:00";
    }

    public static String getEndTimeStr(Date startTime) throws Exception {
        String str = formatTime(startTime, yyyyMMdd);
        return str + " " + "23:59:59";
    }

    public static Date getInitTime(String str) throws Exception {
        str = str + " " + "00:00:00";
        return toTime(str, yyyyMMddHHmmss);
    }

    /**
     * 获取当前时间前3分钟
     *
     * @return
     */
    public static String getLastMinute(String pattern, int minute) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, minute);// 3分钟之前的时间
        Date beforeD = beforeTime.getTime();
        String time = sdf.format(beforeD);
        return time;
    }

    public static String getLastMonthLastDay(String pattern, int lastMonth) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, lastMonth);//-1
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);//"yyyy-MM-dd  HH:mm:ss"
        int lastMonthMaxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), lastMonthMaxDay, 23, 59, 59);
        return sdf.format(c.getTime()); //上月最后一天
    }

    public static String getLastMonthFirstDay(String pattern, int lastMonth) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, lastMonth);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        int lastMonthMaxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), lastMonthMaxDay, 23, 59, 59);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-01");
        return sdf2.format(c.getTime()); //上月第一天
    }

    // 获取上周的开始时间
    public static String getBeginDayOfLastWeek(String pattern) {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek - 7);
        return formatTime(getDayStartTime(cal.getTime()), pattern);
    }

    public static String getEndDayOfLastWeek(String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDateOfLastWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return formatTime(getDayEndTime(weekEndSta), pattern);
    }

    // 获取上周的开始时间
    public static Date getBeginDateOfLastWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek - 7);
        return getDayStartTime(cal.getTime());
    }

    // 获取上周的结束时间
    public static Date getEndDateOfLastWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDateOfLastWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }

    // 获取某个日期的开始时间
    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    // 获取某个日期的结束时间
    public static Timestamp getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 返回给定日期的在去年对应的日期
     *
     * @param date
     * @return
     */
    public static Date getDateLastYear(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
        return calendar.getTime();
    }

	/**
	 * 返回给定日期的在去年对应的日期
	 *
	 * @param date
	 * @return
	 */
	public static String getDateLastYear(Date date,String pattern) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
		return formatTime(calendar.getTime(),pattern);
	}
	
	/**
	 * 获取日期所在周的周一
	 * @param dsString
	 * @return
	 * @throws Exception
	 */
	public static String getWeekByDate(String dsString)  {
	    try{
	        Date time =  getInitTime(dsString);
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(time);
	        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
	        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
	        if (1 == dayWeek) {
	            cal.add(Calendar.DAY_OF_MONTH, -1);
	        }
	        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
	        int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
	        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
	        return sdf.format(cal.getTime());
	    }catch (Exception e){
	        e.printStackTrace();
	    }
	    return dsString;
	}
	
	/**
     * 获取日期所在月的第一天
     * @param dsString
     * @return
     * @throws Exception
     */
	public static String getMonthByDate(String dsString){
	    try{
	        Date time =  getInitTime(dsString);
	        
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	        Calendar calendar = Calendar.getInstance();  
	        calendar.setTime(time);  
	        calendar.set(Calendar.DAY_OF_MONTH, 1);
	        Date theDate = calendar.getTime();  
	        return sdf.format(theDate.getTime());
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	    return dsString;
    }

    public static int getNowMinute() {
        LocalDateTime now = LocalDateTime.now();
        return now.getHour() * 60 + now.getMinute() + 1;
    }

    public static LocalDate getLastDayOfDimension(String ds, String dimension) {
        LocalDate date = LocalDate.parse(ds, DateTimeFormatter.ofPattern(yyyyMMdd));
        if(dimension.equals(DIMENSION_YEAR)){
            return date.with(TemporalAdjusters.lastDayOfYear());
        }else if (dimension.equals(DIMENSION_SEASON)) {
            int monthLeft = date.getMonthValue() % 3;
            int monthDiff = monthLeft > 0 ? 3 - monthLeft : 0;
            return date.plusMonths(monthDiff).with(TemporalAdjusters.lastDayOfMonth());
        }else if (dimension.equals(DIMENSION_MONTH)) {
            return date.with(TemporalAdjusters.lastDayOfMonth());
        }else if (dimension.equals(DIMENSION_WEEK)) {
            return date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        }else {
            return date;
        }
    }

    public static boolean isLastDayOfDimension(String ds, String dimension) {
        LocalDate date = LocalDate.parse(ds, DateTimeFormatter.ofPattern(yyyyMMdd));
        return date.isEqual(getLastDayOfDimension(ds,dimension));
    }

    public static LocalDate getFirstDayOfDimensionV2(String dsEnd, String dimension) {
        LocalDate date = LocalDate.parse(dsEnd, DateTimeFormatter.ofPattern(yyyyMMdd));
        if(dimension.equals(DIMENSION_YEAR)){
            return LocalDate.of(date.getYear(), 1, 1);
        }else if (dimension.equals(DIMENSION_SEASON)) {
            return LocalDate.of(date.getYear(), (date.getMonthValue() - 1) / 3 * 3 + 1, 1);
        }else if (dimension.equals(DIMENSION_MONTH)) {
            return LocalDate.of(date.getYear(), date.getMonth(), 1);
        }else if (dimension.equals(DIMENSION_WEEK)) {
            return date.with(DayOfWeek.MONDAY);
        }else {
            return date;
        }
    }

    public static LocalDate getPreviousDayOfDimension(String ds, String dimension) {
        LocalDate date = LocalDate.parse(ds, DateTimeFormatter.ofPattern(yyyyMMdd));
        if(dimension.equals(DIMENSION_YEAR)){
            return date.minusYears(1);
        }else if (dimension.equals(DIMENSION_SEASON)) {
            return date.minusMonths(3);
        }else if (dimension.equals(DIMENSION_MONTH)) {
            return date.minusMonths(1);
        }else if (dimension.equals(DIMENSION_WEEK)) {
            return date.minusWeeks(1);
        }else {
            return date.minusDays(1);
        }
    }

    public static long getElapsedDaysOfDimension(String ds, String dimension) {
        LocalDate date = LocalDate.parse(ds, DateTimeFormatter.ofPattern(yyyyMMdd));
        if(dimension.equals(DIMENSION_YEAR)){
            return date.getDayOfYear();
        }else if (dimension.equals(DIMENSION_SEASON)) {
            return Math.abs(ChronoUnit.DAYS.between(getFirstDayOfDimensionV2(ds, DIMENSION_SEASON),date)) + 1;
        }else if (dimension.equals(DIMENSION_MONTH)) {
            return date.getDayOfMonth();
        }else if (dimension.equals(DIMENSION_WEEK)) {
            return date.getDayOfWeek().getValue();
        }else {
            return 0;
        }
    }
}
