package com.lf.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.lf.activity.MyActivity;
import com.lf.broadcastceceiver.MyBroadcastReceiverActivity;
import com.lf.listview.AppAdapter;
import com.lf.listview.AppViewHolder;
import com.lf.service.MyServiceActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.ls_main);


        ArrayList<MyMainEntity> arrayList = new ArrayList<>();
        arrayList.add(makeEntity("layout和view控件", ""));
        arrayList.add(makeEntity("service服务", "MyServiceActivity"));
        arrayList.add(makeEntity("activity", "MyActivity"));
        arrayList.add(makeEntity("通知 广播", "MyBroadcastReceiverActivity"));


        AppAdapter<MyMainEntity> adapter = new AppAdapter<MyMainEntity>(arrayList, R.layout.main_item) {
            @Override
            public void bindView(AppViewHolder holder, MyMainEntity obj) {

                holder.setText(R.id.tv_main, obj.getTitle());
            }
        };

        mListView.setAdapter(adapter);


    }




    private MyMainEntity makeEntity(String title, String name) {
        MyMainEntity entity = new MyMainEntity();
        entity.setTitle(title);
        entity.setName(name);
        return entity;
    }
}