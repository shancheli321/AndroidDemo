package com.app.base.listadapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class AppBaseListViewHolder {

    private SparseArray<View> mViews;   //存储ListView 的 item中的View

    private View itemView;                  //存放convertView

    private int position;               //游标

    private Context context;            //Context上下文


    //构造方法，完成相关初始化
    private AppBaseListViewHolder(Context context, ViewGroup parent, int layoutRes) {
        mViews = new SparseArray<>();
        this.context = context;
        View convertView = LayoutInflater.from(context).inflate(layoutRes, parent, false);
        convertView.setTag(this);
        itemView = convertView;
    }

    //绑定ViewHolder与item
    public static AppBaseListViewHolder bind(Context context, View convertView, ViewGroup parent,
                                             int layoutRes, int position) {
        AppBaseListViewHolder holder;
        if (convertView == null) {
            holder = new AppBaseListViewHolder(context, parent, layoutRes);
        } else {
            holder = (AppBaseListViewHolder) convertView.getTag();
            holder.itemView = convertView;
        }
        holder.position = position;
        return holder;
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int id) {
        T t = (T) mViews.get(id);
        if (t == null) {
            t = (T) itemView.findViewById(id);
            mViews.put(id, t);
        }
        return t;
    }


    /**
     * 获取当前条目
     */
    public View getItemView() {
        return itemView;
    }

    /**
     * 获取条目位置
     */
    public int getItemPosition() {
        return position;
    }

    /**
     * 设置文字
     */
    public AppBaseListViewHolder setText(int id, CharSequence text) {
        View view = getView(id);
        if (view instanceof TextView) {
            ((TextView) view).setText(text);
        }
        return this;
    }

    /**
     * 设置图片
     */
    public AppBaseListViewHolder setImageResource(int id, int drawableRes) {
        View view = getView(id);
        if (view instanceof ImageView) {
            ((ImageView) view).setImageResource(drawableRes);
        } else {
            view.setBackgroundResource(drawableRes);
        }
        return this;
    }


    /**
     * 设置点击监听
     */
    public AppBaseListViewHolder setOnClickListener(int id, View.OnClickListener listener) {
        getView(id).setOnClickListener(listener);
        return this;
    }

    /**
     * 设置可见
     */
    public AppBaseListViewHolder setVisibility(int id, int visible) {
        getView(id).setVisibility(visible);
        return this;
    }

    /**
     * 设置标签
     */
    public AppBaseListViewHolder setTag(int id, Object obj) {
        getView(id).setTag(obj);
        return this;
    }

    //其他方法可自行扩展

}
