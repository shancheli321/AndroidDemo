package com.app.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AppDateUtil {



    /**
     * 获得指定年和月的天数
     */
    public static int getMonthLastDay(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        /** 把日期设置为当月第一天 */
        a.set(Calendar.DATE, 1);
        /** 日期回滚一天，也就是最后一天 */
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }


    /**
     * 获取当天是星期几
     */
    public static String getDateToWeek(Date date) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setTime(date);
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
        return "星期" + mWay;
    }

    /**
     * 获取今天的日期
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getToday() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * 获取当天的年份
     *
     * @return
     */

    public static String getTodayYear() {
        String today = getToday();
        String year = today.substring(0, 4);
        return year;
    }

    /**
     * 获取当前日期的月数的位置
     *
     * @return
     */
    public static String getTodayMonth() {
        String today = getToday();
        String month = today.substring(5, 7);
        return month;
    }

    /**
     * 获取当前日期的天数的日子
     *
     * @return
     */
    public static String getTodayDay() {
        String today = getToday();
        String day = today.substring(8, 10);
        return day;
    }

    /**
     * 获取当前小时
     */
    public static String getTodayHour() {
        String today = getToday();
        String hour = today.substring(12, 14);
        return hour;
    }

    /**
     * 获取当前分钟
     */
    public static String getTodayMinute() {
        String today = getToday();
        String minute = today.substring(15, 17);
        return minute;
    }

    /**
     * 获取当前秒
     */
    public static String getTodaySecond() {
        String today = getToday();
        String second = today.substring(18, 20);
        return second;
    }

}
