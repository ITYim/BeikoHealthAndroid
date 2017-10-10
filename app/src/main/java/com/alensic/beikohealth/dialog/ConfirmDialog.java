package com.alensic.beikohealth.dialog;

import android.app.DialogFragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alensic.beikohealth.R;
import com.yim.base.BaseDialogFragment;

import butterknife.BindView;

/**
 * @author zym
 * @since 2017-08-07 17:08
 */

public class ConfirmDialog extends BaseDialogFragment {
    @BindView(R.id.tv_confirm_dialog_title)
    TextView tv_confirm_dialog_title;
    @BindView(R.id.tv_confirm_dialog_content)
    TextView tv_confirm_dialog_content;
    @BindView(R.id.btn_confirm_dialog_confirm)
    TextView btn_confirm_dialog_confirm;
    @BindView(R.id.btn_confirm_dialog_cancel)
    TextView btn_confirm_dialog_cancel;

    private String mTitle, mContent;
    private OnConfirmListener mConfirmListener;
    private OnCancelListener mCancelListener;

    @Override
    public int layoutId() {
        return R.layout.dialog_confirm;
    }

    @Override
    public void initView() {
        tv_confirm_dialog_title.setText(TextUtils.isEmpty(mTitle) ? "提示" : mTitle);
        tv_confirm_dialog_content.setText(mContent);
    }

    @Override
    public void initListener() {
        btn_confirm_dialog_confirm.setOnClickListener(this);
        btn_confirm_dialog_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm_dialog_confirm:
                if (mConfirmListener != null) {
                    boolean result = mConfirmListener.onConfirm(this);
                    if (!result) {
                        dismiss();
                    }
                } else {
                    dismiss();
                }
                break;
            case R.id.btn_confirm_dialog_cancel:
                if (mCancelListener != null) {
                    boolean result = mCancelListener.onCancel(this);
                    if (!result) {
                        dismiss();
                    }
                } else {
                    dismiss();
                }
                break;
        }
    }

    @Override
    protected boolean cancelable() {
        return true;
    }

    @Override
    protected boolean cancelOnTouchOutside() {
        return false;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void setContent(String content) {
        this.mContent = content;
    }

    public void setOnConfirmListener(OnConfirmListener confirmListener) {
        this.mConfirmListener = confirmListener;
    }

    public void setOnCancelListener(OnCancelListener cancelListener) {
        this.mCancelListener = cancelListener;
    }

    public interface OnConfirmListener {
        boolean onConfirm(DialogFragment dialog);
    }

    public interface OnCancelListener {
        boolean onCancel(DialogFragment dialog);
    }
}
