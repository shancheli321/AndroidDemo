package com.app.utils.string;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppStringUtil {



    /**
     * 判断字符串是否为空
     * @param str 字符串
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str) || str.trim().length() == 0;
    }


    /**
     * 判断str null,"","null" 均视为空.
     * @param str      字符
     * @return 结果 boolean
     */
    public static boolean isNotEmpty(String str) {
        boolean bool = true;
        if (str == null || "null".equals(str) || "".equals(str)) {
            bool = false;
        } else {
            bool = true;
        }
        return bool;
    }


    /**
     * 获取随机数
     */
    public static int getRandomNumber(int num) {
        int result;
        Random rd = new Random();
        int tem = (int) Math.pow(10, num);
        result = tem + rd.nextInt(tem);
        return result;
    }


    /**
     * 格式化double数据，并保留两位小数，整数则不补全0.00
     */
    public static String formatExclueIntNumber(double value) {
        double value1 = Math.round(value * 100) * 0.01;
        String bb = "" + value1;
        String result = bb;
        String[] aar = bb.split("\\.");
        if (aar.length == 1) {
            result = aar[0] + "";
        } else if (aar.length > 1) {
            String str1 = aar[1];
            if (str1.length() == 1) {
                result = aar[0] + "." + str1 + "0";
            } else if (str1.length() == 2) {
                result = aar[0] + "." + str1;
            } else {
                result = aar[0] + "." + str1.substring(0, 3);
            }
        }
        return result + "";
    }

    /**
     * 格式化double数据，并保留两位小数，整数也补全0.00
     */
    public static String formatInclueIntNumber(double value) {
        double value1 = Math.round(value * 100) * 0.01;
        String bb = "" + value1;
        String result = bb;
        String[] aar = bb.split("\\.");
        if (aar.length == 1) {
            result = aar[0] + ".00";
        } else if (aar.length > 1) {
            String str1 = aar[1];
            if (str1.length() == 1) {
                result = aar[0] + "." + str1 + "0";
            } else if (str1.length() == 2) {
                result = aar[0] + "." + str1;
            } else {
                result = aar[0] + "." + str1.substring(0, 3);
            }
        }
        return result + "";
    }
}
