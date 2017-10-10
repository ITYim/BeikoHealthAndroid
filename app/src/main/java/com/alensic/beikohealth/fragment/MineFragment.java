package com.alensic.beikohealth.fragment;

import com.alensic.beikohealth.R;
import com.alensic.beikohealth.mvp.BaseMvpFragment;
import com.alensic.beikohealth.mvppresenter.MinePresenter;
import com.alensic.beikohealth.mvpview.MineView;

/**
 * @author zym
 * @since 2017-08-22 17:22
 */
public class MineFragment extends BaseMvpFragment<MinePresenter> implements MineView {

    @Override
    public int layoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public MinePresenter createPresenter() {
        return new MinePresenter(context, this);
    }
}
