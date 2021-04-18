package com.app.base.recycleradapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AppBaseRecyclerViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;

    private View mConvertView;

    public AppBaseRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        mConvertView = itemView;
        mViews = new SparseArray<>();
    }


    public static AppBaseRecyclerViewHolder getHolder(ViewGroup parent, int layoutId) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new AppBaseRecyclerViewHolder(convertView);
    }

    public <T extends View> T getView(int id) {
        View v = mViews.get(id);
        if (v == null) {
            v = mConvertView.findViewById(id);
            mViews.put(id, v);
        }
        return (T)v;
    }

    public void setText(int id, String value) {
        TextView view = getView(id);
        view.setText(value);
    }


}
