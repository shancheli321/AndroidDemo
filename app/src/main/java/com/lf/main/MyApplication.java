package com.lf.main;

import android.app.Application;

import com.app.single.AppSingle;
import com.app.utils.AppLog;
import com.app.utils.suanfa.AppAllUtil;

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        AppSingle.getInstance().setContext(getApplicationContext());

        AppLog.init(this);


            //待放置元素的数组
            String[] out = new String[2];
            //待计算排列组合的数组
            String[] lakes = new String[]{"1", "2"};
            //放置元素的索引从0开始
            AppAllUtil.calc(out, 0, lakes);



    }
}
