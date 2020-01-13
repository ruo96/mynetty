package com.wrh.datetime.utils;


import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 类DateUtils.java的实现描述：时间帮助类
 *
 */
public class DateUtils {

    /** yyyy-MM-dd HH:mm:ss **/
    public static final String DATE_PATTERN_LONG = "yyyy-MM-dd HH:mm:ss";
    /** yyyyMMdd **/
    public static final String DATE_COMPRESS_PATTERN = "yyyyMMdd";

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
        long minute = diff / 1000 / 60;

        return minute;
    }

    /**
     * @param timeLong   获取时间戳转换成分钟对应hbase的列qualifier,范围minute0 到 minute1440
     * @return
     */
    public static String getQualifierByMinute(Long timeLong) {
        Long minute = getMinuteByDatetime(timeLong);
        return "minute"+ minute;
    }


}
