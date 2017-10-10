package com.alensic.beikohealth.image;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;
import android.widget.TextView;

import com.alensic.beikohealth.R;
import com.alensic.beikohealth.bean.PhotoInfo;
import com.yim.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author zym
 * @since 2017-08-14 15:51
 */
public class PhotoPreviewFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
    public static final String KEY_PHOTO_LIST = "PHOTO_LIST";
    public static final String KEY_PREVIEW_POSITION = "PREVIEW_POSITION";

    @BindView(R.id.vp_photo_preview)
    ViewPager vp_photo_preview;
    @BindView(R.id.tv_page_position)
    TextView tv_page_position;

    private ArrayList<PhotoInfo> mPhotoList;
    private int previewPosition;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void fetchArgument(Bundle argument) {
        // noinspection unchecked
        mPhotoList = (ArrayList<PhotoInfo>) argument.getSerializable(KEY_PHOTO_LIST);
        previewPosition = argument.getInt(KEY_PREVIEW_POSITION);
    }

    @Override
    public void initView() {
        PhotoPreviewAdapter adapter = new PhotoPreviewAdapter(context, mPhotoList);
        vp_photo_preview.setAdapter(adapter);
        vp_photo_preview.setCurrentItem(previewPosition, false);
        tv_page_position.setText((previewPosition + 1) + "/" + (mPhotoList == null ? 0 : mPhotoList.size()));
    }

    @Override
    public void initListener() {
        vp_photo_preview.addOnPageChangeListener(this);
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_photo_preview;
    }

    @Override
    protected boolean isSwipe() {
        return false;
    }

    @Override
    public void onPageSelected(int position) {
        tv_page_position.setText((position + 1) + "/" + (mPhotoList == null ? 0 : mPhotoList.size()));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
