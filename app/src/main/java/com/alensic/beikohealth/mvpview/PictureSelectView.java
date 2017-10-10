package com.alensic.beikohealth.mvpview;

import com.alensic.beikohealth.mvp.BaseView;
import com.alensic.beikohealth.bean.PhotoInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zym
 * @since 2017-08-11 10:24
 */

public interface PictureSelectView extends BaseView {

    void showPictures(ArrayList<PhotoInfo> photoInfoList);

    void buildPictureTypes(List<String> types);
}
