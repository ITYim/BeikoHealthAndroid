package com.alensic.beikohealth.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alensic.beikohealth.R;
import com.yim.base.utils.FormatCheckUtils;

import java.util.Locale;

/**
 * 封装Button的功能业务，完成验证码的发送逻辑
 * Created by zym on 2017/3/24.
 */
public class AuthCodeButton extends TextView implements View.OnClickListener {
    private AuthState mCurrentState;
    private AuthHandler mHandler;
    private Callback mCallback;
    private long sendCodeTime;    // 是否触发过发送验证码

    private String phone;
    private EditText phoneInputEditText;

    private static final String[] BTN_TEXT = {"发送验证码", "剩余%ds", "正在发送", "重新发送", "再次发送"};

    public AuthCodeButton(Context context) {
        this(context, null);
    }

    public AuthCodeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        sendCodeTime = 0;
        setOnClickListener(this);
        setSelector();
        mHandler = new AuthHandler(this);
        mCurrentState = AuthState.STATE_NORMAL;
        updateState(mCurrentState);
    }

    private void updateState(AuthState newState) {
        if (newState == AuthState.STATE_NORMAL) {
            setText(BTN_TEXT[0]);
        } else if (newState == AuthState.STATE_WAITING) {
            setText(String.format(Locale.CHINA, BTN_TEXT[1], mHandler.getCurrentSecond()));
        } else if (newState == AuthState.STATE_SENDING) {
            setText(BTN_TEXT[2]);
        } else if (newState == AuthState.STATE_RESEND) {
            setText(BTN_TEXT[3]);
            setEnabled(true);
        } else if (newState == AuthState.STATE_WAITING_COMPLETE) {
            setText(BTN_TEXT[4]);
            setEnabled(true);
        }
    }

    @Override
    public void onClick(View v) {
        setEnabled(false);
        String phone = getPhone();
        if (!FormatCheckUtils.checkPhoneNumber(phone)) {
            Toast.makeText(getContext(), "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
        }
        else if (mCurrentState == AuthState.STATE_NORMAL || mCurrentState == AuthState.STATE_RESEND || mCurrentState == AuthState.STATE_WAITING_COMPLETE) {
            mCurrentState = AuthState.STATE_SENDING;
            updateState(mCurrentState);
//            SMSSDK.getVerificationCode("86", phone);  // 请求发送验证码
            sendCodeTime = System.currentTimeMillis();
            if (mCallback != null)
                mCallback.startSend();
            return;
        }
        setEnabled(true);
    }

    public boolean isHasSendCode() {
        return sendCodeTime != 0;
    }

    public long getSendCodeTime() {
        return sendCodeTime;
    }

    private String getPhone() {
        String result = phone;
        if (phoneInputEditText != null)
            result = phoneInputEditText.getText().toString();
        return result;
    }

    private void authSendSuccess() {
        Toast.makeText(getContext(), "验证码发送成功，请注意查收", Toast.LENGTH_SHORT).show();
        mCurrentState = AuthState.STATE_WAITING;
        updateState(mCurrentState);
        backCount();
    }

    private void authSendError(String msg) {
        Toast.makeText(getContext(), TextUtils.isEmpty(msg) ? "验证码发送失败，请检查网络是否可用" : msg, Toast.LENGTH_SHORT).show();
        mCurrentState = AuthState.STATE_RESEND;
        updateState(mCurrentState);
    }

    private void backCount() {
        mHandler.removeMessages(AuthHandler.WHAT_COUNT);
        mHandler.sendEmptyMessage(AuthHandler.WHAT_COUNT);
    }

    /**
     * 设置收取验证码的手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 设置关联输入框，用于获取收取验证码的手机号码
     */
    public void setPhoneInputEditText(EditText phoneInputEditText) {
        this.phoneInputEditText = phoneInputEditText;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    /**
     * 设置两次发送的时间间隔
     */
    public void setTotalSecond(int totalSecond) {
        mHandler.setTotalSecond(totalSecond);
    }

    static class AuthHandler extends Handler {

        static final int WHAT_COUNT = 0x1;

        private static final int DEFAULT_TOTAL_SECONDS = 60;
        private int totalSecond = DEFAULT_TOTAL_SECONDS;
        private int currentSecond = totalSecond;

        private AuthCodeButton btn;
        AuthHandler(AuthCodeButton btn) {
            this.btn = btn;
        }

        void release() {
            this.currentSecond = this.totalSecond;
            if (hasMessages(WHAT_COUNT)) {
                removeMessages(WHAT_COUNT);
            }
        }

        void setTotalSecond(int totalSecond) {
            this.totalSecond = totalSecond;
        }

        int getCurrentSecond() {
            return currentSecond;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT_COUNT:
                    if (currentSecond == 0) {
                        btn.mCurrentState = AuthState.STATE_WAITING_COMPLETE;
                        btn.updateState(btn.mCurrentState);
                        this.release();
                        break;
                    }
                    btn.mCurrentState = AuthState.STATE_WAITING;
                    btn.updateState(btn.mCurrentState);
                    this.sendEmptyMessageDelayed(WHAT_COUNT, 1000L);
                    currentSecond--;
                    break;
            }
        }
    }

    private void setSelector() {
        DrawableSelector drawableSelector = new DrawableSelector();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(null);
        } else {
            setBackgroundDrawable(null);
        }
        setTextColor(drawableSelector.getColorStateList());
    }

    enum AuthState {
        STATE_NORMAL,
        STATE_RESEND,
        STATE_SENDING,
        STATE_WAITING,
        STATE_WAITING_COMPLETE
    }

    class DrawableSelector {

        StateListDrawable getSelector() {
            StateListDrawable selector = new StateListDrawable();
            Drawable normalDrawable = getGradientDrawable(Color.parseColor("#00000000"), Color.parseColor("#333333"));
            Drawable pressDrawable = getGradientDrawable(Color.parseColor("#E1E1E1"), Color.parseColor("#333333"));
            Drawable disableDrawable = getGradientDrawable(Color.parseColor("#00000000"), Color.parseColor("#999999"));
            selector.addState(new int[]{android.R.attr.state_pressed}, pressDrawable);
            selector.addState(new int[]{-android.R.attr.state_enabled}, disableDrawable);
            selector.addState(new int[]{}, normalDrawable);
            return selector;
        }

        GradientDrawable getGradientDrawable(int color, int strokeColor) {
            GradientDrawable shape = new GradientDrawable();
            shape.setShape(GradientDrawable.RECTANGLE);
            shape.setCornerRadius(dp2px(8f));
            shape.setColor(color);
            shape.setStroke(1, strokeColor);
            return shape;
        }

        ColorStateList getColorStateList() {
            int[][] states = {{android.R.attr.state_enabled}, {android.R.attr.state_pressed}, {-android.R.attr.state_enabled}};
            int[] colors = {ContextCompat.getColor(getContext(), R.color.main_theme),
                    ContextCompat.getColor(getContext(), R.color.main_theme_press),
                    ContextCompat.getColor(getContext(), R.color.main_theme_disable)};
            return new ColorStateList(states, colors);
        }
    }

    private int dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public interface Callback {
        void startSend();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.release();
//        if (eh != null)
//            SMSSDK.unregisterEventHandler(eh);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
//        SMSSDK.registerEventHandler(eh = new EventHandler() {
//            @Override
//            public void afterEvent(int event, final int result, final Object data) {
//                post(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (result == SMSSDK.RESULT_COMPLETE) {
//                            authSendSuccess();
//                        } else {
//                            String detail = null;
//                            try {
//                                Throwable throwable = (Throwable) data;
//                                throwable.printStackTrace();
//                                JSONObject object = new JSONObject(throwable.getMessage());
//                                detail = object.optString("detail");//错误描述
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                            authSendError(detail);
//                        }
//                    }
//                });
//            }
//        });
    }
}
