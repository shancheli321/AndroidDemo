package com.app.utils.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lf.main.R;

import java.util.Timer;
import java.util.TimerTask;


/**
 * 1.toast的显示时间只有两种可能。我们查看源码可以得知它只有2秒和3.5秒
 */
public class AppToastUtil {


    /**
     * 带显示时长的toast
     * @param context
     * @param text
     */
    public static void showText(Context context, String text) {
        TimeToast.makeText(context, text, 5).show();
    }

    /**
     * 显示short message
     * @param context 全局context
     * @param resId string string资源id
     */
    public static void showShort(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示short message
     * @param context 全局context
     * @param message 显示msg
     */
    public static void showShort(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示long message
     * @param context 全局context
     * @param resId string string资源id
     */
    public static void showLong(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }

    /**
     * 显示long message
     * @param context 全局context
     * @param message 显示msg
     */
    public static void showLong(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }


    /**
     * 自定义弹窗
     * @param context
     * @param str
     * @param showTime
     * @return
     */
    private Toast midToast(Context context, String str, int showTime) {

        Toast toast = Toast.makeText(context, str, showTime);

        //设置显示位置
        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL , 0, 0);

        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);

        //设置字体颜色
        v.setTextColor(Color.YELLOW);

        return toast;
    }



    /**
     * 自定义 文字+图片 弹窗
     * @param context
     * @param str
     */
    private static Toast customToast(Context context, String str)  {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.app_toast_custom_layout, null);

        ImageView img_logo = (ImageView) view.findViewById(R.id.img_logo);
        TextView tv_msg = (TextView) view.findViewById(R.id.tv_msg);
        tv_msg.setText(str);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);

        return toast;
    }


    public static class TimeToast {

        //定义的显示时间
        private double time;

        private static Handler handler;

        //显示的计时器
        private Timer showTimer;

        //取消的计时器
        private Timer cancelTimer;

        private Toast toast;

        private TimeToast(){
            showTimer = new Timer();
            cancelTimer = new Timer();
        }
        public void setTime(double time) {
            this.time = time;
        }
        public void setToast(Toast toast){
            this.toast = toast;
        }

        public static TimeToast makeText(Context context, String text, double time){
            TimeToast toast1= new TimeToast();
            toast1.setTime(time);
            toast1.setToast(Toast.makeText(context, text, Toast.LENGTH_SHORT));
            handler = new Handler(context.getMainLooper());
            return toast1;
        }
        public void show(){
            toast.show();
            if(time > 2){
                showTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(new ShowRunnable());
                    }
                }, 0, 1900);
            }
            cancelTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new CancelRunnable());
                }
            }, (long)(time * 1000));
        }
        private class CancelRunnable implements Runnable{
            @Override
            public void run() {
                showTimer.cancel();
                toast.cancel();
            }
        }
        private class ShowRunnable implements Runnable{
            @Override
            public void run() {
                toast.show();
            }
        }
    }
}
