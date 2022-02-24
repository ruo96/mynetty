package com.wrh.datetime;

import com.wrh.datetime.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.wrh.collection.map.DateUtil.DATE_FORMAT;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:42 2018/12/13 0013
 * @Modified By:
 */
@Slf4j
public class TestDateTime {

    public static final String DIMENSION_YEAR = "year";
    public static final String DIMENSION_MONTH = "month";
    public static final String DIMENSION_SEASON = "season";
    public static final String DIMENSION_WEEK = "week";
    public static final String DIMENSION_DAY = "day";

    public static final String yyyyMMdd = "yyyy-MM-dd";

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

    @Test
    public void Test155() {
        String ds = "2020-11-12";
        LocalDate futureEnd = com.wrh.utils.DateUtils.getAfterNDaysByDs(ds, 60);
        String futureDs = futureEnd.isBefore(LocalDate.now()) ? futureEnd.toString() : LocalDate.now().minusDays(1).toString();
        System.out.println(futureDs);



    }

    @Test
    public void Test166() {
        String ds1 = "2020-01-01";
        String ds2 = "2020-05-01";
        LocalDate date1 = LocalDate.parse(ds1,DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDate date2 = LocalDate.parse(ds2,DateTimeFormatter.ofPattern(DATE_FORMAT));
        System.out.println(date1.toString() + "----" + date2.toString());
        handleDate(date1, date2);


        System.out.println(date1.toString() + "----" + date2.toString());


    }

    private void handleDate(LocalDate date1, LocalDate date2) {
        if(date2.isAfter(date1)){
            System.out.println("after");
            date1 = date2;
        }
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
            return null;
        }
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
            return null;
        }
    }

    public static long daysBetweenPeriod(String start, String end){
        LocalDate d1 = LocalDate.parse(start,DateTimeFormatter.ofPattern(yyyyMMdd));
        LocalDate d2 = LocalDate.parse(end,DateTimeFormatter.ofPattern(yyyyMMdd));
        return Math.abs(ChronoUnit.DAYS.between(d1,d2));
    }

    public static int getDayOfSeason(String dsEnd) {
        LocalDate date = LocalDate.parse(dsEnd, DateTimeFormatter.ofPattern(yyyyMMdd));
        return (int) Math.abs(ChronoUnit.DAYS.between(getFirstDayOfDimensionV2(dsEnd, DIMENSION_SEASON), getLastDayOfDimension(dsEnd, DIMENSION_SEASON))) + 1;
    }

    public static int getElapsedDaysOfSeason(String dsEnd) {
        LocalDate d1 = LocalDate.parse(dsEnd,DateTimeFormatter.ofPattern(yyyyMMdd));
        return (int)Math.abs(ChronoUnit.DAYS.between(getFirstDayOfDimensionV2(dsEnd, DIMENSION_SEASON),d1)) + 1;
    }

    @Test
    public void Test214() {
        String d1 = "2021-02-12";
        String d2 = "2021-12-12";
        System.out.println("getFirstDayOfDimensionV2(d1,DIMENSION_SEASON) = " + getFirstDayOfDimensionV2(d1, DIMENSION_SEASON));
        System.out.println("getLastDayOfDimension(d1,DIMENSION_SEASON) = " + getLastDayOfDimension(d1, DIMENSION_SEASON));
        System.out.println("daysBetweenPeriod(d1,d2) = " + daysBetweenPeriod(d1, d2));
        System.out.println("getDayOfSeason(d1) = " + getDayOfSeason(d1));
        System.out.println("getDayOfSeason(d2) = " + getDayOfSeason(d2));
        System.out.println("getElapsedDaysOfSeason(d1) = " + getElapsedDaysOfSeason(d1));
        System.out.println("getElapsedDaysOfSeason(d2) = " + getElapsedDaysOfSeason(d2));

    }

    public static boolean isSecondDayOfDimension(String ds, String dimension) {
        LocalDate date = LocalDate.parse(ds, DateTimeFormatter.ofPattern(yyyyMMdd));
        if (dimension.equals(DIMENSION_YEAR)) {
            return date.getDayOfYear() == 2;
        }else if (dimension.equals(DIMENSION_SEASON)) {
            return getElapsedDaysOfSeason(ds) == 2;
        }else if (dimension.equals(DIMENSION_MONTH)) {
            return date.getDayOfMonth() == 2;
        }else {
            return false;
        }
    }

    @Test
    public void Test276() {
        String d1 = "2021-01-12";
        System.out.println("isSecondDayOfDimension(d1,DIMENSION_SEASON) = " + isSecondDayOfDimension(d1, DIMENSION_SEASON));
        String d2 = "2021-10-03";
        System.out.println("isSecondDayOfDimension(d2,DIMENSION_SEASON) = " + isSecondDayOfDimension(d2, DIMENSION_SEASON));

    }

    @Test
    public void Test263() {
        String d1 = "2021-02-12";
        LocalDate d = LocalDate.parse(d1,DateTimeFormatter.ofPattern(yyyyMMdd));

    }

    public static int getCurrentMonthTotalDaysOfSeason(String dsEnd) {
        LocalDate d1 = LocalDate.parse(dsEnd,DateTimeFormatter.ofPattern(yyyyMMdd));
        return (int) Math.abs(ChronoUnit.DAYS.between(getFirstDayOfDimensionV2(dsEnd, DIMENSION_SEASON), getLastDayOfDimension(dsEnd, DIMENSION_MONTH))) + 1;
    }

    @Test
    public void Test297() {
        String ds = "2021-03-12";
        System.out.println("getCurrentMonthTotalDaysOfSeason(ds) = " + getCurrentMonthTotalDaysOfSeason(ds));

    }

    @Test
    public void Test304() {
        System.out.println("LocalDateTime.now().toString() = " + LocalDateTime.now().toString());
        System.out.println("LocalDateTime.now().format(DateTimeFormatter.ofPattern(\"yyyy-MM-dd HH:mm:ss\")) = " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    }

    @Test
    public void Test311() {
        Long time = System.currentTimeMillis();
        String datestr = new Date(time).toString();
        System.out.println("datestr = " + datestr);

    }
    /** 雪花算法  分布式id比较长， long传到前端会有精度损失，因此需要在返回字段long型上面增加 @JsonSerialize(using=ToStringSerializer.class)*/

    @Test
    public void Test318() {
        LocalDate a = LocalDate.of(2021, 1, 31);
        LocalDate b = a.plus(1, ChronoUnit.MONTHS);
        System.out.println("b.toString() = " + b.toString());

    }

    @Test
    public void Test326() {
        LocalDate a = LocalDate.now();
        String b = a.toString();
        System.out.println("b = " + b);

        LocalDateTime now = LocalDateTime.now();
        System.out.println("now.toEpochSecond(ZoneOffset.of(\"+8\")) = " + now.toEpochSecond(ZoneOffset.of("+8")));
        System.out.println("now.toInstant(ZoneOffset.of(\"+8\")).toEpochMilli() = " + now.toInstant(ZoneOffset.of("+8")).toEpochMilli());
        System.out.println("System.currentTimeMillis() = " + System.currentTimeMillis());

    }

    @Test
    public void Test339() {
        LocalDate date = LocalDate.of(2020,2,27);
        LocalDate date1 = date.minusYears(1);
        System.out.println("date1 = " + date1);

        date = LocalDate.of(2020,2,27);
        date1 = date.minusYears(1);
        System.out.println("date1 = " + date1);

    }


}
