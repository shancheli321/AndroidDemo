package com.lf.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.app.utils.AppLog;
import com.app.base.listadapter.AppBaseListAdapter;
import com.app.base.listadapter.AppBaseListViewHolder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private ListView mListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.ls_main);


        final ArrayList<MyMainEntity> arrayList = new ArrayList<>();
//        arrayList.add(makeEntity("layout和view控件", ""));
        arrayList.add(makeEntity("service服务", "com.lf.service.MyServiceActivity"));
        arrayList.add(makeEntity("activity", "com.lf.activity.MyActivity"));
        arrayList.add(makeEntity("通知 广播", "com.lf.broadcastceceiver.MyBroadcastReceiverActivity"));
        arrayList.add(makeEntity("文件管理", "com.lf.filepath.MyFilePahtActivity"));
        arrayList.add(makeEntity("推送通知栏", "com.lf.notification.MyNotificationActivity"));
        arrayList.add(makeEntity("alert弹窗", "com.lf.alert.MyAlertActivity"));

        AppBaseListAdapter<MyMainEntity> adapter = new MyAdapter(arrayList, R.layout.main_item);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MyMainEntity entity = arrayList.get(position);
                try {
                    Class nameClass = Class.forName(entity.getName());

                    AppLog.d(entity.getName());
                    Intent intent = new Intent(MainActivity.this, nameClass);
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }




    private MyMainEntity makeEntity(String title, String name) {
        MyMainEntity entity = new MyMainEntity();
        entity.setTitle(title);
        entity.setName(name);
        return entity;
    }



    public class MyAdapter extends AppBaseListAdapter<MyMainEntity> {

        public MyAdapter(ArrayList<MyMainEntity> mData, int mLayoutRes) {
            super(mData, mLayoutRes);
        }


        @Override
        public void bindView(AppBaseListViewHolder holder, MyMainEntity obj) {
            holder.setText(R.id.tv_main, obj.getTitle());

        }


    }
}