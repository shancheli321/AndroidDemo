package com.lf.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.app.utils.AppLog;
import com.lf.main.R;

import java.io.File;
import java.util.Calendar;

public class MyActivity extends AppCompatActivity {

    // 1. 生命周期
    //
    //	* onCreate()	这是第一个回调，在活动第一次创建时调用
    //	* onStart()	    这个回调在活动为用户即将可见时被调用
    //	* onResume()	这个回调在应用程序与用户开始可交互的时候调用
    //	* onPause()	    被暂停的活动无法接受用户输入，不能执行任何代码。当前活动将要被暂停，上一个活动将要被恢复时调用
    //	* onStop()	    当活动不在可见时调用
    //	* onDestroy()	当活动被系统销毁之前调用
    //	* onRestart()	当活动被停止以后重新打开时调用


    // 2. 启动模式
    //   standard 标准的默认启动模式，这种模式下activity可以被多次实例化，
    //   即在一个task中可以存在多个activity，每一个activity会处理一个intent对象，
    //   （在A中再次启动A，会存在后面的A在前面的A上面，当前task会存在两个activity的实例对象）
    //
    //   singleTop 如果一个singleTop模式启动的activity实例已经存在于栈顶，
    //   那么再次启动这个activity的时候，不会从新创建实例，而是重用位于栈顶的那个实例，
    //   并且会调用实例的onNewIntent（）方法将对象传递到这个实例中，任务栈中从始至终有且只有一个activity的实例
    //
    //
    //   singleTask  启动singleTask模式的Activity时，会在系统中搜寻是否已经存在一个合适的任务，
    //   若存在，则会将这个任务调度到前台以重用这个任务。
    //   如果这个任务中已经存在一个要启动的Activity的实例，则清除这个实例之上的所有Activity，将这个实例显示给用户。
    //   如果这个已存在的任务中不存在一个要启动的Activity的实例，则在这个任务的顶端启动一个实例。
    //   若这个任务不存在，则会启动一个新的任务，在这个新的任务中启动这个singleTask模式的Activity的一个实例。
    //
    //
    //   singleInstance  总是在新的任务中开始，并且在新的任务中有且只有一个该实例，被该实例启动的activity会自动运行于里一个新的任务栈中，
    //   当再次启动该activity的时候，会重用之前的实例，并且会调用onNewIntent（）方法，将intent传递给该实例



    private static final int REQUEST_CODE_A = 100;
    private static final int REQUEST_CODE_B = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        AppLog.save();

        Button bt = findViewById(R.id.bt_jump);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyActivity.this, MySecondActivity.class);

                // 传多个数据

                // 在使用Bundle传递数据时，要注意，Bundle的大小是有限制的 < 0.5MB，如果大于这个值 是会报TransactionTooLargeException异常的！！！
                Bundle bd = new Bundle();
                bd.putInt("int", 1);
                bd.putString("string", "string");
                intent.putExtra("key", bd);


                // 传单个参数
//                intent.putExtra("key", 123);


                startActivity(intent);
            }
        });


        Button btOther = findViewById(R.id.bt_otherjump);
        btOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MyActivity.this, MySecondActivity.class);

                Bundle bd = new Bundle();
                bd.putInt("int", 1);
                bd.putString("string", "string");
                intent.putExtra("key", bd);
                startActivityForResult(intent, REQUEST_CODE_A);

            }
        });
    }


    // 启动系统自带控制器
    private void gotoSystemActivity() {

        //1.拨打电话
        // 给移动客服10086拨打电话
        Uri uri0 = Uri.parse("tel:10086");
        Intent intent0 = new Intent(Intent.ACTION_DIAL, uri0);
        startActivity(intent0);

//        //2.发送短信
//        // 给10086发送内容为“Hello”的短信
//        Uri uri1 = Uri.parse("smsto:10086");
//        Intent intent1 = new Intent(Intent.ACTION_SENDTO, uri1);
//        intent1.putExtra("sms_body", "Hello");
//        startActivity(intent1);
//
//        //3.发送彩信（相当于发送带附件的短信）
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.putExtra("sms_body", "Hello");
//        Uri uri = Uri.parse("content://media/external/images/media/23");
//        intent.putExtra(Intent.EXTRA_STREAM, uri);
//        intent.setType("image/png");
//        startActivity(intent);
//
//        //4.打开浏览器:
//        // 打开Google主页
//        Uri uri = Uri.parse("http://www.baidu.com");
//        Intent intent  = new Intent(Intent.ACTION_VIEW, uri);
//        startActivity(intent);
//
//        //5.发送电子邮件:(阉割了Google服务的没戏!!!!)
//        // 给someone@domain.com发邮件
//        Uri uri = Uri.parse("mailto:someone@domain.com");
//        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
//        startActivity(intent);
//        // 给someone@domain.com发邮件发送内容为“Hello”的邮件
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.putExtra(Intent.EXTRA_EMAIL, "someone@domain.com");
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
//        intent.putExtra(Intent.EXTRA_TEXT, "Hello");
//        intent.setType("text/plain");
//        startActivity(intent);
//        // 给多人发邮件
//        Intent intent=new Intent(Intent.ACTION_SEND);
//        String[] tos = {"1@abc.com", "2@abc.com"}; // 收件人
//        String[] ccs = {"3@abc.com", "4@abc.com"}; // 抄送
//        String[] bccs = {"5@abc.com", "6@abc.com"}; // 密送
//        intent.putExtra(Intent.EXTRA_EMAIL, tos);
//        intent.putExtra(Intent.EXTRA_CC, ccs);
//        intent.putExtra(Intent.EXTRA_BCC, bccs);
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
//        intent.putExtra(Intent.EXTRA_TEXT, "Hello");
//        intent.setType("message/rfc822");
//        startActivity(intent);
//
//        //6.显示地图:
//        // 打开Google地图中国北京位置（北纬39.9，东经116.3）
//        Uri uri = Uri.parse("geo:39.9,116.3");
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        startActivity(intent);
//
//        //7.路径规划
//        // 路径规划：从北京某地（北纬39.9，东经116.3）到上海某地（北纬31.2，东经121.4）
//        Uri uri = Uri.parse("http://maps.google.com/maps?f=d&saddr=39.9 116.3&daddr=31.2 121.4");
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        startActivity(intent);
//
//        //8.多媒体播放:
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        Uri uri = Uri.parse("file:///sdcard/foo.mp3");
//        intent.setDataAndType(uri, "audio/mp3");
//        startActivity(intent);
//
//        //获取SD卡下所有音频文件,然后播放第一首=-=
//        Uri uri = Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "1");
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        startActivity(intent);
//
//        //9.打开摄像头拍照:
//        // 打开拍照程序
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, 0);
//        // 取出照片数据
//        Bundle extras = intent.getExtras();
//        Bitmap bitmap = (Bitmap) extras.get("data");
//
//        //另一种:
//        //调用系统相机应用程序，并存储拍下来的照片
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        time = Calendar.getInstance().getTimeInMillis();
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment
//                .getExternalStorageDirectory().getAbsolutePath()+"/tucue", time + ".jpg")));
//        startActivityForResult(intent, ACTIVITY_GET_CAMERA_IMAGE);
//
//        //10.获取并剪切图片
//        // 获取并剪切图片
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("image/*");
//        intent.putExtra("crop", "true"); // 开启剪切
//        intent.putExtra("aspectX", 1); // 剪切的宽高比为1：2
//        intent.putExtra("aspectY", 2);
//        intent.putExtra("outputX", 20); // 保存图片的宽和高
//        intent.putExtra("outputY", 40);
//        intent.putExtra("output", Uri.fromFile(new File("/mnt/sdcard/temp"))); // 保存路径
//        intent.putExtra("outputFormat", "JPEG");// 返回格式
//        startActivityForResult(intent, 0);
//        // 剪切特定图片
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setClassName("com.android.camera", "com.android.camera.CropImage");
//        intent.setData(Uri.fromFile(new File("/mnt/sdcard/temp")));
//        intent.putExtra("outputX", 1); // 剪切的宽高比为1：2
//        intent.putExtra("outputY", 2);
//        intent.putExtra("aspectX", 20); // 保存图片的宽和高
//        intent.putExtra("aspectY", 40);
//        intent.putExtra("scale", true);
//        intent.putExtra("noFaceDetection", true);
//        intent.putExtra("output", Uri.parse("file:///mnt/sdcard/temp"));
//        startActivityForResult(intent, 0);
//
//        //11.打开Google Market
//        // 打开Google Market直接进入该程序的详细页面
//        Uri uri = Uri.parse("market://details?id=" + "com.demo.app");
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        startActivity(intent);
//
//        //12.进入手机设置界面:
//        // 进入无线网络设置界面（其它可以举一反三）
//        Intent intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
//        startActivityForResult(intent, 0);
//
//        //13.安装apk:
//        Uri installUri = Uri.fromParts("package", "xxx", null);
//        returnIt = new Intent(Intent.ACTION_PACKAGE_ADDED, installUri);
//
//        //14.卸载apk:
//        Uri uri = Uri.fromParts("package", strPackageName, null);
//        Intent it = new Intent(Intent.ACTION_DELETE, uri);
//        startActivity(it);
//
//        //15.发送附件:
//        Intent it = new Intent(Intent.ACTION_SEND);
//        it.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
//        it.putExtra(Intent.EXTRA_STREAM, "file:///sdcard/eoe.mp3");
//        sendIntent.setType("audio/mp3");
//        startActivity(Intent.createChooser(it, "Choose Email Client"));
//
//        //16.进入联系人页面:
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.setData(People.CONTENT_URI);
//        startActivity(intent);
//
//        //17.查看指定联系人:
//        Uri personUri = ContentUris.withAppendedId(People.CONTENT_URI, info.id);//info.id联系人ID
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.setData(personUri);
//        startActivity(intent);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_A) {
            if (resultCode == RESULT_OK) {

                Bundle bd = data.getBundleExtra("key");
                int va = bd.getInt("key");

                Log.d("onActivityResult--", String.valueOf(va));
            }

        } else if (requestCode == REQUEST_CODE_B) {
            if (resultCode == RESULT_OK) {

                Bundle bd = data.getBundleExtra("key");
                int va = bd.getInt("key");
                Log.d("onActivityResult--", String.valueOf(va));
            }
        }
    }
}