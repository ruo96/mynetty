package com.wrh.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author
 */
public class DateUtilsB {

	public static final String TIME_INTERVAL_SECOND = "second";
	public static final String TIME_INTERVAL_MINUTE = "minute";
	public static final String TIME_INTERVAL_HOUR = "hour";
	public static final String TIME_INTERVAL_DAY = "day";
	public static final String TIME_INTERVAL_WEEK = "week";
	public static final String TIME_INTERVAL_MONTH = "month";
	public static final String TIME_INTERVAL_QUARTER = "quarter";
	public static final String TIME_INTERVAL_YEAR = "year";
	
	public static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static final String PATTERN_DATE = "yyyy-MM-dd";

    public static final String PATTERN_TILL_MINUTE = "yyyy-MM-dd HH:mm";

    private static final ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final DateTimeFormatter MINUTE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static final String YYYY_MM_DD_CN = "yyyy年MM月dd日";

	/**
	 * @param date
	 * @return "yyyy-MM-dd HH:mm:ss"
	 */
    public static String formatDateTimeNormal(Date date) {
        if(date ==null){
            return null;
        }
        LocalDateTime localDateTime = date.toInstant().atZone(DEFAULT_ZONE_ID).toLocalDateTime();
        return localDateTime.format(DATE_TIME_FORMATTER);
    }

    /**
     * @param date
     * @return "yyyy-MM-dd HH:mm"
     */
    public static String formatDateTimeTillMinute(Date date) {
        if(date ==null){
            return null;
        }
        LocalDateTime localDateTime = date.toInstant().atZone(DEFAULT_ZONE_ID).toLocalDateTime();
        return localDateTime.format(MINUTE_FORMATTER);
    }

    /**
     * @param date
     * @return "yyyy-MM-dd"
     */
    public static String formatDateNormal(Date date) {
        if(date ==null){
            return null;
        }
        LocalDateTime localDateTime = date.toInstant().atZone(DEFAULT_ZONE_ID).toLocalDateTime();
        return localDateTime.format(DATE_FORMATTER);
    }

    /**
     * 按默认格式转换日期
     *
     * @param date
     * @return
     */
    public static String parse(Date date) {
        return parse(date, PATTERN);
    }

    /**
     * 按指定格式转换日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String parse(Date date, String pattern) {
        return parse(date, pattern, DEFAULT_ZONE_ID);
    }

    /**
     * 按指定格式和时区转换日期
     *
     * @param date
     * @param pattern
     * @param zoneId
     * @return
     */
    public static String parse(Date date, String pattern, ZoneId zoneId) {
        if (date == null) {
            return "";
        }

        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), zoneId);
        return DateTimeFormatter.ofPattern(pattern).format(localDateTime);
    }

    /**
     * format to "2018-01-01 23:10:01" Date
     */
    public static Date parseNormal(String dateString) {
        return parse(dateString, PATTERN, Date.class);
    }

    /**
     * format to "2018-01-01" Date
     */
    public static Date parseDate(String dateString) {
        return parse(dateString, PATTERN_DATE, Date.class);
    }

    public static Date parseMinute(String string) {
        return parse(string, PATTERN_TILL_MINUTE, Date.class);
    }


    public static Date parse(String dateString, String dateFormat) {
        return parse(dateString, dateFormat, Date.class);
    }

    public static <T extends Date> T parse(String dateString,
                                           String dateFormat, Class<T> targetResultType) {
        if (org.apache.commons.lang.StringUtils.isEmpty(dateString)) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(dateFormat);
        try {
            T t = targetResultType.getConstructor(new Class[] { Long.TYPE })
                    .newInstance(
                            new Object[] { Long.valueOf(df.parse(dateString)
                                    .getTime()) });
            return t;
        } catch (ParseException e) {
            String errorInfo = "cannot use dateformat:" + dateFormat
                    + " parse datestring:" + dateString;
            throw new IllegalArgumentException(errorInfo, e);
        } catch (Exception e) {
            throw new IllegalArgumentException("error targetResultType:"
                    + targetResultType.getName(), e);
        }
    }


    /**
     * 判断 时间格式
     * @param str
     * @return
     */
    public static boolean isNormalDateTime(String str) {
        try {
            DATE_TIME_FORMATTER.parse(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isMinuteTime(String str) {
        try {
            MINUTE_FORMATTER.parse(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }



    public static boolean isDate(String str) {
        try {
            DATE_FORMATTER.parse(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 指定格式化
     *
     * @param str
     * @param pattern
     * @return
     */
    public static boolean isNormalDateTime(String str, String pattern) {
        try {
            SimpleDateFormat simpleDateFormat = (SimpleDateFormat) DateFormat.getDateInstance();
            simpleDateFormat.applyPattern(pattern);
            simpleDateFormat.parse(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否早于当前时间
     * @param date
     * @return
     */
    public static boolean isEarlierNowTime(Date date) {
        return date.getTime() < System.currentTimeMillis();
    }

    /**
     * 判断是否早于今天
     * @param str
     * @return
     */
    public static boolean isEarlierToday(String str) {
        Date input = parse(str, PATTERN_DATE);
        String time = formatDateNormal(new Date());
        Date today = parse(time, PATTERN_DATE);
        return input.before(today);
    }


    /**
     * 日期相加
     * @param date
     * @param dayNum
     * @return
     */
    public static Date addDay(Date date, int dayNum) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(GregorianCalendar.DATE, dayNum);
        return gc.getTime();
    }

    /**
     * 日期比较
     * @param data1
     * @param data2
     * @return
     */
    public static boolean compareTime(Date data1, Date data2) {
        return data1.after(data2);
    }

    /**
     *  yyyy年MM月dd日
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        DateFormat format = new SimpleDateFormat(YYYY_MM_DD_CN);
        return format.format(date);
    }
    
    public static String formatDate(Date date, String format) {
    	if (date == null) {
    		return "";
    	}
    	LocalDateTime localDateTime = date.toInstant().atZone(DEFAULT_ZONE_ID).toLocalDateTime();
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(formatter);
    }

    /**
     * 获取月的最后一天
     * @param month "yyyy-MM"
     * @return
     */
    public static Date lastDayOfMonth(String month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(parse(month, "yyyy-MM"));
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }
    
    /**
     * 获取月的第一天
     * @param month "yyyy-MM"
     * @return
     */
    public static Date firstDayOfMonth(String month) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(parse(month, "yyyy-MM"));
    	cal.set(Calendar.DAY_OF_MONTH,1);
    	return cal.getTime();
    }
    
    /**
     * 获取日期属于月的第几天
     * @param date
     * @return
     */
    public static int dayOfMonth(Date date) {
    	Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }
    
    /**
     * 判断日期是否在某个月份里
     * <pre>
     * isDateInMonth(new Date("2018-11-20"), "2018-11") = true
     * isDateInMonth(new Date("2018-11-20"), "2018-12") = false
     * </pre>
     * @param date
     * @param month
     * @return
     */
    public static boolean isDateInMonth(Date date, String month) {
        return formatDate(date, "yyyy-MM").equals(month);
    }
    
    /**
     * 根据单位返回两个日期的差值
     * <pre>
     * dateDiff(DateUtils.TIME_INTERVAL_YEAR, new Date("2018-01-05"), new Date("2010-08-25")) = 8
     * dateDiff(DateUtils.TIME_INTERVAL_MONTH, new Date("2018-10-05"), new Date("2018-08-25")) = 2
     * dateDiff(DateUtils.TIME_INTERVAL_WEEK, new Date("2018-08-12"), new Date("2018-08-06")) = 1
     * dateDiff(DateUtils.TIME_INTERVAL_DAY, new Date("2018-08-06"), new Date("2018-08-12")) = -6
     * dateDiff(DateUtils.TIME_INTERVAL_HOUR, new Date("2018-08-12 10:12"), new Date("2018-08-12 11:13")) = -1
     * </pre>
     * @param timeInterval
     * @param date1
     * @param date2
     * @return 
     */
    public static long dateDiff(String timeInterval, Date date1, Date date2) {
		Calendar calendar = Calendar.getInstance();
		if (timeInterval.equals(TIME_INTERVAL_YEAR)) {
			calendar.setTime(date1);
			int time = calendar.get(Calendar.YEAR);
			calendar.setTime(date2);
			return time - calendar.get(Calendar.YEAR);
		}

		if (timeInterval.equals(TIME_INTERVAL_QUARTER)) {
			calendar.setTime(date1);
			int time = calendar.get(Calendar.YEAR) * 4;
			calendar.setTime(date2);
			time -= calendar.get(Calendar.YEAR) * 4;
			calendar.setTime(date1);
			time += calendar.get(Calendar.MONTH) / 4;
			calendar.setTime(date2);
			return time - calendar.get(Calendar.MONTH) / 4;
		}

		if (timeInterval.equals(TIME_INTERVAL_MONTH)) {
			calendar.setTime(date1);
			int time = calendar.get(Calendar.YEAR) * 12;
			calendar.setTime(date2);
			time -= calendar.get(Calendar.YEAR) * 12;
			calendar.setTime(date1);
			time += calendar.get(Calendar.MONTH);
			calendar.setTime(date2);
			return time - calendar.get(Calendar.MONTH);
		}

		if (timeInterval.equals(TIME_INTERVAL_WEEK)) {
			calendar.setTime(date1);
			int time = calendar.get(Calendar.YEAR) * 52;
			calendar.setTime(date2);
			time -= calendar.get(Calendar.YEAR) * 52;
			calendar.setTime(date1);
			time += calendar.get(Calendar.WEEK_OF_YEAR);
			calendar.setTime(date2);
			return time - calendar.get(Calendar.WEEK_OF_YEAR);
		}

		if (timeInterval.equals(TIME_INTERVAL_DAY)) {
			calendar.setTime(date1);
			int time = calendar.get(Calendar.DAY_OF_YEAR)
					+ calendar.get(Calendar.YEAR) * 365;
			calendar.setTime(date2);
			return time
					- (calendar.get(Calendar.DAY_OF_YEAR) + calendar
							.get(Calendar.YEAR) * 365);
		}

		if (timeInterval.equals(TIME_INTERVAL_HOUR)) {
			long time = date1.getTime() / 1000 / 60 / 60;
			return time - date2.getTime() / 1000 / 60 / 60;
		}

		if (timeInterval.equals(TIME_INTERVAL_MINUTE)) {
			long time = date1.getTime() / 1000 / 60;
			return time - date2.getTime() / 1000 / 60;
		}

		if (timeInterval.equals(TIME_INTERVAL_SECOND)) {
			long time = date1.getTime() / 1000;
			return time - date2.getTime() / 1000;
		}

		return date1.getTime() - date2.getTime();
	}
    
    /**
     * 月份增减
     * @param date
     * @param monthNum
     * @return
     */
    public static Date addMonth(Date date, int monthNum) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(GregorianCalendar.MONTH, monthNum);
        return gc.getTime();
    }

    /**
     * 月份增减
     * @param month "yyyy-MM"
     * @param monthNum
     * @return
     */
    public static Date addMonth(String month, int monthNum) {
    	Date date = parse(month, "yyyy-MM");
    	return addMonth(date, monthNum);
    }
    
    /**
     * 月份增减
     * @param date
     * @param monthNum
     * @param format
     * @return
     */
    public static String addMonth(Date date, int monthNum, String format) {
        return formatDate(addMonth(date, monthNum), format);
    }
    
    /**
     * 月第一天
     * @param date
     * @param format
     * @return
     */
    public static String firstDayOfMonth(Date date, String format) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	c.add(Calendar.MONTH, 0);
    	c.set(Calendar.DAY_OF_MONTH,1);
    	return formatDate(c.getTime(), format);
    }
}
