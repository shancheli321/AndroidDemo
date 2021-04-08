package com.lf.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.IBinder;
import android.util.Log;


// 1. 作用
// 当一个后台的任务,需要分成几个子任务,然后按先后顺序执行,子任务 (简单的说就是异步操作),
// 此时如果我们还是定义一个普通Service然后 在onStart方法中开辟线程,然后又要去控制线程,这样显得非常的繁琐;
// 此时应该自定义一个IntentService然后再onHandleIntent()方法中完成相关任务！


// 2. 工作流程
// 客户端通过startService(Intent)来启动IntentService;
// 我们并不需要手动地区控制IntentService,当任务执行完后,IntentService会自动停止;
// 可以启动IntentService多次,每个耗时操作会以工作队列的方式在IntentService的
// onHandleIntent回调方法中执行,并且每次只会执行一个工作线程,执行完一，再到二这样!

public class MyIntentService extends IntentService {

    private final String TAG = "MyIntentService";



    private static final String ACTION_FOO = "com.lf.service.action.FOO";
    private static final String ACTION_BAZ = "com.lf.service.action.BAZ";

    private static final String EXTRA_PARAM1 = "com.lf.service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.lf.service.extra.PARAM2";


    public MyIntentService() {
        super("MyIntentService");
    }


    /**
     * 启动服务
     */
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }




    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onCreate() {
        Log.i(TAG,"onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    //重写其他方法,用于查看方法的调用顺序
    @Override
    public IBinder onBind(Intent intent) {

        Log.i(TAG,"onBind");
        return super.onBind(intent);
    }

    @Override
    public void setIntentRedelivery(boolean enabled) {
        super.setIntentRedelivery(enabled);
        Log.i(TAG,"setIntentRedelivery");
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"onDestroy");
        super.onDestroy();
    }

}
