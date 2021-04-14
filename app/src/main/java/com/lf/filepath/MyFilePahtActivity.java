package com.lf.filepath;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lf.main.R;

public class MyFilePahtActivity extends AppCompatActivity {


    // 一. 内部存储
    //          内部存储位于data／data／包名／路径下
    //          系统默认只在内部存储中创建cache目录，并且此时cache目录是空的
    //          getSharedPreferences()会让系统在内部存储中创建shared_prefs目录，但不会创建sp文件,只有在sp文件中存储了值，系统才会创建sp文件
    //          sp文件位于shared_prefs目录下

    //      1. Environment.getDataDirectory()	 /data
    //      2. Environment.getDownloadCacheDirectory()	  /cache
    //      3. Environment.getRootDirectory()	      /system




    // 二、 外部存储  (外部存储又分为SD卡和扩展卡内存)

    // 1.  APP私有目录   /storage/emulated/0/Android/data/cwj.test(包名)/files/test
    //    一般情况下有包名的路径我们都是调用Context中的方法来获得，没有包名的路径，我们直接调用Environment中的方法获得。
    //              1). getExternalFilesDir()      /storage/emulated/0/Android/data/cwj.test(包名)/files/test
    //              2). getExternalCacheDir	    /storage/emulated/0/Android/data/cwj.test(包名)/cache/test

    // 2. 扩展卡内存 (插入的外置SD卡)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_file_paht);



    }
}