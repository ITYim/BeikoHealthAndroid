package com.alensic.beikohealth.activity;

import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.alensic.beikohealth.R;
import com.alensic.beikohealth.dialog.ConfirmDialog;
import com.alensic.beikohealth.fragment.ClassroomFragment;
import com.alensic.beikohealth.fragment.DoctorFragment;
import com.alensic.beikohealth.fragment.HomeFragment;
import com.alensic.beikohealth.fragment.MineFragment;
import com.alensic.beikohealth.mvp.BaseMvpActivity;
import com.alensic.beikohealth.mvppresenter.MainPresenter;
import com.alensic.beikohealth.mvpview.MainView;
import com.yim.base.BaseFragment;

import java.util.List;

import butterknife.BindViews;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements ConfirmDialog.OnConfirmListener, ConfirmDialog.OnCancelListener, MainView {
    private ConfirmDialog mConfirmDialog;
    private HomeFragment mHomeFragment;
    private DoctorFragment mDoctorFragment;
    private ClassroomFragment mClassroomFragment;
    private MineFragment mMineFragment;

    private BaseFragment mLastShowFragment;

    @BindViews({R.id.iv_tab_home, R.id.iv_tab_doctor, R.id.iv_tab_classroom, R.id.iv_tab_mine})
    List<ImageView> mTabImages;
    @BindViews({R.id.rl_tab_home, R.id.rl_tab_doctor, R.id.rl_tab_classroom, R.id.rl_tab_mine})
    List<RelativeLayout> mTabLayouts;

    private ImageView mCurrentSelectedTab;

    @Override
    public void initView() {
        mTabImages.get(0).setSelected(true);
        home(); // 默认加载主页
        mCurrentSelectedTab = mTabImages.get(0);
    }

    @Override
    public void initListener() {
        mTabLayouts.get(0).setOnClickListener(this);
        mTabLayouts.get(1).setOnClickListener(this);
        mTabLayouts.get(2).setOnClickListener(this);
        mTabLayouts.get(3).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_tab_home:
                if (mCurrentSelectedTab == mTabImages.get(0))
                    break;
                if (mCurrentSelectedTab != null)
                    mCurrentSelectedTab.setSelected(false);
                mCurrentSelectedTab = mTabImages.get(0);
                mCurrentSelectedTab.setSelected(true);
                home();
                break;
            case R.id.rl_tab_doctor:
                if (mCurrentSelectedTab == mTabImages.get(1))
                    break;
                if (mCurrentSelectedTab != null)
                    mCurrentSelectedTab.setSelected(false);
                mCurrentSelectedTab = mTabImages.get(1);
                mCurrentSelectedTab.setSelected(true);
                doctor();
                break;
            case R.id.rl_tab_classroom:
                if (mCurrentSelectedTab == mTabImages.get(2))
                    break;
                if (mCurrentSelectedTab != null)
                    mCurrentSelectedTab.setSelected(false);
                mCurrentSelectedTab = mTabImages.get(2);
                mCurrentSelectedTab.setSelected(true);
                classroom();
                break;
            case R.id.rl_tab_mine:
                if (mCurrentSelectedTab == mTabImages.get(3))
                    break;
                if (mCurrentSelectedTab != null)
                    mCurrentSelectedTab.setSelected(false);
                mCurrentSelectedTab = mTabImages.get(3);
                mCurrentSelectedTab.setSelected(true);
                mine();
                break;
        }
    }

    private void home() {
        createHome();
        if (mHomeFragment.isVisible())  // 防止重复点击
            return;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.show(mHomeFragment);
        if (mLastShowFragment != null) {
            transaction.hide(mLastShowFragment);
        }
        transaction.commit();
        mLastShowFragment = mHomeFragment;  // 记录当前展示的Fragment
    }

    private void doctor() {
        createDoctor();
        if (mDoctorFragment.isVisible())  // 防止重复点击
            return;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.show(mDoctorFragment);
        if (mLastShowFragment != null) {
            transaction.hide(mLastShowFragment);
        }
        transaction.commit();
        mLastShowFragment = mDoctorFragment;  // 记录当前展示的Fragment
    }

    private void classroom() {
        createClassroom();
        if (mClassroomFragment.isVisible())  // 防止重复点击
            return;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.show(mClassroomFragment);
        if (mLastShowFragment != null) {
            transaction.hide(mLastShowFragment);
        }
        transaction.commit();
        mLastShowFragment = mClassroomFragment;  // 记录当前展示的Fragment
    }

    private void mine() {
        createMine();
        if (mMineFragment.isVisible())  // 防止重复点击
            return;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.show(mMineFragment);
        if (mLastShowFragment != null) {
            transaction.hide(mLastShowFragment);
        }
        transaction.commit();
        mLastShowFragment = mMineFragment;  // 记录当前展示的Fragment
    }

    private void createHome() {
        if (mHomeFragment == null) {
            mHomeFragment = new HomeFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.container, mHomeFragment, "HomeFragment");
            transaction.commit();
        }
    }

    private void createDoctor() {
        if (mDoctorFragment == null) {
            mDoctorFragment = new DoctorFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.container, mDoctorFragment, "DoctorFragment");
            transaction.commit();
        }
    }

    private void createClassroom() {
        if (mClassroomFragment == null) {
            mClassroomFragment = new ClassroomFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.container, mClassroomFragment, "ClassroomFragment");
            transaction.commit();
        }
    }

    private void createMine() {
        if (mMineFragment == null) {
            mMineFragment = new MineFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.container, mMineFragment, "MineFragment");
            transaction.commit();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mConfirmDialog == null) {
                mConfirmDialog = new ConfirmDialog();
                mConfirmDialog.setContent("确认退出吗？");
                mConfirmDialog.setOnConfirmListener(this);
                mConfirmDialog.setOnCancelListener(this);
            }
            mConfirmDialog.show(getFragmentManager());
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onConfirm(DialogFragment dialog) {
        dialog.dismiss();
        finish();
        return true;
    }

    @Override
    public boolean onCancel(DialogFragment dialog) {
        return false;
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter(context, this);
    }

    @Override
    protected boolean isSwipe() {
        return false;
    }

    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

}
