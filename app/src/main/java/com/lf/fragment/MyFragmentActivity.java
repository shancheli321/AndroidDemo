package com.lf.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.app.base.activity.AppBaseActivity;
import com.app.base.fragment.AppBaseFragment;
import com.lf.main.R;


// onAttach()：Fragment和Activity相关联时调用。可以通过该方法获取Activity引用，还可以通过getArguments()获取参数。
//onCreate()：Fragment被创建时调用。
//onCreateView()：创建Fragment的布局。
//onActivityCreated()：当Activity完成onCreate()时调用。
//onStart()：当Fragment可见时调用。
//onResume()：当Fragment可见且可交互时调用。
//onPause()：当Fragment不可交互但可见时调用。
//onStop()：当Fragment不可见时调用。
//onDestroyView()：当Fragment的UI从视图结构中移除时调用。
//onDestroy()：销毁Fragment时调用。
//onDetach()：当Fragment和Activity解除关联时调用。
//
// 上面的方法中，只有onCreateView()在重写时不用写super方法，其他都需要。


// 1.  Fragment的onAttach()->onCreate()->onCreateView()->onActivityCreated()->onStart()都是在Activity的onStart()中调用的。
// 2.  Fragment的onResume()在Activity的onResume()之后调用。




public class MyFragmentActivity extends AppBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragment);

    }


    /**
     * 动态加载fragment
     */
    @SuppressLint("ResourceType")
    private void loadFragment() {

        //[1]获取手机的分辨率
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        FragmentManager fragmentManager  = getSupportFragmentManager();

        //开启事务
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        AppBaseFragment fragment1 = new AppBaseFragment() {
            @Override
            protected View initContentView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
                return null;
            }

            @Override
            protected void bindView() {

            }
        };

        //判断横竖
        if (height > width) {
            //说明是竖屏  加载一个fragment
            beginTransaction.replace(android.R.id.content, fragment1);


        } else {

            //说明是横屏
            beginTransaction.replace(android.R.id.content, fragment1);
        }

        //最后一步 记得commit
        beginTransaction.commit();

    }
}