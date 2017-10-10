package com.alensic.beikohealth.mvppresenter;

import android.content.Context;

import com.alensic.beikohealth.mvp.BasePresenter;
import com.alensic.beikohealth.mvp.BaseView;
import com.alensic.beikohealth.mvpview.MineView;

/**
 * @author zym
 * @since 2017-08-22 17:23
 */
public class MinePresenter extends BasePresenter<MineView> {

    public MinePresenter(Context context, BaseView mvpView) {
        super(context, mvpView);
    }
}
