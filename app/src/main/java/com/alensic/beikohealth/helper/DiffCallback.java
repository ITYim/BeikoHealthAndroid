package com.alensic.beikohealth.helper;

import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * @author zym
 * @since 2017-08-18 18:14
 */
public class DiffCallback<T> extends DiffUtil.Callback {

    private List<T> oldList;
    private List<T> newList;
    public DiffCallback(List<T> oldList, List<T> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }
}
