package com.lf.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyTouchView extends View {

    private static final String TAG = MyTouchView.class.getSimpleName();

    public MyTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "TouchView onTouchEvent action=" + event.getAction());

        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "TouchView dispatchTouchEvent action=" + event.getAction());

        return super.dispatchTouchEvent(event);
    }

}
