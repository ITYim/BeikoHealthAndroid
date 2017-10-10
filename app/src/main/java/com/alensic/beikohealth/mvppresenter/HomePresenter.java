package com.alensic.beikohealth.mvppresenter;

import android.content.Context;

import com.alensic.beikohealth.mvp.BasePresenter;
import com.alensic.beikohealth.mvp.BaseView;
import com.alensic.beikohealth.mvpview.HomeView;

/**
 * @author zym
 * @since 2017-08-22 17:23
 */
public class HomePresenter extends BasePresenter<HomeView> {

    public HomePresenter(Context context, BaseView mvpView) {
        super(context, mvpView);
    }
}
