package com.yim.base.common;

import android.view.View;

/**
 * Created by Administrator on 2017/3/17.
 */
public interface ICommonUI extends View.OnClickListener{

    int layoutId();

    void initView();

    void fetchData();

    void initListener();

    <T extends View> T findView(View view, int resId);

    <T extends View> T findView(int resId);

}
