package com.lf.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lf.main.R;

public class MySecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_second);


        getData();

        Button bt = findViewById(R.id.bt_jump_back);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setBackData();

            }
        });

    }


    private void getData() {

        Intent intent = getIntent();

//        int keyString = intent.getIntExtra("key", 0);

        Bundle bd = intent.getBundleExtra("key");
        String string = bd.getString("string");
        int va = bd.getInt("int");
        Log.d("onActivityResult--", String.valueOf(va));

    }


    private void setBackData() {

        Intent intent = getIntent();

        Bundle bd = new Bundle();

        bd.putInt("key", 1234);

        intent.putExtra("key", bd);

        setResult(RESULT_OK, intent);

        finish();;

    }


}