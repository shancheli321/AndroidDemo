package com.lf.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.base.listadapter.AppBaseListAdapter;
import com.app.base.listadapter.AppBaseListViewHolder;
import com.lf.main.R;

import java.util.ArrayList;
import java.util.List;

public class MyListViewActivity extends AppCompatActivity {

    private ListView mListView;

    AppBaseListAdapter<AppListEntity> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list_view);

        final List<AppListEntity> list = new ArrayList<>();

        mListView = findViewById(R.id.m_list);

        adapter = new AppBaseListAdapter<AppListEntity>(list, R.layout.my_list_layout) {
            @Override
            public void bindData(AppBaseListViewHolder holder, AppListEntity entity) {

                ((ImageView)holder.getView(R.id.list_image)).setImageResource(entity.getImage());
                ((TextView)holder.getView(R.id.list_title)).setText(entity.getTitle());
                ((TextView)holder.getView(R.id.list_content)).setText(entity.getContent());

            }
        };

        list.add(getEntity("one", "one_content", R.drawable.ic_launcher_background));
        list.add(getEntity("two", "two_content", R.drawable.ic_launcher_background));
        list.add(getEntity("three", "three_content", R.drawable.ic_launcher_background));


        LayoutInflater inflater = LayoutInflater.from(MyListViewActivity.this);
        View headerView = inflater.inflate(R.layout.list_header_layout, null);

        TextView add = headerView.findViewById(R.id.header_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add(getEntity("add", "add_content", R.drawable.ic_launcher_background));
            }
        });

        TextView dele = headerView.findViewById(R.id.header_delete);
        dele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.remove(0);
            }
        });

        TextView update = headerView.findViewById(R.id.header_update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateView(0);
            }
        });


        mListView.setAdapter(adapter);

        mListView.addHeaderView(headerView);

//        mListView.setEmptyView();

    }


    /**
     * ?????????????????????
     * @param itemIndex
     */
    private void updateView(int itemIndex) {

        //??????????????????????????????????????????
        int firstPos = mListView.getFirstVisiblePosition();
        int lastPos = mListView.getLastVisiblePosition();

        //?????????????????????view???????????????????????????????????????????????????????????????
        //?????????????????????????????????getView()?????????
        if (itemIndex >= firstPos && itemIndex <= lastPos) {

            //??????????????????item???view
            View view = mListView.getChildAt(itemIndex + 1 - firstPos);

            //??????adapter????????????
            adapter.updateView(view, itemIndex);
        }
    }


    private AppListEntity getEntity(String title, String content, int image) {
        AppListEntity entity = new AppListEntity();

        entity.setTitle(title);
        entity.setContent(content);
        entity.setImage(image);

        return entity;
    }
}