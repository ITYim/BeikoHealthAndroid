package com.alensic.beikohealth.mvp;

import android.os.Bundle;

import com.yim.base.BaseActivity;

/**
 * MVP模式和Activity基类的绑定
 * @author zym
 * @since 2017-08-08 14:25
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        presenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.destroy();
        }
    }

    public abstract P createPresenter();

    public P getPresenter() {
        return presenter;
    }
}
