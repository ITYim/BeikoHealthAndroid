package com.alensic.beikohealth.base;

import android.content.Context;

/**
 * @author zym
 * @since 2017-08-23 17:57
 */
public class GlobalParams {

    public static Context getContext() {
        return BeikoApplication.getInstance();
    }

    public static BeikoApplication getApplication() {
        return BeikoApplication.getInstance();
    }

}
