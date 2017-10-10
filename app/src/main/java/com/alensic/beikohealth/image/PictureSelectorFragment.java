package com.alensic.beikohealth.image;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alensic.beikohealth.R;
import com.alensic.beikohealth.adapter.BaseRecycleViewAdapter;
import com.alensic.beikohealth.mvp.BaseMvpFragment;
import com.alensic.beikohealth.bean.PhotoInfo;
import com.alensic.beikohealth.helper.EventEntity;
import com.alensic.beikohealth.helper.EventManager;
import com.alensic.beikohealth.helper.UIHelper;
import com.alensic.beikohealth.mvppresenter.PictureSelectPresenter;
import com.alensic.beikohealth.mvpview.PictureSelectView;
import com.alensic.beikohealth.widget.TitleBarView;
import com.yim.base.utils.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * @author zym
 * @since 2017-08-14 16:28
 */

public class PictureSelectorFragment extends BaseMvpFragment<PictureSelectPresenter> implements PictureSelectView, BaseRecycleViewAdapter.OnItemClickListener<PhotoInfo>,PictureSelectorAdapter.OnPhotoCheckChangeListener  {
    public static final String KEY_MAX_PICTURE_SELECT = "MAX_PICTURE_SELECT";
    public static final String KEY_ORIGINAL_PICTURE_SELECTED = "ORIGINAL_PICTURE_SELECTED";

    private static final int DEFAULT_MAX_PICTURE_SELECT = 10;

    private int maxCount;
    private ArrayList<String> originalPictureList;

    @BindView(R.id.tbv_pic_select)
    TitleBarView tbv_pic_select;
    @BindView(R.id.tv_change_picture_type)
    TextView tv_change_picture_type;
    @BindView(R.id.btn_ok)
    Button btn_ok;
    @BindView(R.id.rv_pictures)
    RecyclerView rv_pictures;

    private PictureTypeDialog mPictureTypeDialog;

    private PictureSelectorAdapter mPictureAdapter;

    @Override
    protected void fetchArgument(Bundle argument) {
        maxCount = argument.getInt(KEY_MAX_PICTURE_SELECT, DEFAULT_MAX_PICTURE_SELECT);
        originalPictureList = argument.getStringArrayList(KEY_ORIGINAL_PICTURE_SELECTED);
    }

    @Override
    public void initView() {
        tbv_pic_select.setRightText(String.format(Locale.CHINA,
                "(%d/%d)", originalPictureList == null ? 0 : originalPictureList.size(), maxCount));
    }

    @Override
    public void initListener() {
        setViewClickListener(tv_change_picture_type);
        setViewClickListener(btn_ok);
    }

    @Override
    public void fetchData() {
        Logger.d("fetchData");
        presenter.loadAllPictures();
    }

    @Override
    public void showPictures(ArrayList<PhotoInfo> photoInfoList) {
        if (mPictureAdapter == null) {
            mPictureAdapter = new PictureSelectorAdapter(context, photoInfoList, maxCount, originalPictureList);
            mPictureAdapter.setOnPhotoCheckChangeListener(this);
            mPictureAdapter.setOnItemClickListener(this);
            rv_pictures.setAdapter(mPictureAdapter);
        } else {
            mPictureAdapter.replayDatas(photoInfoList);
        }
    }

    @Override
    public void onItemClick(PhotoInfo item, int position) {
        UIHelper.openPhotoPreview(context, (ArrayList<PhotoInfo>) mPictureAdapter.getData(), position);
    }

    @Override
    public void onPhotoCheckChange(List<PhotoInfo> checkList) {
        tbv_pic_select.setRightText(String.format(Locale.CHINA, "(%d/%d)", checkList.size(), maxCount));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_change_picture_type:
                if (mPictureTypeDialog != null) {
                    mPictureTypeDialog.show(getFragmentManager(), PictureTypeDialog.class.getSimpleName());
                }
                break;
            case R.id.btn_ok:
                if (mPictureAdapter == null)
                    break;
                List<PhotoInfo> checkList = mPictureAdapter.getCheckList();
                if (checkList.size() == 0) {
                    Snackbar.make(v, "请选择图片", Snackbar.LENGTH_SHORT).show();
                    break;
                }
                EventEntity event = new EventEntity();
                event.photoInfoList = checkList;
                EventManager.post(event);
                getActivity().finish();
                break;
        }
    }

    @Override
    public void buildPictureTypes(List<String> types) {
        if (mPictureTypeDialog == null) {
            if (types == null)
                types = new ArrayList<>();
            types.add(0, "全部图片");
            mPictureTypeDialog = new PictureTypeDialog();
            Bundle args = new Bundle();
            args.putStringArrayList(PictureTypeDialog.KEY_PICTURE_TYPE_LIST, (ArrayList<String>) types);
            mPictureTypeDialog.setArguments(args);
        }
    }

    public void selectPictureByType(String type) {
        tv_change_picture_type.setText(type + "∨");
        presenter.loadPictureByType(type);
    }

    @Override
    public PictureSelectPresenter createPresenter() {
        return new PictureSelectPresenter(context, this);
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_picture_selector;
    }
}
