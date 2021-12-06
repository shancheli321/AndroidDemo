package com.app.base.listener;

import android.view.View;
import android.widget.AdapterView;

public abstract class AppClick {

    // 间隔时间
    private final static int INTERVAL_TIME = 1000;

    //上一次点击时间
    static long lastclicktime = 0;


    public abstract class OnClickListener implements View.OnClickListener {

        public abstract void onCustomClick(View view);

        @Override
        public void onClick(View view) {

            if (System.currentTimeMillis() - lastclicktime > INTERVAL_TIME) {

                lastclicktime = System.currentTimeMillis();
                return;
            }

            onCustomClick(view);
        }

    }


    public abstract class OnLongClickListener implements View.OnLongClickListener {

        public abstract boolean onCustomLongClick(View view);

        @Override
        public boolean onLongClick(View view) {

            if (System.currentTimeMillis() - lastclicktime > INTERVAL_TIME) {

                lastclicktime = System.currentTimeMillis();
                return true;
            }

            return onCustomLongClick(view);
        }
    }


    public abstract class OnItemClickListener implements AdapterView.OnItemClickListener {

        public abstract void onCustomItemClick(AdapterView<?> adapterView, View view, int i, long l);

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            if (System.currentTimeMillis() - lastclicktime > INTERVAL_TIME) {

                lastclicktime = System.currentTimeMillis();
                return;
            }

            onCustomItemClick(adapterView, view, i, l);
        }

    }

}
