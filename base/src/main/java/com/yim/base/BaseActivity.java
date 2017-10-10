package com.yim.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.yim.base.common.ICommonUI;
import com.yim.base.permission.YimPermission;
import com.yim.base.utils.Logger;
import com.yim.base.utils.StatusBarUtils;
import com.yim.base.utils.SwipeLayoutHelper;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Activity的基类，所有Activity都应继承自BaseActivity
 * Created by 郑依民 on 2016/7/21.
 */
public abstract class BaseActivity extends AppCompatActivity implements ICommonUI, YimPermission.PermissionCallback {
    private final String TAG = this.getClass().getSimpleName();
    protected Context context;
    private CompositeSubscription mCompositeSubscription;

    private BasePageLayout mBaseLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Logger.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        context = this;
        mCompositeSubscription = new CompositeSubscription();
        setSwipeBackHelper();
        setContentView();
        initView();
        initListener();
        findViewById(android.R.id.content).post(new Runnable() {
            @Override
            public void run() {
                fetchData();
            }
        });
    }

    private void setContentView() {
        mBaseLayout = new BasePageLayout(context, layoutId());
        mBaseLayout.setOnRetryListener(new BasePageLayout.OnRetryListener() {
            @Override
            public void onRetry() {
                BaseActivity.this.onRetry();
            }
        });
        setContentView(mBaseLayout);
        ButterKnife.bind(this);
    }

    public void addSubscriber(Subscription subscription) {
        mCompositeSubscription.add(subscription);
    }

    /**
     * 设置SwipeBack效果
     */
    private void setSwipeBackHelper() {
        if (isSwipe()) {
            SwipeLayoutHelper.init(this);
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        Logger.d(TAG, "onPostCreate");
        super.onPostCreate(savedInstanceState);
        if (isSwipe()) {
            SwipeLayoutHelper.postCreate(this);
        }
    }

    @Override
    protected void onDestroy() {
        Logger.d(TAG, "onDestroy");
        super.onDestroy();
        if (mCompositeSubscription!=null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
        if (isSwipe()) {
            SwipeLayoutHelper.destroy(this);
        }
    }

    public static void setViewEnable(View view, boolean enable) {
        view.setEnabled(enable);
    }

    public void setViewClickListener(View view) {
        view.setOnClickListener(this);
    }

    /**
     * 为跟布局设置顶部内边距
     * 要求系统版本4.4及以上
     */
    public void setViewGroupPaddingTop(View view) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)
            return;
        int paddingLeft = view.getPaddingLeft();
        int paddingTop = view.getPaddingTop() + StatusBarUtils.getStatusBarHeight(context);
        int paddingRight = view.getPaddingRight();
        int paddingBottom = view.getPaddingBottom();
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    /**
     * 是否开启SwipeBack模式
     * 允许子类重写
     */
    protected boolean isSwipe() {
        return true;
    }

    @Override
    public <T extends View> T findView(View view, int resId) {
        // noinspection unchecked
        return (T) view.findViewById(resId);
    }

    @Override
    public <T extends View> T findView(int resId) {
        // noinspection unchecked
        return (T) findViewById(resId);
    }

    @Override
    public void initView() {
        // do nothing
    }

    @Override
    public void initListener() {
        // do nothing
    }

    @Override
    public void onClick(View v) {
        // do nothing
    }

    @Override
    public void fetchData() {
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

    @Override
    protected void onStart() {
        super.onStart();
        Logger.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d(TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.d(TAG, "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.d(TAG, "onStop");
    }

    protected void onRetry() {
        // nothing
    }

    @Override
    public final void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Logger.d(TAG, "onRequestPermissionsResult()");
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
