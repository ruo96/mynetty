package com.wrh.TimeCalculate;

import com.alibaba.fastjson.JSON;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.wrh.utils.DateUtils;
import com.wrh.utils.RowKeyHashUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

        LocalDate day =  LocalDate.parse("2020-05-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(day);
        System.out.println(day.plusMonths(1));
        System.out.println(day.minusMonths(1));
    }

    @Test
    public void Test21() {
        LocalDate ds = LocalDate.now();
        System.out.println(ds.toString());
    }

    public static boolean isFirstDayOfDimension(String dsEnd, String dimension) {
        LocalDate date = LocalDate.parse(dsEnd, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (dimension.equals("year")) {
            return date.getDayOfYear() == 1;
        }else if (dimension.equals("month")) {
            return date.getDayOfMonth() == 1;
        }else {
            return true;
        }
    }

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

    public static LocalDate getFirstDayOfDimension(String dsEnd, String dimension) {
        LocalDate date = LocalDate.parse(dsEnd, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if(dimension.equals("year")){
            return LocalDate.of(date.getYear(), 1, 1);
        }else if (dimension.equals("month")) {
            return LocalDate.of(date.getYear(), date.getMonth(), 1);
        }else {
            return null;
        }
    }

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
    public void Test26() {
        LocalDate now = LocalDate.now();
        int i = now.getDayOfYear();
        System.out.println(i);
    }

    @Test
    public void Test27() {
        LocalDate now = LocalDate.now();
        System.out.println(now.getDayOfYear());
        LocalDate ds = LocalDate.of(2020,1,1);
        System.out.println(ds.minusMonths(1));
    }


}
