package com.app.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lf.main.R;

public class AppBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_base);


    }
}