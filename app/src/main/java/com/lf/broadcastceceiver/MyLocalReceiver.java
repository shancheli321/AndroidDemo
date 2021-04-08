package com.lf.broadcastceceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyLocalReceiver extends BroadcastReceiver {

    private final String action = "com.example.broadcasttest.MY_LOCAL_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (action.equals(intent.getAction())) {
            Toast.makeText(context, "本地广播~", Toast.LENGTH_LONG).show();
        }

    }
}
