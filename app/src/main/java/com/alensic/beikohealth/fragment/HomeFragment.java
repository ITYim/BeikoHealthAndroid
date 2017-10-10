package com.alensic.beikohealth.fragment;

import com.alensic.beikohealth.R;
import com.alensic.beikohealth.mvp.BaseMvpFragment;
import com.alensic.beikohealth.mvppresenter.HomePresenter;
import com.alensic.beikohealth.mvpview.HomeView;
import com.yim.base.permission.Permissions;
import com.yim.base.permission.YimPermission;
import com.yim.base.utils.Logger;
import com.yim.base.utils.StatusBarUtils;

import butterknife.OnClick;

/**
 * @author zym
 * @since 2017-08-22 17:22
 */
public class HomeFragment extends BaseMvpFragment<HomePresenter> implements HomeView {

    @Override
    public void initView() {
        StatusBarUtils.darkenStatusBarColor(getActivity());
    }

    @OnClick(R.id.btn_call)
    void call() {
        YimPermission.requestPermission(this, Permissions.PHONE.PERMISSION_PHONE);
    }

    @Override
    public void onPermissionGranted(String permission, int requestCode) {
        Logger.d("onPermissionGranted " + permission);
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter(context, this);
    }
}
