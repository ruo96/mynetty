package com.wrh.TimeCalculate;

import com.alibaba.fastjson.JSON;
import com.netflix.servo.annotations.DataSourceType;
import com.wrh.collection.map.DateUtil;
import com.wrh.utils.DateUtils;
import com.wrh.utils.RowKeyHashUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.util.TimeUtils;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.junit.Test;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StopWatch;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.wrh.collection.map.DateUtil.*;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:49 2019/9/30 0030
 * @Modified By:
 */
@Slf4j
public class TestTime {

    @Test
    public void test(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.format(formatter));

        //10分钟前
        String d1 = now.minusMinutes(10).format(formatter);
        //10分钟后
        String d2 = now.plusMinutes(10).format(formatter);

        System.out.println(d1);
        System.out.println(d2);


        LocalDateTime t5 = LocalDateTime.parse("2019-01-01 00:00:00", formatter);

        System.out.println(t5.format(formatter));
    }

    @Test
    public void test1(){
        log.info("=== timestamp: {}",System.currentTimeMillis());
    }

    @Test
    public void test2(){

        log.info("===>{}", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        log.info("===>{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        log.info("===>{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));

    }

    @Test
    public void test3(){
        for (int i = 0; i < 100; i++) {

            log.info(UUID.randomUUID().toString().substring(0,6));
        }
    }

    @Test
    public void test3_1(){

        String i = "user_online_gameid_";
        String data = "user_online_gameid_20200316_1234_5678";
//        log.info("{}",i.length());
//        log.info("data: {}, substring:{}",data, StringUtils.substring(data,0,i.length()));
        System.out.println(StringUtils.substring(data,0,i.length()));

    }

    @Test
    public void TestGetMinute() {
        Long time = System.currentTimeMillis();
        Long time1  = DateUtils.getMinuteByDatetime(time);
        String time2  = DateUtils.getMinute(time);
        log.info("minue: {}   now: {}h{}m" , time1,time1/60, time1%60);
        log.info("minue: {}   now: {}h{}m" , time2,time1/60, time1%60);
    }

    @Test
    public void TestCalender() {
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(System.currentTimeMillis());//设置服务器接受时间
        c1.set(Calendar.HOUR_OF_DAY, 0);//调整小时
        c1.set(Calendar.MINUTE, 0);//调整分钟
        c1.set(Calendar.SECOND, 0);//调整秒
        c1.set(Calendar.MILLISECOND, 0);//调整秒
        Long time = c1.getTime().getTime();
        Long now = System.currentTimeMillis();
        Long diff = now - time;
        log.info(">>> begin: {}  now:{}  diff: {}",time,now,diff);
        log.info(">>> now: {}  H:{}  m: {}",LocalDateTime.now(), diff/1000/60/60, diff/1000/60%60);



    }

    @Test
    public void TestGetMinute1() {

        long minute1 = Math.round(1000*1.0/60000);
        long minute2 = (long) Math.floor(1000*1.0/60000);
        long minute3 = (long) Math.ceil(86399000*1.0/60000);
        long minute4 = (long) Math.ceil(86340000*1.0/60000);
        long minute5 = (long) Math.ceil(86341000*1.0/60000);
        long minute6 = (long) Math.ceil(86339000*1.0/60000);
        log.info(">>> minute1: {}",minute1);
        log.info(">>> minute2: {}",minute2);
        log.info(">>> minute3: {}",minute3);
        log.info(">>> minute4: {}",minute4);
        log.info(">>> minute5: {}",minute5);
        log.info(">>> minute6: {}",minute6);
    }

    @Test
    public void TestGetMinute2() {

        long minute1 = (long) Math.ceil(86399000*1.0/60000);
        long minute2 = (long) Math.ceil(86340000*1.0/60000);
        long minute3 = (long) Math.ceil(86341000*1.0/60000);
        long minute4 = (long) Math.ceil(86339000*1.0/60000);
        log.info(">>> minute1: {}",minute1);
        log.info(">>> minute2: {}",minute2);
        log.info(">>> minute3: {}",minute3);
        log.info(">>> minute4: {}",minute4);

        long minute5 = (long) Math.ceil(86399000/60000);
        long minute6 = (long) Math.ceil(86340000/60000);
        long minute7 = (long) Math.ceil(86341000/60000);
        long minute8 = (long) Math.ceil(86339000/60000);

        log.info(">>> minute5: {}",minute5);
        log.info(">>> minute6: {}",minute6);
        log.info(">>> minute7: {}",minute7);
        log.info(">>> minute8: {}",minute8);
    }

    @Test
    public void TestTime() throws InterruptedException {
        while(true){

            Long time = System.currentTimeMillis();
            log.info("timestamp:{}   localdatetime:{}", time, LocalDateTime.now() );

            Long timeWindow = DateUtils.getMinuteByDatetime(time);
            log.info("timeWindow:{} ", timeWindow );

            TimeUnit.SECONDS.sleep(1);
        }



    }

    @Test
    public void Test() {
        String a = "user_online_20200401_347451506_102768";
        String b = RowKeyHashUtils.obtainHashRowKeyStr(a);
        String c = RowKeyHashUtils.getOrigionKey(b);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    @Test
    public void TestLong() {
        Long time = 5000L;
        Long time2 = (time + 5000)/60000;

        long time1 = time / 60;
        System.out.println(time1);
        System.out.println(time2);

    }

    @Test
    public void test4(){
        CqTraceUpload cqTraceUpload = new CqTraceUpload();
        cqTraceUpload.setBIZ_TYPE("123");
        LocalDateTime dateTime = LocalDateTime.now();
        cqTraceUpload.setREQ_TIME(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        cqTraceUpload.setREQ_ID(dateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + UUID.randomUUID().toString().substring(0,6));
        cqTraceUpload.setAUTH_ID("");
        cqTraceUpload.setPARAM("");

        log.info("===>{}", JSON.toJSONString(cqTraceUpload));
    }

    /**
     *
     * @throws InterruptedException
     */
    @Test
    public void Test1() throws InterruptedException {
        while(true) {
        DateTime currentDateTime = new DateTime();
        Duration duration = new Duration(currentDateTime, currentDateTime.withMillisOfDay(0).plusDays(1));
        Long iRemainSecs = duration.getStandardSeconds();

            System.out.println(iRemainSecs);
            TimeUnit.SECONDS.sleep(2);
        }
    }

    @Test
    public void Test2() {
        long timestamp = System.currentTimeMillis();
        String serverDate = DateUtils.getFutureDate(Long.valueOf(timestamp), 0);
        String serverDate1 = DateUtils.getFutureDate(Long.valueOf(timestamp), 1);
        String serverDate3 = DateUtils.getFutureDate(Long.valueOf(timestamp), 3);
        String serverDate4 = DateUtils.getFutureDate(Long.valueOf(timestamp), -1);
        System.out.println(serverDate);
        System.out.println(serverDate1);
        System.out.println(serverDate3);
        System.out.println(serverDate4);
    }

    @Test
    public void Test3() {
        String dateStr = "20200114";
        LocalDate date = LocalDate.parse(dateStr,DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate now = LocalDate.now();
        if(date.isAfter(now)) {
            System.out.println(date + " is after: " + now);
        } else if(date.isEqual(now)) {
            System.out.println(date + " is equal: " + now);
        } else {
            System.out.println(date + " is before: " + now);
        }

        if(date.plusDays(1).isBefore(LocalDate.now())) {
            System.out.println(date + " is before 1 : " + now);

        }
    }

    @Test
    public void Test4() {
        String nowDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println(nowDate);
        System.out.println(LocalDate.now().toString());
    }

    @Test
    public void Test5() {
        Long time = System.currentTimeMillis();

        System.out.println(DateUtils.getMinuteByDatetime(time));
    }

    @Test
    public void Test6() throws Exception {
        Calendar ca = Calendar.getInstance();
        ca.setTime(DateTimeUtil.getInitTime("2020-01-02"));

        Integer totalDays = ca.getActualMaximum(Calendar.DAY_OF_YEAR);
        Integer days = ca.get(Calendar.DAY_OF_YEAR);
        log.info(">>> 今年总天数： {}", totalDays);
        log.info(">>> 当前天数： {}", days);
    }

    @Test
    public void Test7() {
        String firstDayOfWeek = DateTimeUtil.getWeekByDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        String firstDayOfMonth = DateTimeUtil.getMonthByDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        log.info(">>> week: {}",firstDayOfWeek);
        log.info(">>> month: {}",firstDayOfMonth);

        String firstDayOfCurrentYear = DateTimeUtil.getFirstDayOfCurrentYear().toString();
        log.info(">>> firstDayOfCurrentYear :{}",firstDayOfCurrentYear);
    }

    @Test
    public void Test8() {
        log.info(">>> value: {}",DateTimeUtil.getFormatTime("yyyy-MM-dd"));

        Date startDate = DateTimeUtil.StringToDate("2020-04-13",DateTimeUtil.yyyyMMdd);
        Date endDate = DateTimeUtil.StringToDate("2020-04-13",DateTimeUtil.yyyyMMdd);
        int days = DateTimeUtil.getDaysBetween(endDate, startDate);
        log.info(">>> days: {}",days);

    }

    @Test
    public void Test9() {
        int daysOfThisYear = LocalDate.now().lengthOfYear();
        log.info(">>> daysOfThisYear: {}", daysOfThisYear);

        int day = LocalDate.of(2020,4,23).lengthOfYear();
        log.info(">>> day: {}", day);
    }

    @Test
    public void Test311() {
        String yyyyMMddHHmmss = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        System.out.println(yyyyMMddHHmmss);


    }

    @Test
    public void Test10() {
        int i = Calendar.JANUARY;
        System.out.println(i);
    }

    @Test
    public void Test11() {
        cycleDate();
    }
    //处理周期性的日期
    public void cycleDate(){
        LocalDate today = LocalDate.now();
        LocalDate dateOfBirth = LocalDate.of(2020, 06, 02);

        MonthDay birthday = MonthDay.of(dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(today);

        if(currentMonthDay.equals(birthday)){
            System.out.println("Many Many happy returns of the day !!");
        }else{
            System.out.println("Sorry, today is not your birthday");
        }
    }

    @Test
    public void Test12() {
        Clock clock = Clock.systemUTC();
        System.out.println("clock: " + clock);

        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println("default clock: " + defaultClock);
    }

    /**
     * java8处理时区
     */
    @Test
    public void Test13() {
        //设置时区
        ZoneId america = ZoneId.of("America/New_York");

        LocalDateTime localtDateAndTime = LocalDateTime.now();

        ZonedDateTime dateAndTimeInNewYork  = ZonedDateTime.of(localtDateAndTime, america );
        System.out.println("localtDateAndTime = " + localtDateAndTime);
        System.out.println("现在的日期和时间在特定的时区 : " + dateAndTimeInNewYork);
    }

    @Test
    public void Test14() {
        Instant timestamp = Instant.now();
        System.out.println(timestamp);

        String date = "2020-06-01";
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
//        LocalDate localDate1 = LocalDate.parse(date, DateTimeFormatter.);
        System.out.println(localDate);
//        System.out.println(localDate1);
    }

    @Test
    public void Test15() {
        System.out.println(isFirstDayOfYear("2020-01-01"));
        System.out.println(isFirstDayOfYear("2020-01-03"));
    }

    public static boolean isFirstDayOfYear(String dsEnd) {
        LocalDate date = LocalDate.parse(dsEnd, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return date.getDayOfYear() == 1;
    }

    @Test
    public void Test16() {
        String date = "2020-05-01";
        int month = Integer.valueOf(date.replace("-","").substring(0,6));
        System.out.println(month);
        LocalDate date1 = LocalDate.parse(date,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(date.substring(0,4));
        System.out.println(date.substring(5,7));
        System.out.println(date1.getMonth().getValue());
        System.out.println(date1.getMonth().name());
        System.out.println(date1.getMonth().toString());
        System.out.println(String.valueOf(date1.getYear()));
    }
    
    @Test
    public void Test17() {
        String a = null;
        String b = "wrh";
        String c = "";
        StringBuilder sb = new StringBuilder();
        sb.append(a).append(":").append(b).append(":").append(c).append(":");
        System.out.println(sb.toString());

        String str = sb.toString();
        String[] s = str.split(":");
        System.out.println(s.length);
        for (String s1: s) {
            System.out.println( "---"+s1 + "---");

        }
        System.out.println("---end---");

    }

    @Test
    public void Test18() {
        String date = "2020-05-07";
        LocalDate d = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(d.getDayOfMonth());
        System.out.println(d.getMonthValue());
        System.out.println(d.lengthOfYear());
        System.out.println(d.lengthOfMonth());
    }

    @Test
    public void Test19() {
        String ds = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(ds);

    }

    @Test
    public void Test20() {
        LocalDate now = LocalDate.now();
        System.out.println(now);
        System.out.println(now.plusMonths(1));

//        LocalDate day =  LocalDate.parse("2020-05-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate day =  LocalDate.parse("2020-05-01", DateTimeFormatter.ISO_DATE);
        System.out.println(day);
        System.out.println(day.plusMonths(1));
        System.out.println(day.minusMonths(1));
    }

    @Test
    public void Test21() {
        LocalDate ds = LocalDate.now();
        System.out.println(ds.toString());
    }

    /*public static boolean isFirstDayOfDimension(String dsEnd, String dimension) {
        LocalDate date = LocalDate.parse(dsEnd, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (dimension.equals("year")) {
            return date.getDayOfYear() == 1;
        }else if (dimension.equals("month")) {
            return date.getDayOfMonth() == 1;
        }else {
            return true;
        }
    }*/

    @Test
    public void Test22() {
        String ds = "2020-02-01";
        System.out.println(isFirstDayOfDimension(ds, "year"));
        System.out.println(isFirstDayOfDimension(ds, "month"));
    }

    public static boolean isEqualDimension(LocalDate before, LocalDate dsEnd, String dimension) {
        if(dimension.equals("year")){
            return before.getYear() == dsEnd.getYear();
        }else if (dimension.equals("month")) {
            return before.getMonthValue() == dsEnd.getMonthValue();
        }else {
            return false;
        }
    }

    @Test
    public void Test23() {
        String d1 = "2020-05-01";
        String d2 = "2020-05-02";
        String d3 = "2020-01-01";
        String d4 = "2019-12-31";

        LocalDate date1 = LocalDate.parse(d1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate date2 = LocalDate.parse(d2, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate date3 = LocalDate.parse(d3, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate date4 = LocalDate.parse(d4, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(isEqualDimension(date1,date2,"year"));
        System.out.println(isEqualDimension(date1,date2,"month"));
        System.out.println(isEqualDimension(date3,date4,"year"));
        System.out.println(isEqualDimension(date1,date3,"year"));
    }

    /*public static LocalDate getFirstDayOfDimension(String dsEnd, String dimension) {
        LocalDate date = LocalDate.parse(dsEnd, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if(dimension.equals("year")){
            return LocalDate.of(date.getYear(), 1, 1);
        }else if (dimension.equals("month")) {
            return LocalDate.of(date.getYear(), date.getMonth(), 1);
        }else {
            return null;
        }
    }*/

    @Test
    public void Test24() {
        System.out.println(getFirstDayOfDimension("2020-05-01","year").toString());
        System.out.println(getFirstDayOfDimension("2020-05-03","month").toString());

        String endDate = "2020-06-05";
        Integer monthValue = Integer.valueOf(endDate.replace("-","").substring(0,6));
        System.out.println(monthValue);
    }

    @Test
    public void Test25() {
        System.out.println(DateUtils.getDayOfMonth("2020-06-03"));
        System.out.println(DateUtils.getTotalDayOfMonth("2020-05-03"));

        LocalDate today = LocalDate.now().minusMonths(1);
        //本月的第一天
        LocalDate firstday = LocalDate.of(today.getYear(),today.getMonth(),1);
        //本月的最后一天
        LocalDate lastDay =today.with(TemporalAdjusters.lastDayOfMonth());

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 1000000 ; i++) {
            Calendar c = Calendar.getInstance();
            int days = c.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
//            System.out.println("今年天数： " + days);
        }
        stopWatch.stop();
        System.out.println("last: " + stopWatch.getTotalTimeMillis());

        stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 1000000; i++) {
            LocalDate d = LocalDate.now();
            int days2 = d.with(TemporalAdjusters.lastDayOfYear()).getDayOfYear();   //更快一些
//            System.out.println("今年天数： " + days2);
        }
        stopWatch.stop();
        System.out.println("last 1: " + stopWatch.getTotalTimeMillis());

    }

    @Test
    public void Test548() {
        LocalDate today = LocalDate.now().minusMonths(1);
        //本月的第一天
        LocalDate firstday = today.with(TemporalAdjusters.lastDayOfYear());
        //本月的最后一天
        LocalDate lastDay =today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(firstday);
        System.out.println(lastDay);

    }

    /**
     * 算相对进度
     */
    @Test
    public void Test560() {
        Long dayTarget = 5479452L;
        Long monthTarget = 166666666L;
        Long yearTarget = 2000000000L;

        int nowMinute = DateTimeUtil.getNowMinute();
        System.out.println(dayTarget * nowMinute / 1440);
        Long monthAvgDay = monthTarget / 31;
        System.out.println(monthAvgDay * 6 + monthAvgDay * nowMinute / 1440);

        int dayOfYear = LocalDate.now().getDayOfYear();
        System.out.println(dayTarget * (dayOfYear-1) + dayTarget * nowMinute / 1440);

    }

    @Test
    public void Test26() {
        LocalDate now = LocalDate.now();
        int i = now.getDayOfYear();
        System.out.println(i);
    }

    @Test
    public void Test27() {
        LocalDate now = LocalDate.now();
        System.out.println(now.getDayOfYear());
        System.out.println(now.getDayOfMonth());
        System.out.println(DateTimeUtil.getNowMinute());
        LocalDate ds = LocalDate.of(2020,1,1);
        System.out.println(ds.minusMonths(1));
    }

    @Test
    public void Test562() {
        int minute = LocalDateTime.now().getMinute();
        System.out.println(minute +  "----" +LocalDateTime.now());

        int nowMinute = DateUtils.getNowMinute();
        System.out.println(nowMinute +  "----" +LocalDateTime.now());
    }

    @Test
    public void Test573() {
        ZonedDateTime ds = ZonedDateTime.now();
        System.out.println(ds);

        getZoneTime();
    }

    //获取特定时区下面的时间
    public void getZoneTime(){
        //设置时区
        ZoneId america = ZoneId.of("America/New_York");

        LocalDateTime localtDateAndTime = LocalDateTime.now();

        ZonedDateTime dateAndTimeInNewYork  = ZonedDateTime.of(localtDateAndTime, america );
        System.out.println("现在的日期和时间在特定的时区 : " + dateAndTimeInNewYork);
        System.out.println(dateAndTimeInNewYork.getHour());
    }

    //使用 YearMonth类处理特定的日期
    public void checkCardExpiry(){
        YearMonth currentYearMonth = YearMonth.now();
        System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());

        YearMonth creditCardExpiry = YearMonth.of(2028, Month.FEBRUARY);
        System.out.printf("Your credit card expires on %s %n", creditCardExpiry);
    }

    @Test
    public void Test602() {
        LocalDate date = LocalDate.parse("2020-06-22", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate date1 = LocalDate.parse("2020-06-11", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println("date.getLong(ChronoField.EPOCH_DAY) = " + date.getLong(ChronoField.EPOCH_DAY));
        System.out.println("date1.getLong(ChronoField.EPOCH_DAY) = " + date1.getLong(ChronoField.EPOCH_DAY));

        System.out.println(Period.between(date, date1).getDays());
        System.out.println(Period.between(date1, date).getDays());
        System.out.println(Math.abs(Period.between(date1, date).getDays()));


    }

    private static final String yyyyMMdd = "yyyy-MM-dd";

    @Test
    public void Test617() {
        String ds = "2020-06-28";
        long a = 7650000000L;
//        int days = LocalDate.parse(ds,DateTimeFormatter.ofPattern(yyyyMMdd)).getDayOfYear();
        LocalDate date = LocalDate.parse(ds,DateTimeFormatter.ofPattern(yyyyMMdd));
        int days = date.getDayOfYear();
                int totalDays = date.with(TemporalAdjusters.lastDayOfYear()).getDayOfYear();
        System.out.println(days);  //180天
        System.out.println(date.with(TemporalAdjusters.lastDayOfYear()).getDayOfYear());
        System.out.println(a * days / totalDays );


    }
    @Test
    public void Test633() {
        String date = "2020-06-29";
        if(date.equals(LocalDate.now().toString())){
            System.out.println("is today");
        }else {
            System.out.println("not today");
        }

    }

    @Test
    public void Test644() {
        String date = "2020-06-28";
        if(LocalDate.now().minusDays(1).toString().equals(date)){
            System.out.println(" is yesterday");
        }else {
            System.out.println("not yesterday");
        }
        System.out.println(LocalDateTime.now().toString());

    }
    
    @Test
    public void Test656() {
        Date now = new Date();
        System.out.println(now.getDay());
        System.out.println(now.getMonth());
        Instant instant = now.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        System.out.println(localDateTime);
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getDayOfYear());

        System.out.println("==========================");
        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(now.toInstant(), zoneId);
        System.out.println(localDateTime1);
        System.out.println(localDateTime1.getDayOfMonth());
        System.out.println(localDateTime1.getDayOfYear());

    }

    @Test
    public void Test676() {
        System.out.println(isDateToday(new Date()));

    }

    public static boolean isDateToday(Date date){
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate().equals(LocalDate.now());
    }

    public static boolean isThisYearMonth(String ds) {
        LocalDate date = LocalDate.parse(ds,DateTimeFormatter.ofPattern(yyyyMMdd));
        LocalDate now = LocalDate.now();
        return date.getYear() == now.getYear() && date.getMonthValue() == now.getMonthValue();
    }
    @Test
    public void Test691() {
        String ds = "2021-06-30";
        System.out.println(isThisYearMonth(ds));

    }

    public static boolean isYesterday(String ds) {
        return LocalDate.parse(ds,DateTimeFormatter.ofPattern(yyyyMMdd)).equals(LocalDate.now().minusDays(1));
    }

    @Test
    public void Test702() {
        System.out.println(isYesterday("2020-06-29"));
        System.out.println(isYesterday("2020-06-30"));
        System.out.println(isYesterday("2020-06-28"));

    }

    /**
     * 获取第几周
     */
    @Test
    public void Test710() {
        LocalDate date = LocalDate.now();
        WeekFields weekFields = WeekFields.ISO;
        int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
        System.out.println(weekNumber);

        LocalDate d1 = LocalDate.parse("2020-06-29",DateTimeFormatter.ofPattern(yyyyMMdd));
        int weekNumber1 = date.get(weekFields.weekOfWeekBasedYear());
        System.out.println(weekNumber1);

        LocalDate d2 = LocalDate.parse("2020-07-05",DateTimeFormatter.ofPattern(yyyyMMdd));
        int weekNumber2 = date.get(weekFields.weekOfWeekBasedYear());
        System.out.println(weekNumber2);


    }

    public static Integer getNowMinute(){
        LocalDateTime now = LocalDateTime.now();
        return now.getHour() * 60 + now.getMinute();
    }

    @Test
    public void Test737() {
        System.out.println(LocalDateTime.now());

        System.out.println(getNowMinute());

        LocalDateTime setTime = LocalDateTime.of(2020,7,6,23,59,10);
        System.out.println(setTime.getHour());
        System.out.println(setTime.getMinute());
        System.out.println(setTime.getHour() * 60 + setTime.getMinute());
    }

    @Test
    public void Test749() {
        LocalDate ds = LocalDate.now();
        LocalDate dsEnd = LocalDate.parse("2020-06-05", DateTimeFormatter.ofPattern(yyyyMMdd));
        if(ds.minusMonths(1).isAfter(dsEnd)){
            System.out.println("isAfter");
        }
    }

    @Test
    public void Test758() {

        LocalDate dsEnd = LocalDate.parse("2020-03-01", DateTimeFormatter.ofPattern(yyyyMMdd));
        System.out.println(dsEnd.minusDays(1).toString());
    }

    @Test
    public void Test765() {
        System.out.println(LocalDate.now().toString().substring(0,7));
    }
    
    @Test
    public void Test801() {
        LocalDateTime now =  LocalDateTime.now();
        System.out.println(System.currentTimeMillis());
        System.out.println(now.toInstant(ZoneOffset.of("+8")).toEpochMilli());

    }
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String HOUR_MINUTES_FORMAT = "HH:mm";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /** 起始时间*/
    public static final String TIME_BEGIN = "00:00:00";
    public static final String DEFAULT_SHOW = "--";
    public static final long LONG_NULL = -1;
    public static final Integer ZERO = 0;

    public static String getSharpTime(int i, String ds) {
        LocalDateTime time = LocalDateTime.parse(ds+" "+TIME_BEGIN, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)).plusHours(i);
        if(time.getDayOfYear() != LocalDate.parse(ds,DateTimeFormatter.ofPattern(DATE_FORMAT)).getDayOfYear()){
            return ds + " 24:00:00";
        }
        return time.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }

    @Test
    public void Test826() {
        System.out.println(getSharpTime(1, "2020-07-16"));

    }

    @Test
    public void Test832() {
        System.out.println(DateUtils.getNowMinute());
        int shouldSharpData = (DateUtils.getNowMinute() - 1) / 60;
        System.out.println(shouldSharpData);
        int shouldSharpData1 = LocalDateTime.now().getHour();
        System.out.println(shouldSharpData1);


    }

    /**
     * LocalDate转Date
     */
    @Test
    public void Test843() {
        LocalDate nowLocalDate = LocalDate.now();
        Date date = Date.from(nowLocalDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant());
        System.out.println(nowLocalDate);
        System.out.println(date);
    }

    /**
     * LocalDateTime转Date
     */
    @Test
    public void Test857() {
        LocalDateTime nowLocalDate = LocalDateTime.now();
        Date date = Date.from(nowLocalDate.atZone(ZoneOffset.ofHours(8)).toInstant());
        System.out.println(nowLocalDate);
        System.out.println(date);

        /** 第二种方法*/
        LocalDateTime time = LocalDateTime.now();
        Date d1 = Date.from(time.toInstant(ZoneOffset.UTC));
        System.out.println(d1);
    }

    /**
     * Date转LocalDateTime(LocalDate)
     */
    @Test
    public void Test865() {
        Date date = new Date();
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        LocalDate localDate = date.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDate();
        System.out.println(localDateTime);
        System.out.println(localDate);

        /** 第二种方法*/
        LocalDateTime localDateTime1 = date.toInstant().atZone(ZoneOffset.UTC).toLocalDateTime();
        LocalDate localDate1 = date.toInstant().atZone(ZoneOffset.UTC).toLocalDate();
        System.out.println(localDateTime1);
        System.out.println(localDate1);
    }

    /**
     * LocalDate转时间戳
     */
    @Test
    public void Test877() {
        LocalDate localDate = LocalDate.now();
        long timestamp = localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli();
        System.out.println(timestamp);
        System.out.println(System.currentTimeMillis());
    }

    /**
     * LocalDateTime转时间戳
     */
    @Test
    public void Test888() {
        LocalDateTime localDateTime = LocalDateTime.now();
        long timestamp = localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        System.out.println(timestamp);
        System.out.println(System.currentTimeMillis());
    }

    /**
     * 时间戳转LocalDateTime(LocalDate)
     */
    @Test
    public void Test899() {
        long timestamp = System.currentTimeMillis();
        LocalDate localDate = Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDate();
        LocalDateTime localDateTime = Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        System.out.println(localDate);
        System.out.println(localDateTime);

    }

    /**
     * 间隔日期不能用这个， 根本不能够满足实际需求
     */
    @Test
    public void Test854() {
        LocalDate d1 = LocalDate.parse("2020-01-01", DateTimeFormatter.ofPattern(yyyyMMdd));
        LocalDate d2 = LocalDate.parse("2019-11-01", DateTimeFormatter.ofPattern(yyyyMMdd));
        int days = Period.between(d1,d2).getDays();
        int months = Period.between(d1,d2).getMonths();
        int years = Period.between(d1,d2).getYears();
        System.out.println(days);
        System.out.println(months);
        System.out.println(years);

    }

    @Test
    public void Test607() {
        /*LocalDate d = LocalDate.of(2020,2,29);
        LocalDate e = d.minusYears(1);
        LocalDate f = d.minus(1, ChronoUnit.YEARS);
        System.out.println(d);
        System.out.println(e);
        System.out.println(f);*/

        LocalDateTime time = LocalDateTime.of(2020,7,11,23,59,58);
        System.out.println(time.getHour()*60 + time.getMinute() +1);

//        Integer minute = DateTimeUtil.getNowMinute() + 1;

    }




    public void Test939() {
        List<String> list = new ArrayList<>();
        list.add("2020-07-01");
        list.add("2020-07-02");
        list.add("2020-07-03");
        list.add("2020-08-11");
        System.out.println(list);
        list.remove(LocalDate.now().toString());
        System.out.println(list);

    }

    @Test
    public void Test950() {
        List<String> list = new ArrayList<>();
        list.add("2020-07-01");
        list.add("2020-07-02");
        list.add("2020-07-03");
        list.add("2020-08-11");
        System.out.println(list);

        List<String> list1= new ArrayList<>();
        list1.add("2020-07-01");
        list1.add("2020-07-02");

        list.removeAll(list1);
        System.out.println(list);

    }

    public static boolean isIn3Days(String ds) {
        return LocalDate.parse(ds,DateTimeFormatter.ofPattern(DATE_FORMAT)).isAfter(LocalDate.now().minusDays(4));
    }

    public static boolean isIn7Days(String ds) {
        return LocalDate.parse(ds,DateTimeFormatter.ofPattern(DATE_FORMAT)).isAfter(LocalDate.now().minusDays(8));
    }

    @Test
    public void Test976() {
        System.out.println(isIn3Days("2020-08-10"));
        System.out.println(isIn7Days("2020-08-05"));

    }

    @Test
    public void Test1002() {
        String str = "2020-01";
        System.out.println(DateUtil.getLastMonthByMs(str));
        System.out.println(DateUtil.getSameMonthOfLastYearByDs(str));

    }

    @Test
    public void Test1011() {
        System.out.println(LocalDateTime.now().getHour());

    }

    /**
     * 获取本周第一天
     */
    @Test
    public void Test1018() {
        System.out.println(getFirstDayOfDimensionV2("2020-09-25","week"));

    }
    public static LocalDate getFirstDayOfDimensionV3(String dsEnd, String dimension) {
        LocalDate date = LocalDate.parse(dsEnd, DateTimeFormatter.ofPattern(yyyyMMdd));
        return date.with(DayOfWeek.MONDAY);

    }

    public static LocalDate getFirstDayOfDimensionV2(String dsEnd, String dimension) {
        LocalDate date = LocalDate.parse(dsEnd, DateTimeFormatter.ofPattern(yyyyMMdd));

        TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
        return date.with(fieldISO, 1);

    }

    @Test
    public void Test1040() {
        System.out.println(LocalDateTime.now());
//        OperationType.
        AbstractRoutingDataSource dataSource ;
        DataSourceType dataSourceType;
//        dataSourceType
    }

    @Test
    public void Test1051() {
        System.out.println("LocalDateTime.now().getHour() = " + LocalDateTime.now().getHour());

    }

    @Test
    public void Test1057() {
        String ds = "2021-02-28";
        LocalDate end = LocalDate.parse(ds, DateTimeFormatter.ofPattern(yyyyMMdd));
        Integer totalDays = end.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
        System.out.println("totalDays = " + totalDays);
        System.out.println("end.getMonthValue() = " + end.getMonthValue());
    }

    @Test
    public void Test1065() {
        String ds = "2021-02-28";
        LocalDate end = LocalDate.parse(ds, DateTimeFormatter.ofPattern(yyyyMMdd));
        LocalDate end1 = end.minusMonths(1);
        System.out.println("end1 = " + end1);
    }

    boolean isFirstDayOfDimension(String dsEnd, String dimension) {
        LocalDate date = LocalDate.parse(dsEnd, DateTimeFormatter.ofPattern(DATE_FORMAT));
        if (dimension.equals(DIMENSION_YEAR)) {
            return date.getDayOfYear() == 1;
        }else if (dimension.equals(DIMENSION_MONTH)) {
            return date.getDayOfMonth() == 1;
        }else if (dimension.equals(DIMENSION_WEEK)) {
            return date.getDayOfWeek().getValue() == 1;
        }else {
            return date.equals(LocalDate.now());
        }
    }
    @Test
    public void Test1088() {
        String ds = "2021-02-22";
        System.out.println("isFirstDayOfDimension(ds, \"week\") = " + isFirstDayOfDimension(ds, "week"));
    }
    
    @Test
    public void Test1094() {
        String ds = "2021-02-13";
        System.out.println("getFirstDayOfDimension(ds,\"week\") = " + getFirstDayOfDimension(ds, "week"));

    }

    public static LocalDate getFirstDayOfDimension(String dsEnd, String dimension) {
        LocalDate date = LocalDate.parse(dsEnd, DateTimeFormatter.ofPattern(DATE_FORMAT));
        if(dimension.equals(DIMENSION_YEAR)){
            return LocalDate.of(date.getYear(), 1, 1);
        }else if (dimension.equals(DIMENSION_MONTH)) {
            return LocalDate.of(date.getYear(), date.getMonth(), 1);
        }else if (dimension.equals(DIMENSION_WEEK)) {
            return date.minusDays(date.getDayOfWeek().getValue()-1);
        }else {
            return null;
        }
    }

    @Test
    public void Test1114() {
        String ds = "2021-02-13";
        LocalDate date = LocalDate.parse(ds, DateTimeFormatter.ofPattern(DATE_FORMAT));
        System.out.println("date.getDayOfWeek() = " + date.getDayOfWeek());
        System.out.println("date.getDayOfWeek().getValue() = " + date.getDayOfWeek().getValue());

    }

    public static boolean isSameDimension(String ds1, String ds2, String dimension) {
        LocalDate date1 = LocalDate.parse(ds1,DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDate date2 = LocalDate.parse(ds2,DateTimeFormatter.ofPattern(DATE_FORMAT));
        switch (dimension) {
            case DIMENSION_YEAR:
                return date1.getYear() == date2.getYear();
            case DIMENSION_SEASON:
                return date1.getYear() == date2.getYear() && (date1.getMonthValue() - 1) / 3 == (date2.getMonthValue() - 1) / 3;
            case DIMENSION_MONTH:
                return date1.getYear() == date2.getYear() && date1.getMonthValue() == date2.getMonthValue();
            case DIMENSION_WEEK:
                return getFirstDayOfDimension(ds1,DIMENSION_WEEK).equals(getFirstDayOfDimension(ds2,DIMENSION_WEEK));
            default:
                return false;
        }
    }

    public static boolean isSameDimensionNow(String ds1, String dimension) {
        LocalDate date1 = LocalDate.parse(ds1,DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDate date2 = LocalDate.now();
        switch (dimension) {
            case DIMENSION_YEAR:
                return date1.getYear() == date2.getYear();
            case DIMENSION_MONTH:
                return date1.getYear() == date2.getYear() && date1.getMonthValue() == date2.getMonthValue();
            case DIMENSION_WEEK:
                return getFirstDayOfDimension(ds1,DIMENSION_WEEK).equals(getFirstDayOfDimension(date2.toString(),DIMENSION_WEEK));
            default:
                return false;
        }
    }

    @Test
    public void Test1153() {
        String ds1 = "2021-02-21";
        String ds2 = "2021-02-22";

        System.out.println("isSameDimension(ds1, ds2, DIMENSION_WEEK) = " + isSameDimension(ds1, ds2, DIMENSION_WEEK));

    }

    @Test
    public void Test1162() {
        String ds1 = "2021-02-21";
        String ds2 = "2021-02-22";
        LocalDate date1 = LocalDate.parse(ds1,DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDate date2 = LocalDate.parse(ds2,DateTimeFormatter.ofPattern(DATE_FORMAT));
        System.out.println("Period.between(date1,date2).getDays() = " + Period.between(date1, date2).getDays());
        System.out.println("Period.between(date2,date1).getDays() = " + Period.between(date2, date1).getDays());

    }

    @Test
    public void Test1173() {
        String ds1 = "2020-03-01";
        String ds2 = "2020-03-31";
        System.out.println("ds1.substring(0,7) = " + ds1.substring(0, 7));
        LocalDate date1 = LocalDate.parse(ds1,DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDate date2 = LocalDate.parse(ds2,DateTimeFormatter.ofPattern(DATE_FORMAT));
        System.out.println("date1.minusMonths(1).toString() = " + date1.minusMonths(1).toString());
        System.out.println("date2.minusMonths(1).toString() = " + date2.minusMonths(1).toString());
    }

    public static LocalDate getLastDayOfDimension(String ds, String dimension) {
        LocalDate date = LocalDate.parse(ds, DateTimeFormatter.ofPattern(DATE_FORMAT));
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
            return null;
        }
    }
    
    @Test
    public void Test1197() {
        String ds = "2021-09-17";
        LocalDate d1 = getLastDayOfDimension(ds, DIMENSION_YEAR);
        LocalDate d2 = getLastDayOfDimension(ds, DIMENSION_MONTH);
        LocalDate d3 = getLastDayOfDimension(ds, DIMENSION_WEEK);
        LocalDate d4 = getLastDayOfDimension(ds, DIMENSION_SEASON);
        System.out.println("d1 = " + d1);
        System.out.println("d2 = " + d2);
        System.out.println("d3 = " + d3);
        System.out.println("d4 = " + d4);
    }

    @Test
    public void Test1208() {
        String dsEnd = "2021-03-01";
        LocalDate end = DateUtil.getLastDayOfDimension(dsEnd, DIMENSION_YEAR);
        if (end.equals(LocalDate.now())) {
            System.out.println("1end.minusDays(1).toString() = " + end.minusDays(1).toString());
        } else {
            System.out.println("2end.minusDays(1).toString() = " + end.toString());
        }

    }

    @Test
    public void Test1220() {
        /*TraceWatch traceWatch = new TraceWatch();

        traceWatch.start("function1");
        TimeUnit.SECONDS.sleep(1); // 模拟业务代码
        traceWatch.stop();

        traceWatch.start("function2");
        TimeUnit.SECONDS.sleep(1); // 模拟业务代码
        traceWatch.stop();

        traceWatch.record("function1", 1); // 直接记录耗时

        System.out.println(JSON.toJSONString(traceWatch.getTaskMap()));*/

    }

    @Test
    public void Test1239() {
        String dsStart = "2021-02-28";
        String dsEnd = "2021-04-01";
        LocalDate ds = LocalDate.parse(dsStart, DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDate de = LocalDate.parse(dsEnd, DateTimeFormatter.ofPattern(DATE_FORMAT));
        System.out.println("ds.plusMonths(2).toString() = " + ds.plusMonths(1).toString());
        System.out.println("de.minusMonths(3).toString() = " + de.minusMonths(3).toString());
        System.out.println("isSameDimension(dsStart, dsEnd, DIMENSION_SEASON) = " + isSameDimension(dsStart, dsEnd, DIMENSION_SEASON));

    }

    public static boolean isOfflineDataValidByDimensionNow(String ds1, String dimension, int hourTimeLimit) {
        LocalDate date1 = LocalDate.parse(ds1,DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDateTime date2 = LocalDateTime.now();
        switch (dimension) {
            case DIMENSION_YEAR:
                return date2.getDayOfYear() > 2 || (date2.getDayOfYear() == 2 && date2.getHour() >= hourTimeLimit);
            case DIMENSION_SEASON:
                return date2.getMonthValue() % 3 > 1 || (date2.getMonthValue() % 3 == 1 && (date2.getDayOfMonth() > 2 || (date2.getDayOfMonth() == 2 && date2.getHour() >= hourTimeLimit)));
            case DIMENSION_MONTH:
                return date2.getDayOfMonth() > 2 || (date2.getDayOfMonth() == 2 && date2.getHour() >= hourTimeLimit);
            case DIMENSION_WEEK:
                return date2.getDayOfWeek().getValue() > 2 || (date2.getDayOfWeek().getValue() == 2 && date2.getHour() >= hourTimeLimit);
            case DIMENSION_DAY:
                int days = java.time.Period.between(date1, date2.toLocalDate()).getDays();
                return days > 1 || (days == 1 && date2.getHour() >= hourTimeLimit);
            default:
                return false;
        }

    }

    public static boolean isOfflineDataValidByDimensionNow(String ds1, String now, String dimension, int hourTimeLimit) {
        LocalDate date1 = LocalDate.parse(ds1,DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDateTime date2 = LocalDateTime.parse(now,DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        switch (dimension) {
            case DIMENSION_YEAR:
                return date2.getDayOfYear() > 2 || (date2.getDayOfYear() == 2 && date2.getHour() >= hourTimeLimit);
            case DIMENSION_SEASON:
                return date2.getMonthValue() % 3 > 1 || (date2.getMonthValue() % 3 == 1 && (date2.getDayOfMonth() > 2 || (date2.getDayOfMonth() == 2 && date2.getHour() >= hourTimeLimit)));
            case DIMENSION_MONTH:
                return date2.getDayOfMonth() > 2 || (date2.getDayOfMonth() == 2 && date2.getHour() >= hourTimeLimit);
            case DIMENSION_WEEK:
                return date2.getDayOfWeek().getValue() > 2 || (date2.getDayOfWeek().getValue() == 2 && date2.getHour() >= hourTimeLimit);
            case DIMENSION_DAY:
                int days = java.time.Period.between(date1, date2.toLocalDate()).getDays();
                return days > 1 || (days == 1 && date2.getHour() >= hourTimeLimit);
            default:
                return false;
        }

    }
    
    @Test
    public void Test1274() {
        String d1 = "2021-03-31";
        String now = "2021-05-03 10:00:00";
        System.out.println("isOfflineDataValidByDimensionNow(d1, now, DIMENSION_SEASON, 10) = " + isOfflineDataValidByDimensionNow(d1, now, DIMENSION_SEASON, 10));

    }

    public static LocalDate getNextDayOfDimension(String ds, String dimension, int direction) {
        LocalDate date = LocalDate.parse(ds, DateTimeFormatter.ofPattern(DATE_FORMAT));
        if(dimension.equals(DIMENSION_YEAR)){
            return date.with(TemporalAdjusters.lastDayOfYear());
        }else if (dimension.equals(DIMENSION_SEASON)) {
            int monthLeft = date.getMonthValue() % 3;
            int monthDiff = monthLeft > 0 ? 3 - monthLeft : 0;
            return date.plusMonths(monthDiff).with(TemporalAdjusters.lastDayOfMonth());
        }else if (dimension.equals(DIMENSION_MONTH)) {
            return direction > 0 ? date.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth()) : date.with(TemporalAdjusters.lastDayOfMonth());
        }else if (dimension.equals(DIMENSION_WEEK)) {
            return direction > 0 ? date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)) : date.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
        }else {
            return null;
        }
    }

    @Test
    public void Test1309() {
        String ds = "2021-04-01";
        System.out.println("getNextDayOfDimension(ds,DIMENSION_MONTH,1) = " + getNextDayOfDimension(ds, DIMENSION_MONTH, 1));
        System.out.println("getNextDayOfDimension(ds, DIMENSION_MONTH, -1) = " + getNextDayOfDimension(ds, DIMENSION_MONTH, -1));
        System.out.println("getNextDayOfDimension(ds, DIMENSION_WEEK, 1) = " + getNextDayOfDimension(ds, DIMENSION_WEEK, 1));
        System.out.println("getNextDayOfDimension(ds, DIMENSION_WEEK, -1) = " + getNextDayOfDimension(ds, DIMENSION_WEEK, -1));
    }

    @Test
    public void Test1335() {
        String  ds = "2021-03-11";
        System.out.println("1ds = " + ds);
        ds = getFirstDayOfDimension(ds, DIMENSION_WEEK).toString();
        System.out.println("2ds = " + ds);

        System.out.println("ds.substring(0,7) = " + ds.substring(0, 7));

    }

    @Test
    public void Test1347() {
        String ds1 = "2021-03-01";
        String ds2 = "2021-03-14";
        LocalDate date1 = LocalDate.parse(ds1, DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDate date2 = LocalDate.parse(ds2, DateTimeFormatter.ofPattern(DATE_FORMAT));
        long dayDiff = date2.getLong(ChronoField.EPOCH_DAY) - date1.getLong(ChronoField.EPOCH_DAY);
        System.out.println("dayDiff = " + dayDiff);
        System.out.println("(dayDiff + 1) / 7 = " + (dayDiff + 1) / 7);

    }

    @Test
    public void Test1358() {
//        int a = 10;
        String str = "w2";
        switch (str) {
            case "w1":
                int a = 20;
                return;
            case "w2":
                a = 30;
                System.out.println("a = " + a);
                return;
        }

    }

    @Test
    public void Test1375() {
        Date now = new Date();
        List<Date> list = new ArrayList<>();
        list.add(now);
        System.out.println("list = " + list);
        System.out.println("JSON.toJSONString(list) = " + JSON.toJSONString(list));

    }

    public class DsVo {

        /** 环比开始日期*/
        private String momStart;
        /** 环比结束日期*/
        private String momEnd;
        /** 同比开始日期*/
        private String yoyStart;
        /** 同比结束日期*/
        private String yoyEnd;
        /** 是否完整周期比对*/
        private boolean totalFlag;

        public String getMomStart() {
            return momStart;
        }

        public void setMomStart(String momStart) {
            this.momStart = momStart;
        }

        public String getMomEnd() {
            return momEnd;
        }

        public void setMomEnd(String momEnd) {
            this.momEnd = momEnd;
        }

        public String getYoyStart() {
            return yoyStart;
        }

        public void setYoyStart(String yoyStart) {
            this.yoyStart = yoyStart;
        }

        public String getYoyEnd() {
            return yoyEnd;
        }

        public void setYoyEnd(String yoyEnd) {
            this.yoyEnd = yoyEnd;
        }

        public boolean isTotalFlag() {
            return totalFlag;
        }

        public void setTotalFlag(boolean totalFlag) {
            this.totalFlag = totalFlag;
        }

        public String getDimension() {
            return dimension;
        }

        public void setDimension(String dimension) {
            this.dimension = dimension;
        }

        /*private String tipFlag="n";
         *//** 环比开始日期*//*
    private String lastDsStart;
    *//** 环比结束日期*//*
    private String lastDsEnd;
    *//** 整维度展示 默认是非整维度，环比计算采用日表进行累加计算*//*
    private String dimensionFlag = "n";*/
        /** 维度 比如： day, week, month, season, year*/
        private String dimension;
    }

    public DsVo queryMomYoyPeriod(String ds, String dimension) {
        if (DIMENSION_DAY.equals(dimension)) {
            log.info(">>> queryMomYoyPeriod not support day, ds: {}", ds);
            return null;
        }
        DsVo vo = new DsVo();
        vo.setTotalFlag(DateTimeUtil.isLastDayOfDimension(ds, dimension) ? true : false);
        vo.setMomStart(DateTimeUtil.getPreviousDayOfDimension(DateTimeUtil.getFirstDayOfDimensionV2(ds, dimension).toString(), dimension).toString());
        vo.setYoyStart(DateTimeUtil.getFirstDayOfDimensionV2(DateTimeUtil.getPreviousDayOfDimension(ds,DateTimeUtil.DIMENSION_YEAR).toString(),dimension).toString());
        if (vo.isTotalFlag()) {
            vo.setMomEnd(DateTimeUtil.getLastDayOfDimension(vo.getMomStart(), dimension).toString());
            vo.setYoyEnd(DateTimeUtil.getLastDayOfDimension(vo.getYoyStart(), dimension).toString());
        } else {
            vo.setMomEnd(DateTimeUtil.getFirstDayOfDimensionV2(vo.getMomStart(), dimension).plusDays(DateTimeUtil.getElapsedDaysOfDimension(ds, dimension) - 1).toString());
            vo.setYoyEnd(DateTimeUtil.getFirstDayOfDimensionV2(vo.getYoyStart(), dimension).plusDays(DateTimeUtil.getElapsedDaysOfDimension(ds, dimension) - 1).toString());
        }
        return vo;
    }

    @Test
    public void Test1471() {
        String ds = "2021-06-11";
        String dimension = "season";
        DsVo dsVo = queryMomYoyPeriod(ds,dimension);
        System.out.println("dsVo = " + JSON.toJSONString(dsVo));

        System.out.println("DateTimeUtil.getElapsedDaysOfDimension(ds, dimension) = " + DateTimeUtil.getElapsedDaysOfDimension(ds, dimension));

        System.out.println("Math.abs(ChronoUnit.DAYS.between(getFirstDayOfDimensionV2(ds, DIMENSION_SEASON),LocalDate.parse(ds, DateTimeFormatter.ofPattern(yyyyMMdd)))) = "
                + Math.abs(ChronoUnit.DAYS.between(getFirstDayOfDimensionV2(ds, DIMENSION_SEASON), LocalDate.parse(ds, DateTimeFormatter.ofPattern(yyyyMMdd)))));

        System.out.println("DateTimeUtil.getElapsedDaysOfDimension(ds, DateTimeUtil.DIMENSION_SEASON) = " + DateTimeUtil.getElapsedDaysOfDimension(ds, DateTimeUtil.DIMENSION_SEASON));

    }

    @Test
    public void Test1489() {
        LocalDate now = LocalDate.now();
        LocalDate yesterday = now.minusDays(1);
        System.out.println("Period.between(now, yesterday).getDays() = " + Period.between(now, yesterday).getDays());
        System.out.println("Period.between(yesterday, now).getDays() = " + Period.between(yesterday, now).getDays());

    }

    @Test
    public void Test1498() {
        Integer a = getNowMinuteByZone("UTC+8");
        System.out.println(a);

        Integer b = getNowMinuteByZone("UTC-4");
        System.out.println(b);

    }

    public static Integer getNowMinuteByZone(String timeZone){
        LocalDateTime now = LocalDateTime.now(ZoneId.of(timeZone));
        return now.getHour() * 60 + now.getMinute();
    }

    public static String getTimeFromSlotNum(int slotNum) {
        return slotNum / 60 + ":" + slotNum % 60;
    }

    @Test
    public void Test1517() {
        int slotNum = 55;
        System.out.println("getTimeFromSlotNum(slotNum) = " + getTimeFromSlotNum(slotNum));
    }

    @Test
    public void Test1523() {
        String timeZone = "UTC+9";
        LocalDateTime change = LocalDateTime.now(ZoneId.of(timeZone));
        Integer minutes =  change.getHour() * 60 + change.getMinute();

        LocalDateTime now = LocalDateTime.now();
        System.out.println("change = " + change);
        System.out.println("now = " + now);
        System.out.println("minutes = " + minutes);

    }

    @Test
    public void Test1536() {
        System.out.println("LocalDate.now() = " + LocalDate.now());
        System.out.println("LocalDate.now().minusDays(2).toString() = " + LocalDate.now().minusDays(2).toString());

    }

    @Test
    public void Test1543() {
        LocalDate s = LocalDate.now();
        System.out.println("DateUtils.daysBetweenPeriod(s.toString(),s.toString()) = " + DateUtils.daysBetweenPeriod(s.minusDays(2).toString(), s.toString()));

    }

    @Test
    public void Test1550() {
        String ds = "2021-10-10";
        System.out.println("getYesterdayByDs(ds, true) = " + getYesterdayByDs(ds, true));
        System.out.println("getYesterdayByDs(ds,false) = " + getYesterdayByDs(ds, false));

        String ds1 = getYesterdayByDs(ds,true).toString();
        LocalDate ds2 ;
        ds2 = (LocalDate) getYesterdayByDs(ds, true);
        System.out.println("ds1 = " + ds1);
        System.out.println("ds2 = " + ds2);

    }

    public static Object getYesterdayByDs(String ds, boolean returnFlag) {
        LocalDate date = LocalDate.parse(ds, DateTimeFormatter.ofPattern(DATE_FORMAT));
        return returnFlag ? date.minusDays(1) : date.minusDays(1).toString();
    }

    @Test
    public void Test1569() {
        String dsS = "2021-07-01";
        String dsE = "2021-07-08";
        LocalDate dsStart = LocalDate.parse(dsS, DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDate dsEnd = LocalDate.parse(dsE, DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDate momEnd = dsStart.minusDays(1);
//        LocalDate momStart = momEnd.minusDays(DateTimeUtil.getElapsedDaysOfDimension(dsEnd.toString(), "month")-1);
        LocalDate momStart = momEnd.minusDays(DateTimeUtil.getElapsedDaysOfDimension(dsEnd.toString(), "season")-1);
        System.out.println("momStart = " + momStart);
        System.out.println("momEnd = " + momEnd);

    }

    @Test
    public void Test1583() {
        String dsS = "2020-01-01";
        String dsE = "2020-03-31";
        String dimension = "season";
        LocalDate dsStart = LocalDate.parse(dsS, DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDate dsEnd = LocalDate.parse(dsE, DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDate yoyStart = DateTimeUtil.getFirstDayOfDimensionV2(DateTimeUtil.getPreviousDayOfDimension(dsStart.toString(),DIMENSION_YEAR).toString(),dimension);
        LocalDate yoyEnd = yoyStart.plusDays(DateTimeUtil.getElapsedDaysOfDimension(dsEnd.toString(), dimension) - 1);
        System.out.println("yoyStart = " + yoyStart);
        System.out.println("yoyEnd = " + yoyEnd);
    }

    @Test
    public void Test1596() {
        String dsS = "2020-03-29";
        String dsE = "2020-03-31";
        LocalDate dsStart = LocalDate.parse(dsS, DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDate dsEnd = LocalDate.parse(dsE, DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDate momEnd = dsStart.minusDays(1);
        LocalDate momStart = momEnd.minusDays(Math.abs(ChronoUnit.DAYS.between(dsStart,dsEnd)));

        System.out.println("momStart = " + momStart);
        System.out.println("momEnd = " + momEnd);
    }

    @Test
    public void Test1609() {
        String dsS = "2020-02-27";
        LocalDate dsStart = LocalDate.parse(dsS, DateTimeFormatter.ofPattern(DATE_FORMAT));
        System.out.println("dsStart.minusYears(1) = " + dsStart.minusYears(1));
    }

    @Test
    public void Test1616() {
        String dsS = "2020-03-21";
        String dsE = "2020-05-22";
        LocalDate dsStart = LocalDate.parse(dsS, DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDate dsEnd = LocalDate.parse(dsE, DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDate monthDsStart = DateTimeUtil.getFirstDayOfDimensionV2(dsStart.toString(), DIMENSION_MONTH);
        LocalDate monthDsEnd = DateTimeUtil.getLastDayOfDimension(dsEnd.toString(), DIMENSION_MONTH);

        LocalDate momEnd = monthDsStart.minusDays(1);
        LocalDate momStart = DateTimeUtil.getFirstDayOfDimensionV2(momEnd.minusMonths(Math.abs(ChronoUnit.MONTHS.between(monthDsStart,monthDsEnd))).toString(), DIMENSION_MONTH);


        System.out.println("monthDsStart = " + monthDsStart);
        System.out.println("monthDsEnd = " + monthDsEnd);
        System.out.println("momStart = " + momStart);
        System.out.println("momEnd = " + momEnd);


    }

    @Test
    public void Test1640() {
        String dsS = "2020-02-28";
        String dsE = "2020-04-15";
        LocalDate dsStart = LocalDate.parse(dsS, DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDate dsEnd = LocalDate.parse(dsE, DateTimeFormatter.ofPattern(DATE_FORMAT));

        LocalDate weekDsStart = DateTimeUtil.getFirstDayOfDimensionV2(dsStart.toString(), DIMENSION_WEEK);
        LocalDate weekDsEnd = DateTimeUtil.getLastDayOfDimension(dsEnd.toString(), DIMENSION_WEEK);

        LocalDate momEnd = weekDsStart.minusDays(1);
        LocalDate momStart = momEnd.minusDays(Math.abs(ChronoUnit.DAYS.between(weekDsStart,weekDsEnd)));

        LocalDate minusYear = dsStart.minusYears(1);
        System.out.println("minusYear = " + minusYear);

        LocalDate yoyStart = DateTimeUtil.getFirstDayOfDimensionV2(dsStart.minusYears(1).toString(), DIMENSION_WEEK);
        LocalDate yoyEnd = yoyStart.plusDays(Math.abs(ChronoUnit.DAYS.between(weekDsStart,weekDsEnd)));

        System.out.println("weekDsStart = " + weekDsStart);
        System.out.println("weekDsEnd = " + weekDsEnd);
        System.out.println("momStart = " + momStart);
        System.out.println("momEnd = " + momEnd);
        System.out.println("yoyStart = " + yoyStart);
        System.out.println("yoyEnd = " + yoyEnd);

    }

    @Test
    public void Test1665() {
        String dsS = "2019-12-28";
        String dsE = "2021-04-15";
        LocalDate dsStart = LocalDate.parse(dsS, DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDate dsEnd = LocalDate.parse(dsE, DateTimeFormatter.ofPattern(DATE_FORMAT));

        LocalDate yearDsStart = DateTimeUtil.getFirstDayOfDimensionV2(dsStart.toString(), DIMENSION_YEAR);
        LocalDate yearDsEnd = DateTimeUtil.getLastDayOfDimension(dsEnd.toString(), DIMENSION_YEAR);

        LocalDate momEnd = yearDsStart.minusDays(1);
        LocalDate momStart = DateTimeUtil.getFirstDayOfDimensionV2(momEnd.minusYears(Math.abs(ChronoUnit.YEARS.between(yearDsStart,yearDsEnd))).toString(), DIMENSION_YEAR);

        System.out.println("yearDsStart = " + yearDsStart);
        System.out.println("yearDsEnd = " + yearDsEnd);
        System.out.println("momStart = " + momStart);
        System.out.println("momEnd = " + momEnd);

    }


}
