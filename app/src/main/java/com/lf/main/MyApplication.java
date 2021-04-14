package com.lf.main;

import android.app.Application;

import com.app.utils.AppLog;

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        AppLog.init(this);
    }
}
