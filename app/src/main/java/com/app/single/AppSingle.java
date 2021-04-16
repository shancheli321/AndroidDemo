package com.app.single;

import android.content.Context;

public class AppSingle {
    
    private static AppSingle instance = new AppSingle();
    
    private Context mContext;
    
    public AppSingle() {
        
    }
    
    public synchronized static AppSingle getInstance() {
        return instance;
    }


    public void setContext(Context context) {
        this.mContext = mContext;
    }

    public Context getContext() {
        return mContext;
    }
}
