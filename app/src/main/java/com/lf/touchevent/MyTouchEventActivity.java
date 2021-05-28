package com.lf.touchevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.lf.main.R;



public class MyTouchEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_touch_event);
    }


    // （1）dispatchTouchEvent(MotionEvent event)
    //决定touch事件是否派发。在View和Activity中都有这个方法。
    //
    //（2）onTouchEvent(MotionEvent event)
    //如果返回true，则表示这个事件被消费掉，如果返回false则将事件向上一层父容器传递。
    //
    //（3）onInterceptTouchEvent(MotionEvent ev)
    //是否拦截touch事件，如果拦截，则不传递事件到子View，否则事件继续传递给子View，所以这个接口只有ViewGroup的派生类才有，View是没有该接口的。


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return super.onKeyUp(keyCode, event);
    }
}