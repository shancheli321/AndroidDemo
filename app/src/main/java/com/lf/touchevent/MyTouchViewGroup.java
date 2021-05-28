package com.lf.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class MyTouchViewGroup extends FrameLayout {

    private static final String TAG = MyTouchViewGroup.class.getSimpleName();

    public MyTouchViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "TouchVIewGroup dispatchTouchEvent action=" + ev.getAction());

        boolean dispatch = super.dispatchTouchEvent(ev);

        return dispatch;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "TouchVIewGroup onTouchEvent action=" + event.getAction());

        return super.onTouchEvent(event);
    }

}
