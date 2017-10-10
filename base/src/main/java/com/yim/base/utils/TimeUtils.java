package com.yim.base.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间工具类
 * Created by zym on 2016/8/5.
 */
public class TimeUtils {
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将时间戳转为时间字符串
     * <p>格式为yyyy-MM-dd HH:mm:ss
     */
    public static String milliseconds2String(long milliseconds) {
        return milliseconds2String(milliseconds, DEFAULT_FORMAT);
    }

    /**
     * 将时间戳转为时间字符串
     * <p>格式为用户自定义
     */
    public static String milliseconds2String(long milliseconds, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
        return sdf.format(new Date(milliseconds));
    }

    /**
     * 判断一个日期毫秒值是否是今年
     */
    public static boolean isThisYear(long milliseconds) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        int currentYear = calendar.get(Calendar.YEAR);
        calendar.setTimeInMillis(milliseconds);
        int year = calendar.get(Calendar.YEAR);
        return currentYear == year;
    }

    /**
     * 判断一个日期毫秒值是否是明天
     */
    public static boolean isTomorrow(long milliseconds) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.setTimeInMillis(milliseconds - 24 * 60 * 60 * 1000);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return currentYear == year && currentMonth == month && currentDay == day;
    }

    /**
     * 判断一个日期毫秒值是否是今天
     */
    public static boolean isToday(long milliseconds) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.setTimeInMillis(milliseconds);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return currentYear == year && currentMonth == month && currentDay == day;
    }

    /**
     * 判断一个日期毫秒值是否是昨天
     */
    public static boolean isYesterday(long milliseconds) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.setTimeInMillis(milliseconds + 24 * 60 * 60 * 1000);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return currentYear == year && currentMonth == month && currentDay == day;
    }

    public static String getToadyTime(String format) {
        return milliseconds2String(System.currentTimeMillis(), format);
    }

    public static int getAge(Date dateOfBirth) {
        int age = 0;
        Calendar born = Calendar.getInstance(Locale.CHINA);
        Calendar now = Calendar.getInstance(Locale.CHINA);
        if (dateOfBirth != null) {
            now.setTime(new Date());
            born.setTime(dateOfBirth);
            if (born.after(now)) {
                throw new IllegalArgumentException("Can't be born in the future");
            }
            age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
            String now_month_day = TimeUtils.formatDate("MM-dd", now.getTime()),
                    born_month_day = TimeUtils.formatDate("MM-dd", born.getTime());
            if (born_month_day.compareTo(now_month_day)>0) {
                age -= 1;
            }
        }
        return age;
    }

    /**
     * 通用日期格式化方法
     * @param pattern 格式模板
     * @param date 来源日期
     * @return 格式化后的字符串
     */
    public static String formatDate(String pattern, Date date){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.CHINA);

        return simpleDateFormat.format(date);
    }

    /**
     * 获取明天凌晨12点的时间毫秒值
     */
    public static long getTomorrowTimeMillis() {
        long tomorrow = System.currentTimeMillis() + 24 * 60 * 60 * 1000;
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTimeInMillis(tomorrow);
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(currentYear, currentMonth, currentDay, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取明天凌晨12点的时间毫秒值
     */
    public static long getTodayTimeMillis() {
        long tomorrow = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTimeInMillis(tomorrow);
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(currentYear, currentMonth, currentDay, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * @param timeAccuracy 时间精度，根据情况返回具体的精确值 {@link TimeAccuracy#DAY},
     *                                                      {@link TimeAccuracy#HOUR},
     *                                                      {@link TimeAccuracy#MINUTE},
     *                                                      {@link TimeAccuracy#SECOND}
     * @param time         格式化时间
     * @return 返回传入时间和当前时间的时间差字符串
     */
    public static String getTimeDistance(TimeAccuracy timeAccuracy, long time) {
        long currentTimeMillis = System.currentTimeMillis();
        long timeDistance = Math.abs(currentTimeMillis - time);
        long second = timeDistance / 1000;
        long minute = timeDistance / 1000 / 60;
        long hour = timeDistance / 1000 / 60 / 60;
        long day = timeDistance / 1000 / 60 / 60 / 24;

        switch (timeAccuracy) {
            case DAY:
                return day + "天";
            case HOUR:
                if (day > 0) {
                    return day + "天" + ((hour - day * 24) > 0 ? ((hour - day * 24) + "小时") : "");
                } else if (hour > 0) {
                    return hour + "小时";
                } else {
                    return "0小时";
                }
            case MINUTE:
                if (day > 0) {
                    return day + "天" + ((hour - day * 24) > 0 ? ((hour - day * 24) + "小时") : "") +
                            ((minute - hour * 60) > 0 ? ((minute - hour * 60) + "分钟") : "");
                } else if (hour > 0) {
                    return hour + "小时" + ((minute - hour * 60) > 0 ? ((minute - hour * 60) + "分钟") : "");
                } else {
                    return minute + "分钟";
                }
            case SECOND:
                if (day > 0) {
                    return day + "天" + ((hour - day * 24) > 0 ? ((hour - day * 24) + "小时") : "") +
                            ((minute - hour * 60) > 0 ? ((minute - hour * 60) + "分钟") : "") +
                            ((second - minute * 60) > 0 ? ((second - minute * 60) + "秒") : "");
                } else if (hour > 0) {
                    return hour + "小时" + ((minute - hour * 60) > 0 ? ((minute - hour * 60) + "分钟") : "") +
                    ((second - minute * 60) > 0 ? ((second - minute * 60) + "秒") : "");
                } else if (minute > 0) {
                    return minute + "分钟" + ((second - minute * 60) > 0 ? ((second - minute * 60) + "秒") : "");
                } else {
                    return second + "秒";
                }
        }
        return "";
    }

    /**
     * 时间精度
     */
    public enum TimeAccuracy {
        /**
         * 读取到时间的天的单位
         */
        DAY,

        /**
         * 读取到时间的小时的单位
         */
        HOUR,

        /**
         * 读取到时间的分钟的单位
         */
        MINUTE,

        /**
         * 读取到时间的秒的单位
         */
        SECOND
    }
}
