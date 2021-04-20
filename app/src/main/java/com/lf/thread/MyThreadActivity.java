package com.lf.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.lf.main.R;


// Android提供了四种常用的操作多线程的方式，分别是：
//      1. Handler+Thread
//      2. AsyncTask
//      3. ThreadPoolExecutor
//      4. IntentService


public class MyThreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_thread);
    }


    /**
     * thread方式启动一个子线程
     */
    private void thread() {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }

    /**
     * AsyncTask方式启动线程
     */
    private void asyncTask() {
        MyTask task = new MyTask();
        task.execute();
    }


    /**
     * send方式handler
     */
    private void handlerSendMessaege() {
        Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {

                // 主线程执行
            }
        };


        Message msg = Message.obtain(); // 实例化消息对象
        msg.what = 1; // 消息标识
        msg.obj = "AA"; // 消息内容存放

        handler.sendMessage(msg);
    }


    /**
     * post 方式的handler
     */
    private void handlerPost() {
        Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {

                // 主线程执行
            }
        });
    }

    private void intentService() {
        Intent intent = new Intent(MyThreadActivity.this, MyIntentService.class);

        Bundle bundle = new Bundle();
        bundle.putString("task1","openlight");
        intent.putExtra("key", bundle);
        startService(intent);
    }

}