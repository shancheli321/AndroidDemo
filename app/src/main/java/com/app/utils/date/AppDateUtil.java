package com.app.utils.date;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AppDateUtil {

    public static final SimpleDateFormat YYYYMMDD_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    public static final SimpleDateFormat HHMMSS_FORMAT = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    public static final SimpleDateFormat YYYYMMDDHHMMSS_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    private static final String[] CHINESE_ZODIAC = new String[]{"猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊"};
    private static final String[] ZODIAC = new String[]{"水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座"};

    private static final int[] ZODIAC_FLAGS = new int[]{20, 19, 21, 21, 21, 22, 23, 23, 23, 24, 23, 22};


    /** 日期格式：yyyy-MM-dd HH:mm:ss **/
    public static final String DF_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /** 日期格式：yyyy-MM-dd HH:mm **/
    public static final String DF_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    /** 日期格式：yyyy-MM-dd **/
    public static final String DF_YYYY_MM_DD = "yyyy-MM-dd";

    /** 日期格式：HH:mm:ss **/
    public static final String DF_HH_MM_SS = "HH:mm:ss";

    /** 日期格式：HH:mm **/
    public static final String DF_HH_MM = "HH:mm";

    private final static long MINUTE = 60 * 1000;// 1分钟
    private final static long HOUR = 60 * MINUTE;// 1小时
    private final static long DAY = 24 * HOUR;// 1天
    private final static long MONTH = 31 * DAY;// 月
    private final static long YEAR = 12 * MONTH;// 年


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


    /**
     * 当天的年月日
     * @return
     */
    public static String todayYyyyMmDd() {
        return YYYYMMDD_FORMAT.format(new Date());
    }

    /**
     * 当天的时分秒
     * @return
     */
    public static String todayHhMmSs() {
        return HHMMSS_FORMAT.format(new Date());
    }

    /**
     * 当天的年月日时分秒
     * @return
     */
    public static String todayYyyyMmDdHhMmSs() {
        return YYYYMMDDHHMMSS_FORMAT.format(new Date());
    }

    /**
     * 获取年
     * @param dateTime
     * @return
     */
    public static int parseYyyy(String dateTime) {
        try {
            Calendar e = Calendar.getInstance();
            Date date = YYYYMMDDHHMMSS_FORMAT.parse(dateTime);
            e.setTime(date);
            return e.get(1);
        } catch (ParseException var3) {
            var3.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取年
     * @param dateTime
     * @param simpleDateFormat
     * @return
     */
    public static int parseYyyy(String dateTime, SimpleDateFormat simpleDateFormat) {
        try {
            Calendar e = Calendar.getInstance();
            Date date = simpleDateFormat.parse(dateTime);
            e.setTime(date);
            return e.get(1);
        } catch (ParseException var4) {
            var4.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取年
     * @param date
     * @return
     */
    public static int parseYyyy(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(1);
    }

    /**
     * 获取月
     * @param dateTime
     * @return
     */
    public static int parseMm(String dateTime) {
        try {
            Calendar e = Calendar.getInstance();
            Date date = YYYYMMDDHHMMSS_FORMAT.parse(dateTime);
            e.setTime(date);
            return e.get(2);
        } catch (ParseException var3) {
            var3.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取月
     * @param dateTime
     * @param simpleDateFormat
     * @return
     */
    public static int parseMm(String dateTime, SimpleDateFormat simpleDateFormat) {
        try {
            Calendar e = Calendar.getInstance();
            Date date = simpleDateFormat.parse(dateTime);
            e.setTime(date);
            return e.get(2);
        } catch (ParseException var4) {
            var4.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取月
     * @param date
     * @return
     */
    public static int parseMm(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(2);
    }

    /**
     * 获取日
     * @param dateTime
     * @return
     */
    public static int parseDd(String dateTime) {
        try {
            Calendar e = Calendar.getInstance();
            Date date = YYYYMMDDHHMMSS_FORMAT.parse(dateTime);
            e.setTime(date);
            return e.get(5);
        } catch (ParseException var3) {
            var3.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取日
     * @param dateTime
     * @param simpleDateFormat
     * @return
     */
    public static int parseDd(String dateTime, SimpleDateFormat simpleDateFormat) {
        try {
            Calendar e = Calendar.getInstance();
            Date date = simpleDateFormat.parse(dateTime);
            e.setTime(date);
            return e.get(5);
        } catch (ParseException var4) {
            var4.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取日
     * @param date
     * @return
     */
    public static int parseDd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(5);
    }

    /**
     * 获取年月日
     * @param dateTime
     * @return
     */
    public static String parseYyyyMmDd(String dateTime) {
        String result = "";

        try {
            Date e = YYYYMMDDHHMMSS_FORMAT.parse(dateTime);
            result = YYYYMMDD_FORMAT.format(e);
        } catch (ParseException var3) {
            var3.printStackTrace();
        }

        return result;
    }

    /**
     * 获取年月日
     * @param dateTime
     * @param simpleDateFormat
     * @return
     */
    public static String parseYyyyMmDd(String dateTime, SimpleDateFormat simpleDateFormat) {
        String result = "";

        try {
            Date e = simpleDateFormat.parse(dateTime);
            result = YYYYMMDD_FORMAT.format(e);
        } catch (ParseException var4) {
            var4.printStackTrace();
        }

        return result;
    }

    /**
     * 获取年月日
     * @param date
     * @return
     */
    public static String parseYyyyMmDd(Date date) {
        return YYYYMMDD_FORMAT.format(date);
    }

    /**
     * 时分秒
     * @param dateTime
     * @return
     */
    public static String parseHhMmSs(String dateTime) {
        try {
            Date e = YYYYMMDDHHMMSS_FORMAT.parse(dateTime);
            return HHMMSS_FORMAT.format(e);
        } catch (ParseException var2) {
            var2.printStackTrace();
            return "";
        }
    }

    /**
     * 时分秒
     * @param dateTime
     * @param simpleDateFormat
     * @return
     */
    public static String parseHhMmSs(String dateTime, SimpleDateFormat simpleDateFormat) {
        try {
            Date e = simpleDateFormat.parse(dateTime);
            return HHMMSS_FORMAT.format(e);
        } catch (ParseException var3) {
            var3.printStackTrace();
            return "";
        }
    }

    /**
     * 时分秒
     * @param date
     * @return
     */
    public static String parseHhMmSs(Date date) {
        return HHMMSS_FORMAT.format(date);
    }

    /**
     * 获取星期几
     * @param dateTime
     * @return
     */
    public static int getWeekNumber(String dateTime) {
        return getWeekNumber(string2Date(dateTime, YYYYMMDDHHMMSS_FORMAT));
    }

    /**
     * 获取星期几
     * @param dateTime
     * @param simpleDateFormat
     * @return
     */
    public static int getWeekNumber(String dateTime, SimpleDateFormat simpleDateFormat) {
        return getWeekNumber(string2Date(dateTime, simpleDateFormat));
    }

    /**
     * 获取星期几
     * @param date
     * @return
     */
    public static int getWeekNumber(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(7);
    }

    /**
     * 日期中某个月份的第几周
     * @param dateTime
     * @return
     */
    public static int getWeekOfMonth(String dateTime) {
        return getWeekOfMonth(string2Date(dateTime, YYYYMMDDHHMMSS_FORMAT));
    }

    /**
     * 日期中某个月份的第几周
     * @param dateTime
     * @param simpleDateFormat
     * @return
     */
    public static int getWeekOfMonth(String dateTime, SimpleDateFormat simpleDateFormat) {
        return getWeekOfMonth(string2Date(dateTime, simpleDateFormat));
    }

    /**
     * 日期中某个月份的第几周
     * @param date
     * @return
     */
    public static int getWeekOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(4);
    }

    /**
     * 日期中某个年份的第几周
     * @param time
     * @return
     */
    public static int getWeekOfYear(String time) {
        return getWeekOfYear(string2Date(time, YYYYMMDDHHMMSS_FORMAT));
    }

    /**
     * 日期中某个年份的第几周
     * @param time
     * @param simpleDateFormat
     * @return
     */
    public static int getWeekOfYear(String time, SimpleDateFormat simpleDateFormat) {
        return getWeekOfYear(string2Date(time, simpleDateFormat));
    }

    /**
     * 日期中某个年份的第几周
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(3);
    }

    /**
     * 将年月日时分秒转成Long类型
     * @param dateTime
     * @return
     */
    public static Long dateTimeToTimeStamp(String dateTime) {
        try {
            Date e = YYYYMMDDHHMMSS_FORMAT.parse(dateTime);
            return Long.valueOf(e.getTime() / 1000L);
        } catch (ParseException var2) {
            var2.printStackTrace();
            return Long.valueOf(0L);
        }
    }

    /**
     * 将Long类型转成年月日时分秒
     * @param timeStamp
     * @return
     */
    public static String timeStampToDateTime(Long timeStamp) {
        return YYYYMMDDHHMMSS_FORMAT.format(new Date(timeStamp.longValue() * 1000L));
    }

    /**
     * 将年月日时分秒转成Date类型
     * @param time
     * @return
     */
    public static Date string2Date(String time) {
        return string2Date(time, YYYYMMDDHHMMSS_FORMAT);
    }

    /**
     * 将年月日时分秒转成Date类型
     * @param time
     * @param simpleDateFormat
     * @return
     */
    public static Date string2Date(String time, SimpleDateFormat simpleDateFormat) {
        try {
            return simpleDateFormat.parse(time);
        } catch (ParseException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    /**
     * 将Date类型转成年月日时分秒
     * @param date
     * @return
     */
    public static String date2String(Date date) {
        return date2String(date, YYYYMMDDHHMMSS_FORMAT);
    }

    /**
     * 将Date类型转成年月日时分秒
     * @param date
     * @param simpleDateFormat
     * @return
     */
    public static String date2String(Date date, SimpleDateFormat simpleDateFormat) {
        return simpleDateFormat.format(date);
    }

    /**
     * 比较日期
     * @param standDate
     * @param desDate
     * @return
     */
    public static boolean dateIsBefore(String standDate, String desDate) {
        try {
            return YYYYMMDDHHMMSS_FORMAT.parse(desDate).before(YYYYMMDDHHMMSS_FORMAT.parse(standDate));
        } catch (ParseException var3) {
            var3.printStackTrace();
            return false;
        }
    }

    /**
     * 相差多少分钟
     * @param beginDate
     * @param endDate
     * @return
     */
    public static long minutesBetweenTwoDate(String beginDate, String endDate) {
        long millisBegin = dateTimeToTimeStamp(beginDate).longValue();
        long millisEnd = dateTimeToTimeStamp(endDate).longValue();
        return (millisEnd - millisBegin) / 60L;
    }

    /**
     * 将日期格式化成友好的字符串：几分钟前、几小时前、几天前、几月前、几年前、刚刚
     *
     * @param date
     * @return
     */
    public static String formatFriendly(Date date) {
        if (date == null) {
            return null;
        }
        long diff = new Date().getTime() - date.getTime();
        long r = 0;
        if (diff > YEAR) {
            r = (diff / YEAR);
            return r + "年前";
        }
        if (diff > MONTH) {
            r = (diff / MONTH);
            return r + "个月前";
        }
        if (diff > DAY) {
            r = (diff / DAY);
            return r + "天前";
        }
        if (diff > HOUR) {
            r = (diff / HOUR);
            return r + "个小时前";
        }
        if (diff > MINUTE) {
            r = (diff / MINUTE);
            return r + "分钟前";
        }
        return "刚刚";
    }


}
