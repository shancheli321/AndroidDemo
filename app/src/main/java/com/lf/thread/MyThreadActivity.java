package com.lf.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
}