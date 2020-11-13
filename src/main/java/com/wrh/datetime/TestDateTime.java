package com.wrh.datetime;

import com.wrh.datetime.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:42 2018/12/13 0013
 * @Modified By:
 */
@Slf4j
public class TestDateTime {
    public static void main(String[] args) {
        Long beforeStamp = System.currentTimeMillis() - 100000000;

        String beforeStr = Long.toString(beforeStamp);
        System.out.println("beforeStr: " + beforeStr);

        /*如果判断某个日期和现在相差*/
        log.info("now : {}", LocalDate.now());

/*        Instant timestamp = Instant.now();
        *//*比较两个日期的时间间隔*//*

        Period period = Period.between(LocalDate.now(),LocalDate.parse(timestamp.toString()));
        log.info("相差:{}" , period.getDays());*/

        System.out.println("-------------------3 methods-----------------------");
        System.out.println(System.currentTimeMillis());
        System.out.println(Calendar.getInstance().getTimeInMillis());
        System.out.println(Clock.systemDefaultZone().millis());


        System.out.println(LocalDateTime.now());


        String a = "1a";
        int i = Integer.valueOf(a);
        System.out.println("i is : " + i);





    }

    /**
     * 根据timestamp获取日期格式yyyyMMdd
     */
    @Test
    public void Test() {
        Long time = System.currentTimeMillis();
        String ds = DateUtils.getYmdByLongDate(time);
        log.info("当前日期： {}", ds);
    }

    @Test
    public void Test1() {
        Long time = System.currentTimeMillis();
        String minute = DateUtils.getQualifierByMinute(time);
        System.out.println(minute);
        int a = Integer.parseInt(minute.substring(6)) / 60;
        System.out.println(a);

    }

    /**设置格式化模板**/
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSS");

    /**设置日期时区常量**/
    public static final ZoneId CHINA_ZONE_ID = ZoneId.systemDefault();
    /**
     * date转DateTime
     */
    @Test
    public void Test75() {
        Date date = new Date();
        LocalDateTime dateTime = date.toInstant().atZone(CHINA_ZONE_ID).toLocalDateTime();
        System.out.println(dateTime);

    }

    /**
     * LocalDate/LocalDateTime转Date
     */
    @Test
    public void Test92() {
        // LocalDate
        LocalDate localDate = LocalDate.now();
        Date d1 = Date.from(localDate.atStartOfDay(CHINA_ZONE_ID).toInstant());
        System.out.println(d1);

        // LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.now();
        Date d2 = Date.from(localDateTime.atZone(CHINA_ZONE_ID).toInstant());
        System.out.println(d2);

    }

    /**
     * 日期格式化
     */
    @Test
    public void Test109() {
        System.out.println(LocalDateTime.now().format(DATE_TIME_FORMATTER));
    }
    
    @Test
    public void Test117() {
        // 当月第一天
        LocalDateTime dateTime = LocalDateTime.of(2019,07,03,12,12,22);
        dateTime = dateTime.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(dateTime);
        // 当月最后一天
        dateTime = dateTime.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(dateTime);

        //当月的第几天
        dateTime = LocalDateTime.now();
        int dayOfMonth = dateTime.getDayOfMonth();
        System.out.println(dayOfMonth);
        // 当前周的第几天
        int dayOfWeek = dateTime.getDayOfWeek().getValue();
        System.out.println(dayOfWeek);
        
    }

    @Test
    public void Test138() {
        LocalDate now = LocalDate.now();

        LocalDate tomorrow = now.plusDays(1);

        List<String> ds = DateUtils.getDsListFromPeriod(now.toString(), tomorrow.toString());

        System.out.println(ds);

        String dsStr = "2020-11-03";
        LocalDate date = LocalDate.parse(dsStr);
        System.out.println(date);

    }
}
