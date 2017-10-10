package com.alensic.beikohealth.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * RecycleView 适配器的基类
 * Created by 郑依民 on 2016/7/22.
 */
public abstract class BaseRecycleViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    protected List<T> mData;
    private int itemLayoutId;
    protected Context context;
    protected OnItemClickListener<T> mClickListener;
    protected OnItemLongClickListener<T> mLongClickListener;

    public BaseRecycleViewAdapter(Context context, int itemLayoutId) {
        mData = new ArrayList<>();
        this.context = context;
        this.itemLayoutId = itemLayoutId;
    }

    public BaseRecycleViewAdapter(Context context, @NonNull List<T> data, int itemLayoutId) {
        mData = new ArrayList<>();
        if (data != null) {
            mData.addAll(data);
        }
        this.context = context;
        this.itemLayoutId = itemLayoutId;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflateViewByViewType(parent, viewType);
        View itemView = view != null ? view : LayoutInflater.from(context).inflate(itemLayoutId, parent, false);
        return new BaseViewHolder(context, itemView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        T t = mData.get(position);
        holder.itemView.setTag(t);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    T tempT = (T) v.getTag();
                    mClickListener.onItemClick(tempT, position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mLongClickListener != null) {
                    T tempT = (T) v.getTag();
                    mLongClickListener.onItemLongClick(tempT, position);
                    return true;
                }
                return false;
            }
        });
        onBindView(holder, t, position);
    }

    protected View inflateViewByViewType(ViewGroup parent, int viewType) {
        return null;
    }

    public abstract void onBindView(BaseViewHolder holder, T item, int position);

    public T getItem(int position) {
        return mData.get(position);
    }

    public List<T> getData() {
        return mData;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void replayDatas(List<T> data) {
        mData.clear();
        addData(data);
    }

    public void addData(List<T> data) {
        if (data != null) {
            mData.addAll(data);
            notifyDataSetChanged();
        }
    }

    /**
     * 往指定位置添加数据，并刷新
     */
    public void addData(int index, T newData) {
        if (newData != null) {
            mData.add(index, newData);
            notifyDataSetChanged();
        }
    }

    /**
     * 往指定位置添加数据集合，并刷新
     */
    public void addData(int index, List<T> newData) {
        if (newData != null) {
            mData.addAll(index, newData);
            notifyDataSetChanged();
        }
    }

    /**
     * 往列表中继续添加数据，并刷新
     */
    public void addData(T newData) {
        if (newData != null) {
            mData.add(newData);
            notifyDataSetChanged();
        }
    }

    public void removeData(int index) {
        mData.remove(index);
        notifyDataSetChanged();
    }

    public void addDataWithAnim(int index, T newData) {
        mData.add(index, newData);
        notifyItemInserted(index);
    }

    public void removeDataWithAnim(int index) {
        mData.remove(index);
        notifyItemRemoved(index);
    }

    public void clearData() {
        mData.clear();
        notifyDataSetChanged();
    }
    public interface OnItemClickListener<R> {
        void onItemClick(R item, int position);
    }

    public interface OnItemLongClickListener<R> {
        void onItemLongClick(R item, int position);
    }

    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        this.mClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<T> listener) {
        this.mLongClickListener = listener;
    }
}