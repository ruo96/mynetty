package com.wrh.utils;


import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.joda.time.DateTime;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 类DateUtils.java的实现描述：时间帮助类
 *
 * @author lishi 2019年12月23日 下午8:44:05
 */
@Slf4j
public class DateUtils {

    /** yyyy-MM-dd HH:mm:ss **/
    public static final String DATE_PATTERN_LONG = "yyyy-MM-dd HH:mm:ss";
    /** yyyyMMdd **/
    public static final String DATE_COMPRESS_PATTERN = "yyyyMMdd";
    /** yyyy-MM-dd **/
    public static final String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 获取未来 第past天的日期
     * @param past
     * @return
     */
    public static String getFutureDate(Long timestamp,int past) {
        DateTime dt = new DateTime(timestamp);
        return dt.plusDays(past).toString(DATE_COMPRESS_PATTERN);
    }

    /**
     * 根据偏移量获取yyMMdd格式日期
     *
     * @param timestamp 时间偏移量
     * @return
     */
    public static String getYmdhmsByLongDate(Long timestamp) {
        DateTime dt = new DateTime(timestamp);
        return dt.toString(DATE_PATTERN_LONG);
    }

    /**
     * 根据偏移量获取yy-MM-dd hh:mm:ss格式日期
     *
     * @param timestamp 时间偏移量
     * @return
     */
    public static String getYmdByLongDate(Long timestamp) {
        DateTime dt = new DateTime(timestamp);
        return dt.toString(DATE_COMPRESS_PATTERN);
    }

    /**
     * 获取传入时间戳的 年月日 字符串 20160624
     *
     * @param serverTime
     * @return
     */
    public static String getDateYMD(String serverTime) {

        SimpleDateFormat formatYMD = new SimpleDateFormat(DATE_COMPRESS_PATTERN);
        String dataYMD = formatYMD.format(serverTime);
        return dataYMD;
    }

    /**
     * @param timeLong   当前需要计算槽位的时间戳
     * @return
     */
    public static Long getMinuteByDatetime(Long timeLong) {
        //取得传入时间当天00:00:00时的时间戳
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(timeLong);//设置服务器接受时间
        c1.set(Calendar.HOUR_OF_DAY, 0);//调整小时
        c1.set(Calendar.MINUTE, 0);//调整分钟
        c1.set(Calendar.SECOND, 0);//调整秒
        long day0time = c1.getTime().getTime();

        //算出当前时间与今天凌晨时间差值,毫秒
        long diff = timeLong - day0time;

        if (diff < 0) {
            diff = 0;
        }

        //将时差换算为分钟，从0开始,1440结尾
//        long minute = diff / 1000 / 60;
        Long minute = Math.round( diff * 1.0  / 60000);

        return minute;
    }

    @Test
    public void Test() {
        /*for (int i1 = 0; i1 < 10; i1++) {
            Integer i = RandomUtils.nextInt(0,1000000);
            Integer j = i %10;
            System.out.println(i +"===" + j);
        }*/

        Map<String,String> map = new HashMap<>();
        map.put("key1","1");
        map.put("key2","2");
        map.put("key3","");
        map.put("key4","abb");

        List<String> list = Lists.newArrayList();
        map.entrySet().stream().filter(e -> !StringUtils.isNotEmpty(e.getValue()) || !StringUtils.isNumeric(e.getValue())).map(e->list.add(e.getKey())).count();

        System.out.println(map);
        System.out.println(list);



        Map<String,String> newMap = new HashMap<>();
        newMap.entrySet().forEach(e-> {
            map.put(e.getKey(),String.valueOf(e.getValue()));});
        System.out.println(map);

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);
        list1.add(6);

        List<Integer> list3 = list1.stream().filter(e-> e>3).collect(Collectors.toList());
        log.info(">>> list1:{}",list1);
        log.info(">>> list3:{}",list3);

    }

    /**
     * @param timeLong   获取时间戳转换成分钟对应hbase的列qualifier,范围minute0 到 minute1440
     * @return
     */
    public static String getQualifierByMinute(Long timeLong) {
        Long minute = getMinuteByDatetime(timeLong);
        return "minute"+ minute;
    }

    /**
     * @param timeLong   获取时间戳转换成分钟对应hbase的列qualifier,范围minute0 到 minute1440
     * @return
     */
    public static String getMinute(Long timeLong) {
        Long minute = getMinuteByDatetime(timeLong);
        return minute.toString();
    }

    public static Integer getDayOfMonth(String ds){
        LocalDate end = LocalDate.parse(ds, DateTimeFormatter.ofPattern(DATE_PATTERN));
        return end.getDayOfMonth();
    }

    public static Integer getTotalDayOfMonth(String ds){
        LocalDate end = LocalDate.parse(ds, DateTimeFormatter.ofPattern(DATE_PATTERN));
        return end.getMonth().length(end.isLeapYear());
    }

    public static Integer getNowMinute(){
        LocalDateTime now = LocalDateTime.now();

        return now.getHour() * 60 + now.getMinute();
    }


}
