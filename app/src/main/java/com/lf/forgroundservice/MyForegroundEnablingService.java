package com.lf.forgroundservice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.RequiresApi;



public class MyForegroundEnablingService extends Service {

    private static final int NOTIFICATION_ID = 10;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();
        startForeground(this, "FORE_GROUND-1");

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {



        startForeground(MyForeGroundService.instance, "FORE_GROUND-1");

        stopForeground(true);

        stopSelf();

        return START_NOT_STICKY;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startForeground(Service service, String name) {
        NotificationChannel channel = new NotificationChannel(name, "FORE_GROUND_NAME", NotificationManager.IMPORTANCE_LOW);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        Notification notification = new Notification.Builder(getApplicationContext(), name).build();

        service.startForeground(NOTIFICATION_ID, notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
