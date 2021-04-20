package com.lf.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;

import com.lf.main.R;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_timer);



        // 三种定时
        handlerTimer();
        timer();
        timerCountDownTimer();

        // 三种延时
        delayHandler();
        delayTimer();
        delayThread();


    }


    private void handlerTimer() {


        final Handler timerHandler = new Handler();

        Runnable myTimerRun = new Runnable() {

            @Override
            public void run() {

                //要做的事情这里

                //再次调用myTimerRun对象，实现每两秒一次的定时器操作
                timerHandler.postDelayed(this, 2000);

            }
        };

        //使用postDelayed方法，两秒后再调用此myTimerRun对象
        timerHandler.postDelayed(myTimerRun, 2000);


        //调用此方法，以关闭此定时器
        timerHandler.removeCallbacks(myTimerRun);

    }


    private void timer() {

        Timer timer = new Timer();

        //创建定时器任务对象，必须实现run方法，在该方法中定义用户任务
        TimerTask task = new TimerTask() {

            @Override
            public void run() {

            }
        };

        timer.schedule(task,0,10000);


        timer.cancel();

    }


    private void timerCountDownTimer() {

        //参数1：计时总时间，参数2：每次扣除时间数
        CountDownTimer countDownTimer = new CountDownTimer(10000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

            }
        };

        countDownTimer.start();

        countDownTimer.cancel();
    }



    private void delayHandler() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                //do something
            }
        }, 1000);    //延时1s执行
    }


    private void delayTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //do something
            }
        },1000);//延时1s执行
    }

    private void delayThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);//延时1s
                    //do something
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}