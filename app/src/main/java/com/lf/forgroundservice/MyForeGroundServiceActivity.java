package com.lf.forgroundservice;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lf.main.R;
import com.lf.service.MyService;
import com.lf.service.MyServiceActivity;

public class MyForeGroundServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fore_ground_service2);

        Button btn = findViewById(R.id.start_fore_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MyForeGroundServiceActivity.this, MyForeGroundService.class);
                startForegroundService(intent);

            }
        });
    }
}