package com.alensic.beikohealth.fragment;

import com.alensic.beikohealth.R;
import com.alensic.beikohealth.mvp.BaseMvpFragment;
import com.alensic.beikohealth.mvppresenter.ClassroomPresenter;
import com.alensic.beikohealth.mvpview.ClassroomView;

/**
 * @author zym
 * @since 2017-08-22 17:22
 */
public class ClassroomFragment extends BaseMvpFragment<ClassroomPresenter> implements ClassroomView {

    @Override
    public int layoutId() {
        return R.layout.fragment_classroom;
    }

    @Override
    public ClassroomPresenter createPresenter() {
        return new ClassroomPresenter(context, this);
    }
}
