package com.alensic.beikohealth.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alensic.beikohealth.glide.BitmapUtil;

/**
 * Created by 郑依民 on 2016/8/1.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> views;
    private View convertView;
    private Context context;

    public BaseViewHolder(Context context, View itemView) {
        super(itemView);
        convertView = itemView;
        views = new SparseArray<>();
        this.context = context;
    }

    /**
     * 取代并优化 findViewById
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T findView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 设置文本
     *
     * @param textId TextView id
     * @param text   文本内容
     */
    public void setText(int textId, String text) {
        TextView textView = findView(textId);
        textView.setText(text);
    }

    /**
     * 设置图片
     *
     * @param imageId ImageView id
     * @param imgUrl  图片url
     */
    public void setImage(int imageId, String imgUrl) {
        ImageView imageView = findView(imageId);
        BitmapUtil.showImageWithPlaceHolderAndError(context, imgUrl, imageView);
    }

    /**
     * 设置圆角图片图片
     *
     * @param imageId ImageView id
     * @param imgUrl  图片url
     * @param radius  <0时相当于默认值4，其他设置值均有效
     */
    public void setRoundImage(int imageId, String imgUrl, int radius) {
        ImageView imageView = findView(imageId);
        BitmapUtil.showRoundImageWithPlaceHolderAndError(context, imgUrl, imageView, radius);
    }

    /**
     * 设置图片
     *
     * @param imageId ImageView id
     * @param imgRes  图片资源Id
     */
    public void setImage(int imageId, int imgRes) {
        ImageView imageView = findView(imageId);
        BitmapUtil.showImageWithPlaceHolderAndError(context, imgRes, imageView);
    }

    /**
     * 设置圆角图片图片
     *
     * @param imageId ImageView id
     * @param imgRes  图片url
     */
    public void setRoundImage(int imageId, int imgRes, int radius) {
        ImageView imageView = findView(imageId);
        BitmapUtil.showRoundImageWithPlaceHolderAndError(context, imgRes, imageView, radius);
    }
}
