package com.alensic.beikohealth.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alensic.beikohealth.R;

/**
 * 自定义标题控件
 * Created by 郑依民 on 2016/7/26.
 */
@SuppressWarnings("unused")
public class TitleBarView extends FrameLayout {
    private static final int DEFAULT_TEXT_COLOR = Color.BLACK;
    private TextView mTitleTv, mLeftTv, mRightTv;
    private ImageView mIvBack, mIvNext;
    private String title, leftText, rightText;
    private int leftDrawable = -1, rightDrawable = -1;
    private final int defaultDrawablePadding = 8;
    private View rl_titleBar;

    public TitleBarView(Context context) {
        this(context, null);
    }

    public TitleBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View mTitleBarView = View.inflate(context, R.layout.view_title, this);
        rl_titleBar = mTitleBarView.findViewById(R.id.rl_titleBar);
        mLeftTv = (TextView) mTitleBarView.findViewById(R.id.tv_left);
        mRightTv = (TextView) mTitleBarView.findViewById(R.id.tv_right);
        mTitleTv = (TextView) mTitleBarView.findViewById(R.id.tv_title);
        mIvBack = (ImageView) mTitleBarView.findViewById(R.id.iv_back);
        mIvNext = (ImageView) mTitleBarView.findViewById(R.id.iv_next);

        OnClickListener viewClickListener = new OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.iv_back:
                    case R.id.tv_left:
                        if (mClickListener != null) {
                            boolean handle = mClickListener.clickBack(view);
                            if (!handle && getContext() instanceof Activity) {
                                ((Activity) getContext()).finish();
                            }
                        } else {
                            if (getContext() instanceof Activity) {
                                ((Activity) getContext()).finish();
                            }
                        }
                        break;
                    case R.id.tv_right:
                    case R.id.iv_next:
                        if (mClickListener != null)
                            mClickListener.clickRight(view);
                        break;
                }
            }
        };

        mIvBack.setOnClickListener(viewClickListener);
        mIvNext.setOnClickListener(viewClickListener);
        mLeftTv.setOnClickListener(viewClickListener);
        mRightTv.setOnClickListener(viewClickListener);
        init(context, attrs);
    }

    /**
     * 初始化，获取自定义属性
     */
    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBarView);
        title = typedArray.getString(R.styleable.TitleBarView_titleText);
        leftText = typedArray.getString(R.styleable.TitleBarView_leftText);
        rightText = typedArray.getString(R.styleable.TitleBarView_rightText);
        leftDrawable = typedArray.getResourceId(R.styleable.TitleBarView_leftDrawable, -1);
        rightDrawable = typedArray.getResourceId(R.styleable.TitleBarView_rightDrawable, -1);
        int tBackground = typedArray.getResourceId(R.styleable.TitleBarView_tBackground, -1);
        int leftColor = typedArray.getColor(R.styleable.TitleBarView_leftTextColor, DEFAULT_TEXT_COLOR);
        int rightColor = typedArray.getColor(R.styleable.TitleBarView_rightTextColor, DEFAULT_TEXT_COLOR);
        int titleColor = typedArray.getColor(R.styleable.TitleBarView_titleTextColor, DEFAULT_TEXT_COLOR);
        typedArray.recycle();
        setTitle(title);
        if (!TextUtils.isEmpty(leftText)) {
            mLeftTv.setText(leftText);
        }
        if (!TextUtils.isEmpty(rightText)) {
            mRightTv.setText(rightText);
        }
        if (leftDrawable != -1) {
            setDrawableLeft(leftDrawable);
        }
        if (rightDrawable != -1) {
            setDrawableRight(rightDrawable);
        }
        if (tBackground != -1) {
            rl_titleBar.setBackgroundResource(tBackground);
        }
        mLeftTv.setTextColor(leftColor);
        mRightTv.setTextColor(rightColor);
        mTitleTv.setTextColor(titleColor);
    }

    // -------------------------设置点击回调----------------------------------
    private OnTitleBarClickListener mClickListener;

    // 暴露点击监听接口
    public void setOnTitleClickListener(OnTitleBarClickListener listener) {
        this.mClickListener = listener;
    }

    // 定义回调接口
    public interface OnTitleBarClickListener {

        boolean clickBack(View view);  // 点击左边文字

        void clickRight(View view); // 点击右边文字
    }

    // 提供已实现点击回调接口的类，外部实现可以不用三个方法都重写
    public static abstract class SimpleClickAdapter implements OnTitleBarClickListener {

        @Override
        public boolean clickBack(View view) {
            return false;
        }

        @Override
        public void clickRight(View view) {
            // empty
        }
    }


    //-------------------------------------title,left,right的getter和setter方法----------------------------------------------//
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        mTitleTv.setText(title);
    }

    public String getLeftText() {
        return leftText;
    }

    public void setLeftText(String leftText) {
        this.leftText = leftText;
        mLeftTv.setText(leftText);
    }

    public String getRightText() {
        return rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
        mRightTv.setText(rightText);
    }

    public int getLeftDrawable() {
        return leftDrawable;
    }

    public void setLeftDrawable(int leftDrawable) {
        this.leftDrawable = leftDrawable;
        setDrawableLeft(leftDrawable);
    }

    public int getRightDrawable() {
        return rightDrawable;
    }

    public void setRightDrawable(int rightDrawable) {
        this.rightDrawable = rightDrawable;
        setDrawableRight(rightDrawable);
    }

    /**
     * 设置位于TextView左边的图片
     * @param res      图片id
     */
    private void setDrawableLeft(int res) {
        mIvBack.setImageResource(res);
    }

    /**
     * 设置位于TextView左边的图片
     * @param res      图片id
     */
    private void setDrawableRight(int res) {
        mIvNext.setImageResource(res);
    }

    /**
     * 设置文字与图片的间距，默认为8px {@link #defaultDrawablePadding}
     */
    public void setDrawablePadding(int padding) {
        if (padding >= 0) {
            mLeftTv.setCompoundDrawablePadding(padding);
            mRightTv.setCompoundDrawablePadding(padding);
        } else {
            mLeftTv.setCompoundDrawablePadding(defaultDrawablePadding);
            mRightTv.setCompoundDrawablePadding(defaultDrawablePadding);
        }
    }

    /**
     * 设置标题栏背景
     */
    public void setTitleBarBackgroundRes(int resId) {
        rl_titleBar.setBackgroundResource(resId);
    }

    public void setLeftTextColor(int color) {
        mLeftTv.setTextColor(color);
    }

    public void setRightTextColor(int color) {
        mRightTv.setTextColor(color);
    }

    public void setTitleTextColor(int color) {
        mTitleTv.setTextColor(color);
    }
}
