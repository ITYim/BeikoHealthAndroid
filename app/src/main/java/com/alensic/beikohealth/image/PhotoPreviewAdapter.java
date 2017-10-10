package com.alensic.beikohealth.image;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alensic.beikohealth.bean.PhotoInfo;
import com.alensic.beikohealth.glide.BitmapUtil;

import java.util.ArrayList;

/**
 * @author zym
 * @since 2017-08-15 10:45
 */

public class PhotoPreviewAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<PhotoInfo> mPhotoList;

    public PhotoPreviewAdapter(Context context, ArrayList<PhotoInfo> mPhotoList) {
        this.context = context;
        this.mPhotoList = mPhotoList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewPager.LayoutParams lp = new ViewPager.LayoutParams();
        ImageView imageView = new ImageView(context);
        BitmapUtil.showImage(context, mPhotoList.get(position).getPhotoPath(), imageView);
        imageView.setLayoutParams(lp);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mPhotoList == null ? 0 : mPhotoList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
