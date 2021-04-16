package com.lf.main;

import android.app.Application;

import com.app.single.AppSingle;
import com.app.utils.AppLog;

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        AppSingle.getInstance().setContext(getApplicationContext());

        AppLog.init(this);


    }
}
