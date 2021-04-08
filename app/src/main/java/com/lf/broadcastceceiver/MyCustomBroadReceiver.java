package com.lf.broadcastceceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyCustomBroadReceiver extends BroadcastReceiver {

    // 自定义广播
    private final String ACTION_BOOT_2 = "com.example.broadcasttest.MY_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {

        if(ACTION_BOOT_2.equals(intent.getAction())) {
            Toast.makeText(context, "收到告白啦~", Toast.LENGTH_SHORT).show();
        }
    }

}
