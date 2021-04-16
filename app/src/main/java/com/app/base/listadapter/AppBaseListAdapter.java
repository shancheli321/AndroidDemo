package com.app.base.listadapter;


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class AppBaseListAdapter<T> extends BaseAdapter {

    private List<T> mData;

    private int mLayoutRes;           //布局id


    public AppBaseListAdapter() {

    }

    public AppBaseListAdapter(List<T> mData, int mLayoutRes) {
        this.mData = mData;
        this.mLayoutRes = mLayoutRes;
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AppBaseListViewHolder holder = AppBaseListViewHolder.bind(parent.getContext(), convertView, parent, mLayoutRes
                , position);
        bindData(holder, getItem(position));
        return holder.getItemView();
    }

    public abstract void bindData(AppBaseListViewHolder holder, T entity);


    /**
     * 局部刷新
     * @param view
     * @param itemIndex
     */
    public void updateView(View view, int itemIndex) {

         if(view == null) {
             return;
         }
         AppBaseListViewHolder holder = (AppBaseListViewHolder)view.getTag();
        bindData(holder, mData.get(itemIndex));
    }



    //添加一个元素
    public void add(T data) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.add(data);
        notifyDataSetChanged();
    }

    //往特定位置，添加一个元素
    public void add(int position, T data) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.add(position, data);
        notifyDataSetChanged();
    }

    public void remove(T data) {
        if (mData != null) {
            mData.remove(data);
        }
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if (mData != null) {
            mData.remove(position);
        }
        notifyDataSetChanged();
    }

    public void clear() {
        if (mData != null) {
            mData.clear();
        }
        notifyDataSetChanged();
    }

}
