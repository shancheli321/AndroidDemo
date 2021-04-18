package com.app.base.recycleradapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lf.main.R;

import java.util.ArrayList;
import java.util.List;

public abstract class AppBaseRectcleAdapter<T> extends RecyclerView.Adapter {

    enum ItemType {
        ItemTypeHeader,
        ItemTypeNormal,
        ItemTypeFooter
    }

    private Context mContext;

    private List<T> mData;

    private int mLayoutRes;

    private View mHeaderView;

    private View mFooterView;

    private OnItemClickListener onItemClickListener;

    private OnItemLongClickListener onItemLongClickListener;

    public AppBaseRectcleAdapter(Context context,
                                 List<T> data,
                                 int layoutRes) {
        this.mContext = context;
        this.mLayoutRes = layoutRes;
        this.mData = data;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ItemType.ItemTypeHeader.ordinal();
        }

        if (position == getItemCount() - 1) {
            return ItemType.ItemTypeFooter.ordinal();
        }

        return ItemType.ItemTypeNormal.ordinal();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == ItemType.ItemTypeHeader.ordinal()) {

            if (null == mHeaderView) {
                mHeaderView = new View(mContext);
            }

            return new RecyclerView.ViewHolder(mHeaderView) {};
        }


        if (viewType == ItemType.ItemTypeFooter.ordinal()) {
            if (null == mFooterView) {
                mFooterView = new View(mContext);
            }

            return new RecyclerView.ViewHolder(mFooterView) {};
        }

        return AppBaseRecyclerViewHolder.getHolder(parent, mLayoutRes);

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        if (position == 0) {
            return;

        } else if (position == getItemCount() - 1) {
            return;

        } else {

            bindData((AppBaseRecyclerViewHolder) holder, mData.get(position - 1));

            if (null != onItemClickListener) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClickListener(holder.itemView, position - 1);
                    }
                });
            }

            if (null != onItemLongClickListener) {
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        onItemLongClickListener.onItemLongClickListener(holder.itemView, position - 1);
                        return true;
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return (null == mData) ? 2 : mData.size() + 2;
    }


    public void setHeaderView(View headerView) {
        this.mHeaderView = headerView;
    }

    public void setFooterView(View footerView) {
        this.mFooterView = footerView;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }


    /**
     * 更新adapter
     * @param data
     */
    public void updateData(List<T> data) {
        this.mData = data;
        notifyItemRangeChanged(0, getItemCount());
    }


    /**
     * 添加新的adapter
     * @param data
     */
    public void addNewItem(T data) {
        if(mData == null) {
            mData = new ArrayList<T>();
        }
        mData.add(data);

        notifyItemInserted(mData.size());
    }


    /**
     * 删除Item
     * @param data
     */
    public void deleteItem(T data) {
        if(mData == null || mData.isEmpty()) {
            return;
        }

        int position = mData.indexOf(data);
        mData.remove(data);
        notifyItemRemoved(position + 1);
    }



    protected abstract void bindData(AppBaseRecyclerViewHolder holder, T data);


    public interface OnItemClickListener {
         void onItemClickListener(View view, int position);
    }

    public interface OnItemLongClickListener {
         void onItemLongClickListener(View view, int position);
    }

}
