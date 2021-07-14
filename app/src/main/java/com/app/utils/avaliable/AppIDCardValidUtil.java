package com.app.utils.avaliable;

import java.util.Calendar;
import java.util.Hashtable;

public class AppIDCardValidUtil {

    /**
     * 获取身份证号所有区域编码设置
     * @return Hashtable
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Hashtable getAreaCodeAll() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }


    /**
     * 根据身份号返回所在区域信息
     * @param idCard
     * @return String
     */
    @SuppressWarnings("unchecked")
    public String getIDCardArea(String idCard) {
        Hashtable<String, String> ht = getAreaCodeAll();
        String area = ht.get(idCard.substring(0, 2));
        return area;
    }


    /**
     * 56名族定义
     * @return Hashtable
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Hashtable getMinorityAll() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("汉族", "汉族");
        hashtable.put("壮族", "壮族");
        hashtable.put("满族", "满族");
        hashtable.put("回族", "回族");
        hashtable.put("苗族", "苗族");
        hashtable.put("维吾尔族", "维吾尔族");
        hashtable.put("土家族", "土家族");
        hashtable.put("彝族", "彝族");
        hashtable.put("蒙古族", "蒙古族");
        hashtable.put("藏族", "藏族");
        hashtable.put("布依族", "布依族");
        hashtable.put("侗族", "侗族");
        hashtable.put("瑶族", "瑶族");
        hashtable.put("朝鲜族", "朝鲜族");
        hashtable.put("白族", "白族");
        hashtable.put("哈尼族", "哈尼族");
        hashtable.put("哈萨克族", "哈萨克族");
        hashtable.put("黎族", "黎族");
        hashtable.put("傣族", "傣族");
        hashtable.put("畲族", "畲族");
        hashtable.put("傈僳族", "傈僳族");
        hashtable.put("仡佬族", "仡佬族");
        hashtable.put("东乡族", "东乡族");
        hashtable.put("高山族", "高山族");
        hashtable.put("拉祜族", "拉祜族");
        hashtable.put("水族", "水族");
        hashtable.put("佤族", "佤族");
        hashtable.put("纳西族", "纳西族");
        hashtable.put("羌族", "羌族");
        hashtable.put("土族", "土族");
        hashtable.put("仫佬族", "仫佬族");
        hashtable.put("锡伯族", "锡伯族");
        hashtable.put("柯尔克孜族", "柯尔克孜族");
        hashtable.put("达斡尔族", "达斡尔族");
        hashtable.put("景颇族", "景颇族");
        hashtable.put("毛南族", "毛南族");
        hashtable.put("撒拉族", "撒拉族");
        hashtable.put("布朗族", "布朗族");
        hashtable.put("塔吉克族", "塔吉克族");
        hashtable.put("阿昌族", "阿昌族");
        hashtable.put("普米族", "普米族");
        hashtable.put("鄂温克族", "鄂温克族");
        hashtable.put("怒族", "怒族");
        hashtable.put("京族", "京族");
        hashtable.put("基诺族", "基诺族");
        hashtable.put("德昂族", "德昂族");
        hashtable.put("保安族", "保安族");
        hashtable.put("俄罗斯族", "俄罗斯族");
        hashtable.put("裕固族", "裕固族");
        hashtable.put("乌孜别克族", "乌孜别克族");
        hashtable.put("门巴族", "门巴族");
        hashtable.put("鄂伦春族", "鄂伦春族");
        hashtable.put("独龙族", "独龙族");
        hashtable.put("塔塔尔族", "塔塔尔族");
        hashtable.put("赫哲族", "赫哲族");
        hashtable.put("珞巴族", "珞巴族");
        return hashtable;
    }

    /**
     * 15位的身份证号码转换成18位
     * @param idCardNo 待转换的 15 位身份证号码
     * @return
     */
    public static String from15to18(String idCardNo) {
        String finalID = null;// 最终的ID
        if (!isIdCardNo(idCardNo))
            throw new IllegalArgumentException("您输入的身份证号码不正确:" + idCardNo);
        String century = "19";
        if (idCardNo.length() == 18) {
            finalID = idCardNo;
        } else {
            // 对应的17位的各个系数
            int[] weight = new int[] { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
                    5, 8, 4, 2 };

            // 通过加入世纪码, 变成 17 位的新号码本体.
            String tempNo = idCardNo.substring(0, 6) + century
                    + idCardNo.substring(6);

            // 下面算最后一位校验码
            int checkSum = 0;
            for (int i = 0; i < weight.length; i++) {
                int ai = Integer.parseInt("" + tempNo.charAt(i)); // 位于 i 位置的数值
                checkSum = checkSum + ai * weight[i];
            }
            // 求余数
            int checkNum = checkSum % 11;
            String lastNumber = null;
            switch (checkNum) {
                case 0:
                    lastNumber = "1";
                    break;
                case 1:
                    lastNumber = "0";
                    break;
                case 2:
                    lastNumber = "X";
                    break;
                case 3:
                    lastNumber = "9";
                    break;
                case 4:
                    lastNumber = "8";
                    break;
                case 5:
                    lastNumber = "7";
                    break;
                case 6:
                    lastNumber = "6";
                    break;
                case 7:
                    lastNumber = "5";
                    break;
                case 8:
                    lastNumber = "4";
                    break;
                case 9:
                    lastNumber = "3";
                    break;
                case 10:
                    lastNumber = "2";
                    break;
            }
            finalID = tempNo + lastNumber;
        }

        return finalID;

    }

    /**
     * 18位的身份证号码转换成15位
     * @param idCardNo18
     * @return
     */
    public static String from18to15(String idCardNo18) {
        if (!(isIdCardNo(idCardNo18) && idCardNo18.length() == 18)) {
            throw new IllegalArgumentException("身份证号参数格式不正确！");
        }
        return idCardNo18.substring(0, 6) + idCardNo18.substring(8, 17);
    }

    /**
     * 传入18位的身份证号,得到生日,格式:19880101
     * @param idCardNo18
     * @return
     */
    public static String getBirthday(String idCardNo18) {
        return idCardNo18.substring(6, 14);
    }

    /**
     * 根据身份证号码获取年龄
     * @param idCardNo18
     * @return
     */
    public static int getAge(String idCardNo18) {
        idCardNo18 = from15to18(idCardNo18);
        // 当前年份
        Calendar instance = Calendar.getInstance();
        int currentYear = instance.get(Calendar.YEAR);
        int age = currentYear - Integer.parseInt(getBirthYear(idCardNo18));
        return age;
    }

    /**
     * 传入身份证号,得到性别
     * @param idCardNo18
     * @return
     */
    public static String getSex(String idCardNo18) {
        idCardNo18 = from15to18(idCardNo18);
        // 定位到倒数第二位
        char c = idCardNo18.charAt(16);
        String sex = "";
        if (c % 2 != 0) {
            sex = "男";
        } else {
            sex = "女";
        }
        return sex;
    }

    /**
     * 传入身份证号,获取出生年份
     * @param idCardNo18
     * @return
     */
    private static String getBirthYear(String idCardNo18) {
        return idCardNo18.substring(6, 10);
    }

    /**
     * 判断输入的身份证号码是否符合身份证号的要求
     * @param idCardNO
     * @return
     */
    public static boolean isIdCardNo(String idCardNO) {
        boolean isID = false;
        if (idCardNO == null)
            return false;
        int len = idCardNO.length();
        if (len != 15 && len != 18) {
            isID = false;
        } else {
            // 排除最后一位是:X的情况
            for (int i = 0; i < len; i++) {
                try {
                    if (i < len - 1)
                        Integer.parseInt("" + idCardNO.charAt(i));
                    else if ((idCardNO.charAt(i) + "").equalsIgnoreCase("X")
                            || Integer.parseInt("" + idCardNO.charAt(i)) > -1)
                        isID = true;
                    else
                        isID = false;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }
        return isID;
    }
}
