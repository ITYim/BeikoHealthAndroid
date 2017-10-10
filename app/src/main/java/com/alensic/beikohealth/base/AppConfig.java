package com.alensic.beikohealth.base;

import com.alensic.beikohealth.BuildConfig;

/**
 * @author zym
 * @since 2017-08-09 10:54
 */

public interface AppConfig {

    boolean DEBUG = BuildConfig.DEBUG;   // 应用是否处于debug状态
    int J_PUSH_NOTIFICATION_MAX_COUNT = 8;   // 默认极光推送通知栏保留通知的最大数量

    long ALIAS_SETTING_DELAY = 10 * 1000;   // 设置别名失败，定时重新设置别名

}
