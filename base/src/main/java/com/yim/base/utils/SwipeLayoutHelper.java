package com.yim.base.utils;

import android.app.Activity;
import android.graphics.Color;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.jude.swipbackhelper.SwipeBackPage;

/**
 * Activity右滑关闭界面的工具类
 * Created by zym on 2017/5/26.
 */
public class SwipeLayoutHelper {

    public static void init(Activity activity) {
        SwipeBackHelper.onCreate(activity);
        SwipeBackHelper.getCurrentPage(activity)//获取当前页面
                .setSwipeBackEnable(true)//设置是否可滑动
                .setSwipeEdge(ScreenInfo.width / 2)//可滑动的范围。px。200表示为左边200px的屏幕
                .setSwipeEdgePercent(0.5f)//可滑动的范围。百分比。0.2表示为左边20%的屏幕
                .setSwipeSensitivity(0.9f)//对横向滑动手势的敏感程度。0为迟钝 1为敏感
                .setScrimColor(Color.parseColor("#00000000"))//底层阴影颜色
                .setClosePercent(0.5f)//触发关闭Activity百分比
                .setSwipeRelateEnable(true)// 是否与下一级activity联动(微信效果)。默认关
                .setSwipeRelateOffset(ScreenInfo.width / 2)//activity联动时的偏移量。默认500px。
                .setDisallowInterceptTouchEvent(false);//不抢占事件，默认关（事件将先由子View处理再由滑动关闭处理）
    }

    public static void postCreate(Activity activity) {
        SwipeBackHelper.onPostCreate(activity);
    }

    public static void destroy(Activity activity) {
        SwipeBackHelper.onDestroy(activity);
    }

    public static void disable(Activity activity) {
        SwipeBackPage page = SwipeBackHelper.getCurrentPage(activity);
        if (page != null) {
            page.setSwipeBackEnable(false);
        }
    }

    public static void setSwipeEdge(Activity activity, float edge) {
        SwipeBackPage page = SwipeBackHelper.getCurrentPage(activity);
        if (page != null) {
            page.setSwipeEdge((int) (ScreenInfo.width * edge));
        }
    }
}
