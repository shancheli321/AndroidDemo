package com.app.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lf.main.R;

public abstract class AppBaseFragment extends Fragment {

    protected Activity mActivity;

    protected View mView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mActivity = getActivity();

        mView = initContentView(inflater, container);

        bindView();

        return mView;

    }


    /**
     * 查找子view
     * @param id
     * @param <VT>
     * @return
     */
    protected <VT extends View> VT searchViewById(int id) {
        if (mView == null) {
            return null;
        }

        VT view = mView.findViewById(id);

        return view;
    }


    /**
     * 初始化 内容view
     * @param inflater
     * @param container
     * @return
     */
    protected abstract View initContentView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container);


    /**
     * 绑定数据
     */
    protected abstract void bindView();

}
