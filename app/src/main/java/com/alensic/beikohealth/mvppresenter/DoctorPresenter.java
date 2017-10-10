package com.alensic.beikohealth.mvppresenter;

import android.content.Context;

import com.alensic.beikohealth.mvp.BasePresenter;
import com.alensic.beikohealth.mvp.BaseView;
import com.alensic.beikohealth.mvpview.DoctorView;

/**
 * @author zym
 * @since 2017-08-22 17:23
 */
public class DoctorPresenter extends BasePresenter<DoctorView> {

    public DoctorPresenter(Context context, BaseView mvpView) {
        super(context, mvpView);
    }
}
