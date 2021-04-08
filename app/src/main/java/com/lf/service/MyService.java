package com.lf.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.lf.main.MyApplication;
import com.lf.main.R;

import static android.os.Build.ID;


// 启动和绑定的情况下： onCreate( )->onStartCommand( )->onBind( )->onUnbind( )->onRebind( )


public class MyService extends Service {

    private final String TAG = "MyService---";

    private int count;
    private boolean quit;

    //定义onBinder方法所返回的对象
    private MyBinder binder = new MyBinder();


    //Service被创建时回调
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate方法被调用!");

        Log.d(TAG, String.valueOf(Thread.currentThread().getId()));

        //创建一个线程动态地修改count的值
        new Thread() {
            public void run() {
                while(!quit) {
                    try {
                        Thread.sleep(1000);
                        Log.i(TAG, "运行中----");

                    } catch (InterruptedException e){e.printStackTrace();}
                    count++;

                    setNotification();

                }
            };
        }.start();

    }

    //Service被启动时调用
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand方法被调用!");
        return super.onStartCommand(intent, flags, startId);
    }


    //必须实现的方法,绑定改Service时回调该方法
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind方法被调用!");
        return binder;
    }


    //Service断开连接时回调
    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind方法被调用!");
        return true;
    }


    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG, "onRebind方法被调用!");
        super.onRebind(intent);
    }



    //Service被关闭前回调
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.quit = true;
        Log.i(TAG, "onDestroyed方法被调用!");

        stopForeground(true);
    }



    // 重写onStartCommand方法，使用StartForeground(int,Notification)方法来启动service。
    // 前台服务是那些被认为用户知道（用户所认可的）且在系统内存不足的时候不允许系统杀死的服务。
    // 前台服务必须给状态栏提供一个通知，
    // 它被放到正在运行(Ongoing)标题之下——这就意味着通知只有在这个服务被终止或从前台主动移除通知后才能被解除。
    private void setNotification() {

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MyApplication.class), 0);
        NotificationCompat.Builder notification; //创建服务对象
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(ID, "NAME", manager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.setShowBadge(true);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            manager.createNotificationChannel(channel);
            notification = new NotificationCompat.Builder(MyService.this).setChannelId(ID);
        } else {
            notification = new NotificationCompat.Builder(MyService.this);
        }
        notification.setContentTitle("标题")
                .setContentText("内容----" + String.valueOf(count))
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)
                .build();
        Notification notification1 = notification.build();
        startForeground(1,notification1);

    }



    public class MyBinder extends Binder {
        public int getCount() {
            return count;
        }
    }

}
