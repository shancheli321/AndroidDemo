package com.lf.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.base.recycleradapter.AppBaseRectcleAdapter;
import com.app.base.recycleradapter.AppBaseRecyclerViewHolder;
import com.lf.listview.AppListEntity;
import com.lf.listview.MyListViewActivity;
import com.lf.main.R;

import java.util.ArrayList;
import java.util.List;


/*

参考 ：https://www.jianshu.com/p/4f9591291365

一、 RecyclerView的四大组成是：

        Layout Manager：Item的布局。
        Adapter：为Item提供数据。
        Item Decoration：Item之间的Divider。
        Item Animator：添加、删除Item动画。



二、RecyclerView提供了三种布局管理器：

        LinerLayoutManager 以垂直或者水平列表方式展示Item
        GridLayoutManager 以网格方式展示Item
        StaggeredGridLayoutManager 以瀑布流方式展示Item

三、Item Animator动画

        RecyclerView能够通过mRecyclerView.setItemAnimator(ItemAnimator animator)设置添加、删除、移动、改变的动画效果。
        动画框架： recyclerview-animators
        https://github.com/wasabeef/recyclerview-animators

四、 局部刷新闪屏问题解决

        当Item视图中有图片和文字，当更新文字并调用notifyItemChanged()时，文字改变的同时图片会闪一下。
        这个问题的原因是当调用notifyItemChanged()时，会调用DefaultItemAnimator的animateChangeImpl()执行change动画，
        该动画会使得Item的透明度从0变为1，从而造成闪屏。

        解决办法很简单，在rv.setAdapter()之前调用
        ((SimpleItemAnimator)rv.getItemAnimator()).setSupportsChangeAnimations(false)
        禁用change动画。


 */

public class MyRecycleViewActivity extends AppCompatActivity {

    private RecyclerView mRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recycle_view);

        mRecycleView = findViewById(R.id.my_recycle);

//        LinearLayoutManager layoutManager = (LinearLayoutManager) mRecycleView.getLayoutManager();
//        layoutManager.setOrientation(RecyclerView.VERTICAL);
//        mRecycleView.setLayoutManager(layoutManager);

        final List<AppListEntity> list = new ArrayList<>();

        list.add(AppListEntity.getEntity("one", "one_content", R.drawable.ic_launcher_background));
        list.add(AppListEntity.getEntity("two", "two_content", R.drawable.ic_launcher_background));
        list.add(AppListEntity.getEntity("three", "three_content", R.drawable.ic_launcher_background));

        final AppBaseRectcleAdapter<AppListEntity> adapter = new AppBaseRectcleAdapter<AppListEntity>(MyRecycleViewActivity.this, list, R.layout.my_list_layout) {
            @Override
            protected void bindData(AppBaseRecyclerViewHolder holder, AppListEntity data) {
                holder.setImageView(R.id.list_image, data.getImage());
                holder.setText(R.id.list_title, data.getTitle());
                holder.setText(R.id.list_content, data.getContent());
            }
        };

        // header 根布局不能是LinearLayout
        LayoutInflater inflater = LayoutInflater.from(MyRecycleViewActivity.this);
        View headerView = inflater.inflate(R.layout.list_header_layout, null);

        // 添加
        TextView add = headerView.findViewById(R.id.header_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItem(AppListEntity.getEntity("add", "add_content", R.drawable.ic_launcher_background));
            }
        });

        // 删除
        TextView dele = headerView.findViewById(R.id.header_delete);
        dele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.removeItemAtPosition(0);
            }
        });

        // 更新
        TextView update = headerView.findViewById(R.id.header_update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapter.updateItemAtPosition(0, AppListEntity.getEntity("更新", "更行内容", R.drawable.ic_launcher_background));
            }
        });

        adapter.setHeaderView(headerView);
        mRecycleView.setAdapter(adapter);

        addLineColor();

    }

    // 1. 系统方式
    private void addLine() {

        DividerItemDecoration decoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        mRecycleView.addItemDecoration(decoration);
    }


    /**
     * 系统改色
     */
    private void addLineColor() {

        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        Drawable dividerDrawable = getResources().getDrawable(R.drawable.recycle_divider);
        decoration.setDrawable(dividerDrawable);
        mRecycleView.addItemDecoration(decoration);

    }


}