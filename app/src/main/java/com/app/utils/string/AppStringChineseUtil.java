package com.app.utils.string;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppStringChineseUtil {


    // 纯数字
    private static String DIGIT_REGEX = "[0-9]+";

    // 含有数字
    private static String CONTAIN_DIGIT_REGEX = ".*[0-9].*";

    // 纯字母
    private static String LETTER_REGEX = "[a-zA-Z]+";

    // 包含字母
    private static String CONTAIN_LETTER_REGEX = ".*[a-zA-z].*";

    // 纯中文
    private static String CHINESE_REGEX = "[\u4e00-\u9fa5]";

    // 仅仅包含字母和数字
    private static String LETTER_DIGIT_REGEX = "^[a-z0-9A-Z]+$";

    // 仅仅包含中文和字母
    private static String CHINESE_LETTER_REGEX = "([\u4e00-\u9fa5]+|[a-zA-Z]+)";

    // 仅仅包含中文 字母 和数字
    private static String CHINESE_LETTER_DIGIT_REGEX = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";

    /**
     * 判断字符串是否仅含有数字和字母
     *
     * @param str
     * @return
     */
    public static boolean isLetterDigit(String str) {
        return str.matches(LETTER_DIGIT_REGEX);
    }

    /**
     * 仅包含英文字母、数字和汉字
     *
     * @param str
     * @return
     */
    public static boolean isLetterDigitOrChinese(String str) {
        return str.matches(CHINESE_LETTER_DIGIT_REGEX);
    }

    /**
     * 仅包含汉字和字母
     *
     * @param passengerName
     * @return
     */
    public static boolean isChineseOrLetter(String passengerName) {
        Pattern pattern = Pattern.compile(CHINESE_LETTER_REGEX);
        Matcher matcher = pattern.matcher(passengerName);
        if (matcher.matches()) {
            //不包含特殊字符
            return true;
        } else {
            //包含了特殊字符
            return false;
        }
    }
    /**
     * 判断一个字符串是否包含标点符号（中文或者英文标点符号），true 包含。<br/>
     * 原理：对原字符串做一次清洗，清洗掉所有标点符号。<br/>
     * 此时，如果入参 ret 包含标点符号，那么清洗前后字符串长度不同，返回true；否则，长度相等，返回false。<br/>
     *
     * @param ret
     * @return true 包含中英文标点符号
     */
    public static boolean hasPunctuation(String ret) {
        boolean b = false;
        String tmp = ret;
//        replaceAll里面的正则匹配可以清空字符串中的中英文标点符号，只保留数字、英文和中文。
        tmp = tmp.replaceAll("\\p{P}", "");
        if (ret.length() != tmp.length()) {
            b = true;
        }
        return b;
    }

    /**
     *
     * 检测String是否全是中文
     * @param name
     * @return
     */

    public static boolean isAllChinese(String name) {
        boolean res = true;
        char[] cTemp = name.toCharArray();
        for (int i = 0; i < name.length(); i++) {
            if (!isChinese(cTemp[i])) {
                res = false;
                break;
            }
        }
        return res;
    }

    /**
     *
     * 判定输入汉字
     * @param c
     * @return
     */
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {

            return true;
        }
        return false;

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
        if (!AppStringUtil.isEmpty(str)) {
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
     * 仅为汉字
     *
     * @param con
     * @return true 是汉字
     */
    public static boolean isChinese(String con) {
        Pattern pattern = Pattern.compile(CHINESE_REGEX);
        for (int i = 0; i < con.length(); i = i + 1) {
            if (!pattern.matcher(
                    String.valueOf(con.charAt(i))).find()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 仅是数字
     * @param ret
     * @return
     */
    public static boolean isDigit(String ret) {
        return ret.matches(DIGIT_REGEX);
    }

    /**
     * 仅是字母
     * @param ret
     * @return
     */
    public static boolean isLetter(String ret) {
        return ret.matches(LETTER_REGEX);
    }

    /**
     * 包含数字
     * @param ret
     * @return
     */
    public static boolean hasDigit(String ret) {
        return ret.matches(CONTAIN_DIGIT_REGEX);
    }

    /**
     * 包含字母
     * @param ret
     * @return
     */
    public static boolean hasLetter(String ret) {
        return ret.matches(CONTAIN_LETTER_REGEX);
    }


    /**
     * 移除中英文标点符号
     * @param content
     * @return
     */
    public static String removeProperty(String content) {
        return content.replaceAll("\\pP|\\pS|\\pC|\\pN|\\pZ", "");
    }


    /**
     * 得到字母
     * @param content
     * @return
     */
    public static String getLetter(String content) {
        return content.replaceAll("[^(A-Za-z)]", "");
    }

    /**
     * 得到中文
     * @param content
     * @return
     */
    public static String getChinese(String content) {
        return  content.replaceAll("[^(\\u4e00-\\u9fa5)]", "");

    }

    /**
     * 得到数字
     * @param content
     * @return
     */
    public static String getNumber(String content) {
        return content.replaceAll("[^(0-9)]", "");
    }

    /**
     * 得到中文和字母
     * @param content
     * @return
     */
    public static String getChineseAndLetter(String content) {
        return content.replaceAll(CHINESE_LETTER_REGEX, "");
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
        if (AppStringUtil.isEmpty(str)) {
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
    public static String toUtf8Encode(String str) {

        if (!AppStringUtil.isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(
                        "UnsupportedEncodingException occurred. ", e);
            }
        }
        return str;
    }
}
