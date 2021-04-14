package com.app.utils;

import android.content.Context;
import android.widget.Toast;

public class AppToastUtil {

    /**
     * 显示short message
     * @param context 全局context
     * @param resId string string资源id
     */
    public static void showShort(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示short message
     * @param context 全局context
     * @param message 显示msg
     */
    public static void showShort(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示long message
     * @param context 全局context
     * @param resId string string资源id
     */
    public static void showLong(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }

    /**
     * 显示long message
     * @param context 全局context
     * @param message 显示msg
     */
    public static void showLong(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
