package com.alensic.beikohealth.image;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.FrameLayout;

import com.alensic.beikohealth.R;
import com.alensic.beikohealth.adapter.BaseRecycleViewAdapter;
import com.alensic.beikohealth.adapter.BaseViewHolder;
import com.alensic.beikohealth.bean.PhotoInfo;
import com.alensic.beikohealth.utils.DimensionUtils;
import com.yim.base.utils.JsonUtils;
import com.yim.base.utils.ScreenInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zym
 * @since 2017-08-11 15:01
 */
@SuppressWarnings("WeakerAccess")
public class PictureSelectorAdapter extends BaseRecycleViewAdapter<PhotoInfo> {
    private int space, size;
    private OnPhotoCheckChangeListener listener;
    private List<String> checkList;
    private int maxCheckCount;
    public PictureSelectorAdapter(Context context, List<PhotoInfo> data, int maxCheckCount, List<String> original) {
        super(context, data, R.layout.adapter_picture_selector);
        space = DimensionUtils.dp2px(2);
        size = (ScreenInfo.width - space * 5) / 4;
        checkList = new ArrayList<>();
        if (original != null) {
            checkList.addAll(original);
        }
        this.maxCheckCount = maxCheckCount;
    }

    @Override
    public void onBindView(BaseViewHolder holder, final PhotoInfo item, int position) {
        FrameLayout frameLayout = (FrameLayout) holder.itemView;
        GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) frameLayout.getLayoutParams();
        layoutParams.topMargin = space;
        layoutParams.width = size;
        layoutParams.height = size;
        holder.setImage(R.id.iv_picture, item.getPhotoPath());
        final View v_photo_check = holder.findView(R.id.v_photo_check);
        FrameLayout fl_photo_check_layout = holder.findView(R.id.fl_photo_check_layout);
        v_photo_check.setSelected(checkList.contains(JsonUtils.toJson(item)));
        fl_photo_check_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkList.size() >= maxCheckCount) {
                    Snackbar.make(v, "最多选" + maxCheckCount + "张图片", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (checkList.contains(JsonUtils.toJson(item))) {
                    checkList.remove(JsonUtils.toJson(item));
                    v_photo_check.setSelected(false);
                } else {
                    checkList.add(JsonUtils.toJson(item));
                    v_photo_check.setSelected(true);
                }
                if (listener != null) {
                    listener.onPhotoCheckChange(getCheckList());
                }
            }
        });
    }

    public void setOnPhotoCheckChangeListener(OnPhotoCheckChangeListener listener) {
        this.listener = listener;
    }

    public interface OnPhotoCheckChangeListener {
        void onPhotoCheckChange(List<PhotoInfo> checkList);
    }

    public List<PhotoInfo> getCheckList() {
        List<PhotoInfo> list = new ArrayList<>();
        for (String s : checkList) {
            list.add(JsonUtils.fromJson(s, PhotoInfo.class));
        }
        return list;
    }
}
