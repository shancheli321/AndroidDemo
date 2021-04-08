package com.lf.service;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.lf.main.R;


// 1. 按运行地点：  区分字段 process  android:process=":remote"是将服务设置为远程服
//              本地   服务依附在主进程上而不是独立的进程
//                    主进程被Kill后，服务便会终止

//              远程   服务为独立的进程，对应进程名格式为所在包名加上你指定的android:process字符串
//                     该服务是独立的进程，会占用一定资源，并且使用AIDL进行IPC稍微麻烦一点。

// 2. 按运行类型：
//      前台服务   会在通知栏显示onGoing的 Notification
//                当服务被终止的时候，通知一栏的 Notification 也会消失，这样对于用户有一定的通知作用。常见的如音乐播放服务。

//      后台服务   默认的服务即为后台服务，即不会在通知一栏显示 onGoing的 Notification。
//                当服务被终止的时候，用户是看不到效果的

// 3. 按使用方式:
//              startService启动的服务       主要用于启动一个服务执行后台任务，不进行通信。停止服务使用stopService。
//              bindService启动的服务        方法启动的服务要进行通信。停止服务使用unbindService。
//              同时使用startService、bindService 启动的服务	  停止服务应同时使用stopService与unbindService。



// 4. 一个Service被使用startService方法启动多少次，
// onCreate方法只会调用一次，onStartCommand方法将会被调用多次（与startService的次数一致），
// 且系统只会创建一个Service实例（结束该Service也只需要调用一次stopService），
// 该Service会一直在后台运行，直至调用stopService或调用自身的stopSelf方法。
//     在系统资源不足的情况下，服务有可能被系统结束（kill）；



// 5. Thread和Service的区别：
//                          1)Thread 是程序执行的最小单元，它是分配CPU的基本单位，可以用 Thread 来执行一些异步的操作。
//                          如果是Local Service，那么对应的 Service 是运行在主进程的 main 线程上的。
//                          如果是Remote Service，那么对应的 Service 则是运行在独立进程的main 线程上。因此 Service不是线程！
//
//
//                          2）Thread 的运行是独立于 Activity 的，也就是说当一个 Activity 被 finish 之后，
//                          如果你没有主动停止Thread 或者Thread 里的 run 方法没有执行完毕的话，Thread 也会一直执行。
//                          因此这里会出现一个问题：当 Activity 被 finish 之后，你不再持有该 Thread 的引用。
//                          另一方面，你没有办法在不同的 Activity 中对同一 Thread 进行控制。
//                          而任何 Activity 都可以控制同一 Service，而系统也只会创建一个对应 Service 的实例。
//                          因此你可以把 Service 想象成一种消息服务，
//                          而你可以在任何有 Context 的地方调用 Context.startService、Context.stopService、Context.bindService，Context.unbindService，来控制它，
//                          你也可以在 Service 里注册 BroadcastReceiver，在其他地方通过发送 broadcast 来控制它，当然这些都是 Thread 做不到的。
//
//                          3) Service组件主要有两个目的：后台运行和跨进程访问。service可以在android系统后台独立运行，线程是不可以。
//
//                          4) Service类是可以供其他应用程序来调用这个Service的而Thread只是在本类中在使用，如果本类关闭 那么这个thread也就下岗了而Service类则不会.


public class MyServiceActivity extends AppCompatActivity {

    private final String TAG = "MyService----";

    MyService.MyBinder binder;

    // service 链接
    private ServiceConnection conn = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);

        setService();

        setIntentService();

    }




    private void setService() {
        // 开启服务
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, String.valueOf(Thread.currentThread().getId()));


                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Log.d(TAG, String.valueOf(Thread.currentThread().getId()));
                        Intent intent = new Intent(MyServiceActivity.this, MyService.class);
                        startService(intent);
                    }
                }).start();


            }
        });

        // 停止服务
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyServiceActivity.this, MyService.class);
                stopService(intent);
            }
        });

        // 绑定服务
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                conn = new ServiceConnection() {

                    //Activity与Service断开连接时回调该方法
                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        System.out.println("------Service DisConnected-------");
                    }

                    //Activity与Service连接成功时回调该方法
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        System.out.println("------Service Connected-------");
                        binder = (MyService.MyBinder) service;
                    }
                };

                Intent intent = new Intent(MyServiceActivity.this, MyService.class);
                bindService(intent, conn, Service.BIND_AUTO_CREATE);
            }
        });

        // 解绑服务
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(conn);
            }
        });
    }

    private void setIntentService() {
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it1 = new Intent(MyServiceActivity.this, MyIntentService.class);
                Bundle b1 = new Bundle();
                b1.putString("param", "s1");
                it1.putExtras(b1);

                Intent it2 = new Intent(MyServiceActivity.this, MyIntentService.class);
                Bundle b2 = new Bundle();
                b2.putString("param", "s2");
                it2.putExtras(b2);

                Intent it3 = new Intent(MyServiceActivity.this, MyIntentService.class);
                Bundle b3 = new Bundle();
                b3.putString("param", "s3");
                it3.putExtras(b3);

                //接着启动多次IntentService,每次启动,都会新建一个工作线程
                //但始终只有一个IntentService实例
                startService(it1);
                startService(it2);
                startService(it3);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (null != conn) {
            unbindService(conn);
        }
        stopService(new Intent(MyServiceActivity.this, MyService.class));
    }
}