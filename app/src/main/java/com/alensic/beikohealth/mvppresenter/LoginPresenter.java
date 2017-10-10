package com.alensic.beikohealth.mvppresenter;

import android.content.Context;

import com.alensic.beikohealth.mvp.BasePresenter;
import com.alensic.beikohealth.mvp.BaseView;
import com.alensic.beikohealth.mvpview.LoginView;

/**
 * @author zym
 * @since 2017-08-08 14:13
 */
public class LoginPresenter extends BasePresenter<LoginView> {

    public LoginPresenter(Context context, BaseView mvpView) {
        super(context, mvpView);
    }
}
