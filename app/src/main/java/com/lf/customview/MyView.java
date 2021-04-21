package com.lf.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

    // 构造函数1
    // 调用场景：View是在Java代码里面new的
    public MyView(Context context) {
        super(context);
    }

    // 构造函数2
    // 调用场景：View是在.xml里声明的
    // 自定义属性是从AttributeSet参数传进来的
    public  MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // 构造函数3
    // 应用场景：View有style属性时
    // 一般是在第二个构造函数里主动调用；不会自动调用
    public  MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 构造函数4
    // 应用场景：View有style属性时、API21之后才使用
    // 一般是在第二个构造函数里主动调用；不会自动调用
    public  MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
