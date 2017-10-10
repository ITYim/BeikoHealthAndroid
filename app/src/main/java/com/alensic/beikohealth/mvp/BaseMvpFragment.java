package com.alensic.beikohealth.mvp;

import android.os.Bundle;

import com.yim.base.BaseFragment;

/**
 *
 * @author zym
 * @since 2017-08-07 14:21
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment {

    protected P presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.destroy();
        }
    }

    public abstract P createPresenter();
}
