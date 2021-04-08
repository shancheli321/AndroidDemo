package com.lf.broadcastceceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyStaticBroadReceiver extends BroadcastReceiver {

    private final String ACTION_BOOT = "android.intent.action.BOOT_COMPLETED";




    @Override
    public void onReceive(Context context, Intent intent) {

        if (ACTION_BOOT.equals(intent.getAction())) {
            Toast.makeText(context, "开机完毕~", Toast.LENGTH_LONG).show();

        }
    }
}
