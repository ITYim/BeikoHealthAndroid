package com.alensic.beikohealth.image;

import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.alensic.beikohealth.R;
import com.alensic.beikohealth.adapter.BaseRecycleViewAdapter;
import com.alensic.beikohealth.adapter.LinearItemDecoration;
import com.alensic.beikohealth.utils.DimensionUtils;
import com.yim.base.BaseDialogFragment;
import com.yim.base.utils.Logger;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author zym
 * @since 2017-08-11 17:41
 */
public class PictureTypeDialog extends BaseDialogFragment implements BaseRecycleViewAdapter.OnItemClickListener<String> {
    @BindView(R.id.rv_pictures_type)
    RecyclerView rv_pictures_type;
    public static final String KEY_PICTURE_TYPE_LIST = "PICTURE_TYPE_LIST";
    private ArrayList<String> pictureTypeList;

    @Override
    protected void fetchArgument(Bundle argument) {
        pictureTypeList = argument.getStringArrayList(KEY_PICTURE_TYPE_LIST);
    }

    @Override
    public void initView() {
        rv_pictures_type.addItemDecoration(new LinearItemDecoration(context, LinearItemDecoration.VERTICAL_LIST));
        int row = pictureTypeList.size() > 5 ? 5 : pictureTypeList.size();
        int height = row * DimensionUtils.dp2px(50);
        setDialogSize(new Size(ViewGroup.LayoutParams.MATCH_PARENT, height));
        PictureTypeAdapter typeAdapter = new PictureTypeAdapter(context, pictureTypeList);
        typeAdapter.setOnItemClickListener(this);
        rv_pictures_type.setAdapter(typeAdapter);
    }

    @Override
    public int layoutId() {
        return R.layout.dialog_picture_type;
    }

    @Override
    protected void customDialog(Dialog dialog) {
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.windowAnimations = R.style.DialogBottomInOutAnim;
        layoutParams.gravity = Gravity.BOTTOM;
    }

    @Override
    public void onItemClick(String item, int position) {
        dismiss();
        Fragment fragment = getActivity().getFragmentManager().findFragmentByTag(PictureSelectorFragment.class.getSimpleName());
        if (fragment != null && fragment instanceof PictureSelectorFragment) {
            ((PictureSelectorFragment) fragment).selectPictureByType(item);
        } else {
            Logger.e("找不到Fragment[" + (fragment == null) + "]");
        }
    }
}
