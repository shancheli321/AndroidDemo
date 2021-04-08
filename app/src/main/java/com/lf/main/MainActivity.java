package com.lf.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lf.activity.MyActivity;
import com.lf.broadcastceceiver.MyBroadcastReceiverActivity;
import com.lf.service.MyServiceActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btService = findViewById(R.id.bt_service);
        btService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MyServiceActivity.class);
                startActivity(intent);

            }
        });

        Button btLayout = findViewById(R.id.bt_layout);
        btLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button btActivity = findViewById(R.id.bt_activity);
        btActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.bt_brodReciver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyBroadcastReceiverActivity.class);
                startActivity(intent);
            }
        });
    }
}