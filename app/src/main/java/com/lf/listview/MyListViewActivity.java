package com.lf.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        mListView = findViewById(R.id.m_list);


        List<AppListEntity> list = new ArrayList<>();
        list.add(getEntity("one", "one_content", R.drawable.ic_launcher_background));
        list.add(getEntity("two", "two_content", R.drawable.ic_launcher_background));
        list.add(getEntity("three", "three_content", R.drawable.ic_launcher_background));

        adapter = new AppBaseListAdapter<AppListEntity>(list, R.layout.my_list_layout) {
            @Override
            public void bindData(AppBaseListViewHolder holder, AppListEntity entity) {

                ((ImageView)holder.getView(R.id.list_image)).setImageResource(entity.getImage());
                ((TextView)holder.getView(R.id.list_title)).setText(entity.getTitle());
                ((TextView)holder.getView(R.id.list_content)).setText(entity.getContent());

            }
        };

        mListView.setAdapter(adapter);

//        mListView.setEmptyView();

    }


    /**
     * 刷新某一条数据
     * @param itemIndex
     */
    private void updateView(int itemIndex) {

        //得到第一个可显示控件的位置，
        int firstPos = mListView.getFirstVisiblePosition();
        int lastPos = mListView.getLastVisiblePosition();

        //只有当要更新的view在可见的位置时才更新，不可见时，跳过不更新
        //可见才更新，不可见则在getView()时更新
        if (itemIndex >= firstPos && itemIndex <= lastPos) {

            //得到要更新的item的view
            View view = mListView.getChildAt(itemIndex - firstPos);

            //调用adapter更新界面
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