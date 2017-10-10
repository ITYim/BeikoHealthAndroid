package com.yim.base;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.yim.base.common.ICommonUI;
import com.yim.base.utils.Logger;
import com.yim.base.utils.ScreenInfo;

import butterknife.ButterKnife;

/**
 * @author zym
 * @since 2017-08-07 15:14
 */
public abstract class BaseDialogFragment extends DialogFragment implements ICommonUI {
    private final String TAG = getClass().getSimpleName();
    protected View rootView;
    private Size mDialogSize;

    protected Context context;

    public BaseDialogFragment() {
        Logger.d(TAG, "BaseDialogFragment()");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Logger.d(TAG, "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(TAG, "onCreate");
        context = getActivity();
        fetchArgument(getArguments());
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MyDialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Logger.d(TAG, "onCreateView");
        rootView = getActivity().getLayoutInflater().inflate(layoutId(), null);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Logger.d(TAG, "onViewCreated");
        ButterKnife.bind(this, rootView);
        initView();
        initListener();
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.d(TAG, "onResume");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.d(TAG, "onActivityCreated");
        setDialogStyle();
        fetchData();
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.d(TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.d(TAG, "onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.d(TAG, "onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logger.d(TAG, "onDetach");
    }

    /**
     * 设置对话框风格和属性
     */
    @SuppressWarnings("ConstantConditions")
    private void setDialogStyle() {
        try{
            Dialog dialog = getDialog();
            dialog.setCanceledOnTouchOutside(cancelOnTouchOutside());
            dialog.setCancelable(cancelable());
            Window window = dialog.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.dimAmount = .1f;
            attributes.width = mDialogSize == null ? ScreenInfo.width / 10 * 8 : mDialogSize.width;
            attributes.height = mDialogSize == null ? ViewGroup.LayoutParams.WRAP_CONTENT : mDialogSize.height;
            window.setAttributes(attributes);
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            customDialog(dialog);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 是否可以关闭该Dialog
     */
    protected boolean cancelable() {
        return true;
    }

    /**
     * 点击外部是否可以关闭该Dialog
     */
    protected boolean cancelOnTouchOutside() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends View> T findView(int resId) {
        return (T) rootView.findViewById(resId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends View> T findView(View view, int resId) {
        return (T) view.findViewById(resId);
    }

    /**
     * 提供给子类获取参数使用
     */
    protected void fetchArgument(Bundle argument) {
        // do nothing
    }

    @Override
    public void initView() {
    }

    @Override
    public void initListener() {
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void fetchData() {
    }

    public void setDialogSize(Size size) {
        this.mDialogSize = size;
    }

    public class Size {
        public int width, height;
        public Size(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }

    /**
     * 交给子类自定义特定的属性
     */
    protected void customDialog(Dialog dialog) {
        // do nothing
    }

    public void show(FragmentManager manager) {
        super.show(manager, TAG);
    }

    public int show(FragmentTransaction transaction) {
        return super.show(transaction, TAG);
    }
}
