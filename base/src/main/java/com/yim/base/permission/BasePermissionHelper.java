package com.yim.base.permission;

import android.app.Activity;
import android.app.Fragment;

/**
 * @author zym
 * @since 2017-08-24 14:02
 */
abstract class BasePermissionHelper<T> {
    private T host;

    BasePermissionHelper(T host) {
        this.host = host;
    }

    static BasePermissionHelper<Fragment> newInstance(Fragment fragment) {
        return new FragmentPermissionHelper(fragment);
    }

    static BasePermissionHelper<android.support.v4.app.Fragment> newInstance(android.support.v4.app.Fragment fragment) {
        return new SupportFragmentPermissionHelper(fragment);
    }

    static BasePermissionHelper<Activity> newInstance(Activity activity) {
        return new ActivityPermissionHelper(activity);
    }

    public abstract boolean checkPermission(T instance, String permission);

    public abstract void requestPermission(T instance, String permission);

    public abstract boolean shouldShowRequestPermissionRationale(T instance, String permission);

    public T getHost() {
        return host;
    }

}
