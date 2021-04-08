package com.lf.broadcastceceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBRReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context,"网络状态发生改变~",Toast.LENGTH_SHORT).show();

    }
}
