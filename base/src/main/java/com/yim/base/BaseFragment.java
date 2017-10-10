package com.yim.base;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yim.base.common.ICommonUI;
import com.yim.base.permission.YimPermission;
import com.yim.base.utils.Logger;
import com.yim.base.utils.StatusBarUtils;

import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

/**
 * Fragment的基类
 * Created by 郑依民 on 2016/7/21.
 */
public abstract class BaseFragment extends Fragment implements ICommonUI, YimPermission.PermissionCallback {
    protected Context context;

    private CompositeSubscription mCompositeSubscription;
    private final String TAG = this.getClass().getSimpleName();

    private BasePageLayout mBaseLayout; // fragment的跟布局

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i(TAG, "onCreate");
        if (!isSwipe() && getActivity() instanceof BaseFragmentActivity) {
            ((BaseFragmentActivity) getActivity()).disableSwipe();
        }
        fetchArgument(getArguments());
        context = this.getActivity();
        mCompositeSubscription = new CompositeSubscription();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.i(TAG, "onCreateView");
        if (mBaseLayout == null) {
            mBaseLayout = new BasePageLayout(context, layoutId());
            mBaseLayout.setOnRetryListener(new BasePageLayout.OnRetryListener() {
                @Override
                public void onRetry() {
                    BaseFragment.this.onRetry();
                }
            });
        } else {
            ViewGroup parent = (ViewGroup) mBaseLayout.getParent();
            if (parent != null) {    // 如果该View有父控件，即已经被添加到布局中，先移除，避免重复添加；
                parent.removeView(mBaseLayout);
            }
        }
        return mBaseLayout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Logger.i(TAG, "onViewCreated");
        ButterKnife.bind(this, mBaseLayout);
        initView();
        initListener();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.i(TAG, "onActivityCreated");
        fetchData();
    }

    @Override
    public void onDestroyView() {
        Logger.i(TAG, "onDestroyView");
        if (mCompositeSubscription!=null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
        super.onDestroyView();
    }

    @Override
    public <T extends View> T findView(View view, int resId) {
        // noinspection unchecked
        return (T) view.findViewById(resId);
    }

    @Override
    public <T extends View> T findView(int resId) {
        // noinspection unchecked
        return (T) mBaseLayout.findViewById(resId);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Logger.i(TAG, "onAttach");
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.i(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.i(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.i(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.i(TAG, "onStop");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logger.i(TAG, "onDetach");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.i(TAG, "onDestroy");
    }

    public static void setViewEnable(View view, boolean enable) {
        view.setEnabled(enable);
    }

    public void setViewClickListener(View view) {
        view.setOnClickListener(this);
    }

    public void setViewGroupPaddingTop(View view) {
        int paddingLeft = view.getPaddingLeft();
        int paddingTop = view.getPaddingTop() + StatusBarUtils.getStatusBarHeight(getActivity());
        int paddingRight = view.getPaddingRight();
        int paddingBottom = view.getPaddingBottom();
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    /**
     * 是否开启Swipe切换
     */
    protected boolean isSwipe() {
        return true;
    }

    /**
     * 提供给子类获取参数使用
     */
    protected void fetchArgument(Bundle argument) {
        // do nothing
    }

    @Override
    public void initView() {
        // do nothing
    }

    @Override
    public void fetchData(){
        // do nothing
    }

    @Override
    public void initListener(){
        // do nothing
    }

    @Override
    public void onClick(View v) {
        // do nothing
    }

    public void showLoadingView() {
        mBaseLayout.showLoadingView();
    }

    public void hideLoadingView() {
        mBaseLayout.hideLoadingView();
    }

    public void showEmptyView() {
        mBaseLayout.showEmptyView();
    }

    public void hideEmptyView() {
        mBaseLayout.hideEmptyView();
    }

    public void showErrorView() {
        mBaseLayout.showErrorView();
    }

    public void hideErrorView() {
        mBaseLayout.hideErrorView();
    }

    protected void onRetry() {
        // nothing
    }

    @Override
    public final void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Logger.i(TAG, "onRequestPermissionsResult()");
        YimPermission.onPermissionRequestCallback(this, requestCode, permissions, grantResults);
    }

    @Override
    public void onPermissionGranted(String permission, int requestCode) {
        // do nothing
    }

    @Override
    public void onPermissionDenied(String permission, int requestCode) {
        // do nothing
    }
}
