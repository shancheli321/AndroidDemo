package com.lf.filepath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;

import com.lf.main.R;

public class MyFilePahtActivity extends AppCompatActivity {

    //    数据存储：
    //          1.内存
    //          2.内存设备--->应用名--->（cache/files/其他名字）：用的少
    //          3.内存设备--->Android--->data--->应用名--->（cache/files/其他名字）：用的多
    //          4.sd卡--->应用名--->（files/cache/其他名字）：用得少
    //          5.sd卡--->Android--->data--->应用名--->（cache/files/其他名字）：用的多
    //





    // 一. 内部存储
    //          内部存储位于data／data／包名／路径下

    //          系统默认只在内部存储中创建cache目录，并且此时cache目录是空的
    //          getSharedPreferences()会让系统在内部存储中创建shared_prefs目录，但不会创建sp文件,只有在sp文件中存储了值，系统才会创建sp文件
    //          sp文件位于shared_prefs目录下
    //
    //      Environment.getDataDirectory():      /data
    //      Context.getCacheDir():                    /data/data/com.learn.test/cache
    //      Context.getFilesDir():                      /data/data/com.learn.test/files
    //      Context.getFileStreamPath(""):         /data/data/com.learn.test/files
    //      Context.getFileStreamPath("test"):   /data/data/com.learn.test/files/test




    // 二、 外部存储  (外部存储又分为SD卡和扩展卡内存)
    //       /storage/emulated/0

    // 公共存储目录:
    //        我们可以在外部存储上新建任意文件夹，不过在6.0及之后的系统需要动态申请权限，这些目录的内容不会随着应用的卸载而消失。如：
    //          Environment.getExternalStorageDirectory():                        /storage/emulated/0
    //          Environment.getExternalStoragePublicDirectory(""):             /storage/emulated/0
    //          Environment.getExternalStoragePublicDirectory("test"):        /storage/emulated/0/test
    //          Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)：  /storage/emulated/0/Pictures

    // 假设我们的应用程序包名为com.learn.test，路径如下：
    //      Environment.getExternalStorageDirectory():            /storage/emulated/0
    //      Context.getExternalCacheDir():                              /storage/emulated/0/Android/data/com.learn.test/cache
    //      Context.getExternalFilesDir(""):                             /storage/emulated/0/Android/data/com.learn.test/files
    //      Context.getExternalFilesDir("test"):                        /storage/emulated/0/Android/data/com.learn.test/files/test
    //      Context.getExternalFilesDir(Environment.DIRECTORY_PICTURES):    /storage/emulated/0/Android/data/com.learn.test/files/Pictures


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_file_paht);




        getFilesPath(MyFilePahtActivity.this);
        getCachePath(MyFilePahtActivity.this);

    }


    /**
     * 函数返回路径/storage/emulated/0/Android/data/包名/files
     *
     * 用来存储一些长时间保留的数据,应用卸载会被删除
     *
     * 对应 设置->应用->应用详情里面的”清除数据“
     *
     *
     * 加External和不加的比较:
     *
     * 相同点：
     *
     *          1. 都可以做app缓存目录。
     *
     *          2. app卸载后，两个目录下的数据都会被清空。
     *
     * 不同点:
     *
     *          1、目录的路径不同。前者的目录存在外部SD卡上的。后者的目录存在app的内部存储上。
     *
     *          2、前者的路径在手机里可以直接看到。后者的路径需要root以后，用Root Explorer 文件管理器才能看到。
     *
     * @param context
     * @return
     */
    public String getFilesPath(Context context) {
         String filePath ;
         if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
             //外部存储可用
             filePath = context.getExternalFilesDir(null).getPath();
         } else {
             //外部存储不可用
             filePath = context.getFilesDir().getPath() ;
         }
        return filePath ;
    }


    /**
     *
     * 函数返回路径/storage/emulated/0/Android/data/包名/cache
     * /sdcard/Android/data/<application package>/cache
     *
     * /data/data/<application package>/cache
     *
     * 用来存储一些临时缓存数据
     *
     * 对应 设置->应用->应用详情里面的”清除缓存“选项
     *
     * @param context
     * @return
     */
    public String getCachePath( Context context ) {
         String cachePath ;
         if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            //外部存储可用
            cachePath = context.getExternalCacheDir().getPath() ;
         } else {
            //外部存储不可用
            cachePath = context.getCacheDir().getPath() ;
         }
         return cachePath ;
    }


}