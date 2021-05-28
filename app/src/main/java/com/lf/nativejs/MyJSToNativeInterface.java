package com.lf.nativejs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.webkit.JavascriptInterface;

public class MyJSToNativeInterface {

    private Context mContext;

    public MyJSToNativeInterface(Context context) {
        mContext = context;
    }


    /**
     * H5 调用native的方法
     * @param message
     */
    @SuppressLint("JavascriptInterface")
    @JavascriptInterface
    private void jsToNative(String message) {

    }

}
