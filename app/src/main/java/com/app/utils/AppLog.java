package com.app.utils;

import android.content.Context;
import android.os.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import me.pqpo.librarylog4a.Level;
import me.pqpo.librarylog4a.Log4a;
import me.pqpo.librarylog4a.LogData;
import me.pqpo.librarylog4a.logger.AppenderLogger;
import me.pqpo.librarylog4a.appender.AndroidAppender;
import me.pqpo.librarylog4a.appender.FileAppender;
import me.pqpo.librarylog4a.formatter.DateFileFormatter;
import me.pqpo.librarylog4a.interceptor.Interceptor;


public class AppLog {

    public static final int BUFFER_SIZE = 1024 * 400; //400k

    public static void init(Context context) {

        int level = Level.DEBUG;
        Interceptor wrapInterceptor = new Interceptor() {
            @Override
            public boolean intercept(LogData logData) {
                logData.tag = "Log4a-" + logData.tag;
                return true;
            }
        };
        AndroidAppender androidAppender = new AndroidAppender.Builder()
                .setLevel(level)
                .addInterceptor(wrapInterceptor)
                .create();

        // log文件
        File log = context.getExternalFilesDir("logs");
        if (log == null) {
            log = new File(context.getFilesDir(), "logs");
        }
        if (!log.exists()) {
            log.mkdir();
        }

        String buffer_path = log.getAbsolutePath() + File.separator + ".logCache";
        String time = new SimpleDateFormat("yyyy_MM_dd", Locale.getDefault()).format(new Date());
        String log_path = log.getAbsolutePath() + File.separator + time + ".txt";
        FileAppender fileAppender = new FileAppender.Builder(context)
                .setLogFilePath(log_path)
                .setLevel(level)
                .addInterceptor(wrapInterceptor)
                .setBufferFilePath(buffer_path)
                .setFormatter(new DateFileFormatter())
                .setCompress(false)
                .setBufferSize(BUFFER_SIZE)
                .create();

        AppenderLogger logger = new AppenderLogger.Builder()
                .addAppender(androidAppender)
                .addAppender(fileAppender)
                .create();
        Log4a.setLogger(logger);
    }


    public static void save() {
        Log4a.flush();
    }

    public static void release() {
        Log4a.release();
    }

    public static void d(String tag, String string) {
        Log4a.d(tag, string);
    }

    public static void d(String string) {
        Log4a.d("AppLog---", string);
    }

    public static void e(String tag, String string) {
        Log4a.e(tag, string);
    }

    public static void e(String string) {
        Log4a.e("AppLog---", string);
    }

    public static void i(String tag, String string) {
        Log4a.i(tag, string);
    }

    public static void i(String string) {
        Log4a.i("AppLog---", string);
    }
}
