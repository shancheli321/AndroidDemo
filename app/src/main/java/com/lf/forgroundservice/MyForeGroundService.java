package com.lf.forgroundservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyForeGroundService extends Service {

    static MyForeGroundService instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        if (startService(new Intent(this, MyForegroundEnablingService.class)) == null)
            throw new RuntimeException("Couldn't find " + MyForegroundEnablingService.class.getSimpleName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        instance = null;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
