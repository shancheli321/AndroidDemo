package com.app.utils.encry;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AppMD5Util {

    private static final String TAG = "AppMD5Util";

    /**
     * md5加密
     * @param plainText 待加密字符串
     * @return 加密后32位字符串
     */
    public static String getMd5(String plainText) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            Log.d(TAG, "getMd5 error", e);
            return null;
        }

    }


    /**
     * SHA1编码
     */
    public static String SHA1Encode(String source) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            return byte2String(md.digest(source.getBytes()));
        } catch (Exception ex) {
            throw new RuntimeException("md5 encode error");
        }
    }


    /**
     * byte数组转换为字符串
     */
    public static String byte2String(byte[] bytes) {
        StringBuffer buf = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            if (((int) bytes[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString((int) bytes[i] & 0xff, 16));
        }
        return buf.toString();
    }
}
