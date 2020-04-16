package com.wrh.TimeCalculate;

import com.alibaba.fastjson.JSON;
import com.wrh.utils.DateUtils;
import com.wrh.utils.RowKeyHashUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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


}
