package com.lf.customview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.app.appview.AppMoreText;
import com.lf.main.R;

public class MyCustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_custom_view);


        TextView moretext = findViewById(R.id.more_tv);

        AppMoreText.getInstance().getLastIndexForLimit(moretext, 2, "测试查看更多查看更多测试查看更多查看更多测试查看更多查看更多测试查看更多查看更多测试查看更多查看更多测试查看更多查看更多测试查看更多查看更多测试查看更多查看更多测试查看更多查看更多测试查看更多查看更多测试查看更多查看更多测试查看更多查看更多测试查看更多查看更多测试查看更多查看更多测试查看更多查看更多测试查看更多查看更多");

    }
}