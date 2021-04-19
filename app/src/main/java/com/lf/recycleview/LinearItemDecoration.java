package com.lf.recycleview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LinearItemDecoration extends RecyclerView.ItemDecoration {

    private Paint paint;

    public LinearItemDecoration() {

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
    }



    /**
     * getItemOffsets() 主要作用是在 item 的四周留下边距，效果和 margin 类似，
     * item 的四周留下边距后，我们就可以通过 onDraw() 在这个边距上绘制了
     * @param outRect   表示 item 的上下左右所留下的边距。其中 outRect 的 left, top,right,bottom 即为 item 四周留下的边距的距离，默认都为 0
     * @param view    指当前 item 的 View 对象；
     * @param parent  指 RecyclerView 本身；
     * @param state   指 RecyclerView 当前的状态；
     *
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        // 10px
        outRect.bottom = 10;

        outRect.left = 100;

        outRect.right = 40;

    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

        for (int i = 0; i < parent.getChildCount(); i++) {
            View childView = parent.getChildAt(i);
            c.drawCircle(50, childView.getTop() + childView.getHeight() / 2, 20, paint);
        }

    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
