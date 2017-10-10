package com.yim.base;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * 实现包含LoadingView、ErrorView、EmptyView、ContentView
 * @author zym
 * @since 2017-08-16 13:42
 */
public class BasePageLayout extends FrameLayout {

    private View mLoadingView;
    private View mEmptyView;
    private View mErrorView;

    private OnRetryListener mRetryListener;

    public BasePageLayout(Context context, int contentViewLayoutId) {
        super(context);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initContentView(context, contentViewLayoutId);
        initView(context);
    }

    private void initView(Context context) {
        initLoadingView(context);
        initEmptyView(context);
        initErrorView(context);
    }

    private void initLoadingView(Context context) {
        mLoadingView = new ProgressBar(context);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        mLoadingView.setLayoutParams(params);
        mLoadingView.setVisibility(GONE);
        addView(mLoadingView);
    }

    private void initEmptyView(Context context) {
        mEmptyView = new TextView(context);
        ((TextView) mEmptyView).setText("暂无数据");
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        mEmptyView.setLayoutParams(params);
        mEmptyView.setVisibility(GONE);
        addView(mEmptyView);
    }

    private void initErrorView(Context context) {
        mErrorView = new LinearLayout(context);
        ((LinearLayout) mErrorView).setGravity(Gravity.CENTER_HORIZONTAL);
        ((LinearLayout) mErrorView).setOrientation(LinearLayout.VERTICAL);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        mErrorView.setLayoutParams(params);
        mErrorView.setVisibility(GONE);

        TextView textView = new TextView(context);
        textView.setText("网络错误");
        textView.setTextColor(Color.GRAY);
        textView.setTextSize(16);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ((LinearLayout) mErrorView).addView(textView);

        Button button = new Button(context);
        button.setText("重试");
        button.setTextColor(Color.WHITE);
        button.setBackgroundColor(ContextCompat.getColor(context, R.color.main_theme));
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(dp2px(150), dp2px(50));
        buttonParams.topMargin = dp2px(8);
        button.setLayoutParams(buttonParams);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRetryListener != null) {
                    mRetryListener.onRetry();
                }
            }
        });
        ((LinearLayout) mErrorView).addView(button);

        addView(mErrorView);
    }

    private void initContentView(Context context, int contentViewLayoutId) {
        View contentView = LayoutInflater.from(context).inflate(contentViewLayoutId, null);
        addView(contentView);
    }

    public void setOnRetryListener(OnRetryListener retryListener) {
        this.mRetryListener = retryListener;
    }

    public interface OnRetryListener {
        void onRetry();
    }

    // --------------------------------- SHOW AND HIDE VIEW ----------------------------------------
    public void showLoadingView() {
        mEmptyView.setVisibility(GONE);
        mErrorView.setVisibility(GONE);
        mLoadingView.setVisibility(VISIBLE);
    }

    public void hideLoadingView() {
        mLoadingView.setVisibility(GONE);
    }

    public void showEmptyView() {
        mErrorView.setVisibility(GONE);
        mLoadingView.setVisibility(GONE);
        mEmptyView.setVisibility(VISIBLE);
    }

    public void hideEmptyView() {
        mLoadingView.setVisibility(GONE);
    }

    public void showErrorView() {
        mLoadingView.setVisibility(GONE);
        mEmptyView.setVisibility(GONE);
        mErrorView.setVisibility(VISIBLE);
    }

    public void hideErrorView() {
        mErrorView.setVisibility(GONE);
    }

    private int dp2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
