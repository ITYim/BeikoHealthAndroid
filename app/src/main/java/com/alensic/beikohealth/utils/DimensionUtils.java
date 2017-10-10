package com.alensic.beikohealth.utils;

import com.alensic.beikohealth.base.GlobalParams;

/**
 * 单位转换工具类
 * Created by zym on 2016/8/10.
 */
public class DimensionUtils {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     * @param dpValue 值
     * @return 转换结果
     */
    public static int dp2px(float dpValue) {
        final float scale = GlobalParams.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     * @param pxValue 值
     * @return 转换结果
     */
    public static int px2dp(float pxValue) {
        final float scale = GlobalParams.getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp转px
     * @param spValue 值
     * @return 转换结果
     */
    public static int sp2px(float spValue) {
        final float fontScale = GlobalParams.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * px转sp
     * @param pxValue 值
     * @return 转换结果
     */
    public static int px2sp(float pxValue) {
        final float fontScale = GlobalParams.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
}
