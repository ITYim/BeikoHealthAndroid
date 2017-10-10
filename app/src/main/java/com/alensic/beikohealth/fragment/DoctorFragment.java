package com.alensic.beikohealth.fragment;

import com.alensic.beikohealth.R;
import com.alensic.beikohealth.mvp.BaseMvpFragment;
import com.alensic.beikohealth.mvppresenter.DoctorPresenter;
import com.alensic.beikohealth.mvpview.DoctorView;

/**
 * @author zym
 * @since 2017-08-22 17:22
 */
public class DoctorFragment extends BaseMvpFragment<DoctorPresenter> implements DoctorView {

    @Override
    public int layoutId() {
        return R.layout.fragment_doctor;
    }

    @Override
    public DoctorPresenter createPresenter() {
        return new DoctorPresenter(context, this);
    }
}
