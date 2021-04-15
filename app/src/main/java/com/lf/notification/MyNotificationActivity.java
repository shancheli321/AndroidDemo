package com.lf.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.app.base.activity.AppBaseActivity;
import com.lf.activity.MyActivity;
import com.lf.main.R;

public class MyNotificationActivity extends AppBaseActivity {

    private Context mContext;

    private Notification notify1;

    private NotificationManager mNManager;

    private static final int NOTIFYID_1 = 1;


    Bitmap LargeBitmap = null;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notification);

        mContext = MyNotificationActivity.this;


        mNManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        LargeBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);


        Button btnShow = findViewById(R.id.btn_noti_show);
        Button btnCancel = findViewById(R.id.btn_noti_cancle);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancle();
            }
        });

    }

    private void show() {
        //定义一个PendingIntent点击Notification后启动一个Activity
        Intent it = new Intent(mContext, MyActivity.class);
        PendingIntent pit = PendingIntent.getActivity(mContext, 0, it, 0);

        //设置图片,通知标题,发送时间,提示方式等属性
        Notification.Builder mBuilder = new Notification.Builder(this);
        mBuilder.setContentTitle("叶良辰")                        //标题
                .setContentText("我有一百种方法让你呆不下去~")      //内容
                .setSubText("——记住我叫叶良辰")                    //内容下面的一小段文字
                .setTicker("收到叶良辰发送过来的信息~")             //收到信息后状态栏显示的文字信息
                .setWhen(System.currentTimeMillis())           //设置通知时间
                .setSmallIcon(R.mipmap.ic_launcher)            //设置小图标
                .setLargeIcon(LargeBitmap)                     //设置大图标
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)    //设置默认的三色灯与振动器
//                .setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.biaobiao))  //设置自定义的提示音
                .setAutoCancel(true)                           //设置点击后取消Notification
                .setContentIntent(pit);                        //设置PendingIntent
        notify1 = mBuilder.build();
        mNManager.notify(NOTIFYID_1, notify1);
    }


    /**
     *
     * 取消通知
     *
     * //除了可以根据ID来取消Notification外,还可以调用cancelAll();关闭该应用产生的所有通知
     */
    private void cancle() {
        mNManager.cancel(NOTIFYID_1);
    }


    /**
     * 自定义通知栏
     */
//    private void customNotifation() {
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
//        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_notification);
//        NotificationManager notificationManager = (NotificationManager) getSystemService
//                (NOTIFICATION_SERVICE);
//        mBuilder.setSmallIcon(R.mipmap.timg);
//        mBuilder.setContent(remoteViews);
//        if (progress == 1) {
//            mBuilder.setDefaults(Notification.DEFAULT_SOUND);
//        }
//        remoteViews.setImageViewResource(R.id.image, R.mipmap.timg);
//        remoteViews.setTextViewText(R.id.title, "我是标题");
//        remoteViews.setTextViewText(R.id.content, "我是内容");
//        remoteViews.setProgressBar(R.id.pBar, 10, progress, false);
//        remoteViews.setTextViewText(R.id.proNum, progress + "/10");
//        notificationManager.notify(10, mBuilder.build());
//
//
//        // 在8.0以上需要增加渠道名称和渠道ID
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(id, name, NotificationManager
//                    .IMPORTANCE_DEFAULT);
//            mBuilder.setChannelId(id);
//            notificationManager.createNotificationChannel(channel);
//
//            mBuilder.setSmallIcon(R.mipmap.timg);
//            mBuilder.setContent(remoteViews);
//            if (progress == 1) {
//                mBuilder.setDefaults(Notification.DEFAULT_SOUND);
//            }
//            remoteViews.setImageViewResource(R.id.image, R.mipmap.timg);
//            remoteViews.setTextViewText(R.id.title, "我是标题");
//            remoteViews.setTextViewText(R.id.content, "我是内容");
//            remoteViews.setProgressBar(R.id.pBar, 10, progress, false);
//            remoteViews.setTextViewText(R.id.proNum, progress + "/10");
//            notificationManager.notify(10, mBuilder.build());
//        }
//    }
}