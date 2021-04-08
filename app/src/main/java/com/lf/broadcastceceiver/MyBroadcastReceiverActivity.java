package com.lf.broadcastceceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.lf.main.R;


// 一、 两种广播类型：
// 1. 无序广播 标准广播   完全异步的广播 发出广播后，所有的广播接收器 几乎在同一时刻收到这条广播
//                      所以没有先后顺序可言，效率比较高，无法被截断。
// 2. 有序广播           同步执行的广播，广播发出后，会有一个广播接收器接收广播消息，
//                      当这个广播接收器中的逻辑执行完毕后广播才会继续传递。
//                      有先后顺序，优先级较高的接收器先收到广播消息并且可以截断正在传递的广播，使得后面的接收器无法收到广播消息。
//
// 二、 两种注册广播的方式
//  1. 动态注册广播       在activity中通过代码动态注册广播，定义类extends BroadcastReceiver，重写onReceiver方法，
//                      通过registerReceiver注册广播。在onDestory方法中通过unregisterReceiver取消注册。
//                      自由控制注册和取消，具有灵活性，缺点是程序启动后才能接受广播。
//
//  2.静态注册           需要在androidManifest.xml文件中声明, 程序未启动就可以接受的广播


// 三、 注意
// 不要在广播里添加过多逻辑或者进行任何耗时操作,因为在广播中是不允许开辟线程的,
// 当onReceiver( )方法运行较长时间(超过10秒)还没有结束的话,那么程序会报错(ANR),
// 广播更多的时候扮演的是一个打开其他组件的角色,比如启动Service,Notification提示, Activity等！


// 四、本地广播
//              正在发送的广播不会离开我们的程序，不必担心数据泄露。
//              其他程序无法将广播发送到我们程序内部，不必担心安全漏洞。发送本地广播比系统全局广播更高效。

public class MyBroadcastReceiverActivity extends AppCompatActivity {

    private MyBRReceiver myReceiver;

    private MyLocalReceiver localReceiver;

    private MyCustomBroadReceiver customBroadReceiver;

    private LocalBroadcastManager localBroadcastManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_broadcast_receiver);


        // 发送自定义广播
        findViewById(R.id.bt_sendReciver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 发送无序广播
                sendBroadcast(new Intent("com.example.broadcasttest.MY_BROADCAST"));

                // 发送有序广播
//                sendOrderedBroadcast(new Intent("com.example.broadcasttest.MY_BROADCAST"), null);

            }
        });


        // 发送本地广播
        findViewById(R.id.bt_sendlocalReciver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("com.example.broadcasttest.MY_LOCAL_BROADCAST");
                localBroadcastManager.sendBroadcast(intent);
            }
        });

        registSystmReceiver();
        registLocalReceiver();
        registCustomReceiver();

    }

    //动态注册广播
    private void registSystmReceiver() {

        myReceiver = new MyBRReceiver();
        IntentFilter itFilter = new IntentFilter();
        itFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(myReceiver, itFilter);
    }


    // 本地广播
    private void registLocalReceiver() {
        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        //初始化广播接收者，设置过滤器
        localReceiver = new MyLocalReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcasttest.MY_LOCAL_BROADCAST");
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);

    }

    // 自定义广播
    private void registCustomReceiver() {

        customBroadReceiver = new MyCustomBroadReceiver();
        IntentFilter itFilter = new IntentFilter();
        itFilter.addAction("com.example.broadcasttest.MY_BROADCAST");
        registerReceiver(customBroadReceiver, itFilter);
    }




    //别忘了将广播取消掉哦~
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);

        localBroadcastManager.unregisterReceiver(localReceiver);
    }
}