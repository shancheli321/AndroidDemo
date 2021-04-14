package com.app.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppStringUtil {


    /**
     * 判断是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.trim().length() == 0);
    }


    /**
     * 是否只是数字.
     *
     * @param str 指定的字符串
     * @return 是否只是数字:是为true，否则false
     */
    public static Boolean isNumber(String str) {
        Boolean isNumber = false;
        String expr = "^[0-9]+$";
        if (str.matches(expr)) {
            isNumber = true;
        }
        return isNumber;
    }

    /**
     * 是否是邮箱
     *
     * @param email 指定的字符串
     * @return 是否是邮箱是为true，否则false
     */
    public static Boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{3})$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    /**
     * 是否包含中文.
     *
     * @param str 指定的字符串
     * @return 是否包含中文:是为true，否则false
     */
    public static Boolean isContainChinese(String str) {
        Boolean isChinese = false;
        String chinese = "[\u0391-\uFFE5]";
        if (!isEmpty(str)) {
            // 获取字段值的长度，如果含中文字符，则每个中文字符长度�?，否则为1
            for (int i = 0; i < str.length(); i++) {
                // 获取�?��字符
                String temp = str.substring(i, i + 1);
                // 判断是否为中文字�?
                if (temp.matches(chinese)) {
                    isChinese = true;
                } else {

                }
            }
        }
        return isChinese;
    }



    /**
     * 将首字母大写
     * <p/>
     * capitalizeFirstLetter(null) = null;
     * capitalizeFirstLetter("") = "";
     * capitalizeFirstLetter("2ab") = "2ab"
     * capitalizeFirstLetter("a") = "A"
     * capitalizeFirstLetter("ab") = "Ab"
     * capitalizeFirstLetter("Abc") = "Abc"
     *
     * @param str
     * @return
     */
    public static String capitalizeFirstLetter(String str) {
        if (isEmpty(str)) {
            return str;
        }

        char c = str.charAt(0);
        return (!Character.isLetter(c) || Character.isUpperCase(c)) ? str
                : new StringBuilder(str.length())
                .append(Character.toUpperCase(c))
                .append(str.substring(1)).toString();
    }


    /**
     * UTF-8编码
     * <p/>
     * <pre>
     * utf8Encode(null) = null
     * utf8Encode("") = "";
     * utf8Encode("aa") = "aa";
     * utf8Encode("啊啊啊啊") = "%E5%95%8A%E5%95%8A%E5%95%8A%E5%95%8A";
     * </pre>
     *
     * @param str
     * @return
     */
    public static String utf8Encode(String str) {

        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(
                        "UnsupportedEncodingException occurred. ", e);
            }
        }
        return str;
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
