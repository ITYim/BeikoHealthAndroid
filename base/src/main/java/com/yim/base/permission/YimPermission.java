package com.yim.base.permission;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.yim.base.BuildConfig;
import com.yim.base.utils.Logger;

/**
 * @author zym
 * @since 2017-08-24 11:14
 */
public class YimPermission {

    public static boolean checkPermission(Context context, String permission) {
        return !Utils.is23() || ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestPermission(Fragment fragment, String permission) {
        Log.d("zym", "BUILD_TYPE:" + BuildConfig.BUILD_TYPE);
        if (!Utils.is23()) {
            Logger.d("当前系统版本低于6.0，sdk23");
            if (fragment instanceof PermissionCallback) {
                ((PermissionCallback) fragment).onPermissionGranted(permission, Utils.getCodeByPermission(permission));
            }
            return;
        }
        BasePermissionHelper<Fragment> helper = BasePermissionHelper.newInstance(fragment);
        if (helper.checkPermission(fragment, permission)) {
            Logger.d("权限" + permission + "已经拥有");
            if (fragment instanceof PermissionCallback) {
                ((PermissionCallback) fragment).onPermissionGranted(permission, Utils.getCodeByPermission(permission));
            }
        }
        else if (helper.shouldShowRequestPermissionRationale(fragment, permission)){
            Logger.d("权限说明需要");
            helper.requestPermission(fragment, permission);
        }
        else {
            Logger.d("无权限，开始申请");
            helper.requestPermission(fragment, permission);
        }
    }

    public static void requestPermission(android.support.v4.app.Fragment fragment, String permission) {
        if (!Utils.is23()) {
            Logger.d("当前系统版本低于6.0，sdk23");
            if (fragment instanceof PermissionCallback) {
                ((PermissionCallback) fragment).onPermissionGranted(permission, Utils.getCodeByPermission(permission));
            }
            return;
        }
        BasePermissionHelper<android.support.v4.app.Fragment> helper = BasePermissionHelper.newInstance(fragment);
        if (helper.checkPermission(fragment, permission)) {
            if (fragment instanceof PermissionCallback) {
                ((PermissionCallback) fragment).onPermissionGranted(permission, Utils.getCodeByPermission(permission));
            }
        }
        else if (helper.shouldShowRequestPermissionRationale(fragment, permission)){
            helper.requestPermission(fragment, permission);
        }
        else {
            helper.requestPermission(fragment, permission);
        }
    }

    public static void requestPermission(Activity activity, String permission) {
        if (!Utils.is23()) {
            Logger.warn("当前系统版本低于6.0，sdk23");
            if (activity instanceof PermissionCallback) {
                ((PermissionCallback) activity).onPermissionGranted(permission, Utils.getCodeByPermission(permission));
            }
            return;
        }
        BasePermissionHelper<Activity> helper = BasePermissionHelper.newInstance(activity);
        if (helper.checkPermission(activity, permission)) {
            if (activity instanceof PermissionCallback) {
                ((PermissionCallback) activity).onPermissionGranted(permission, Utils.getCodeByPermission(permission));
            }
        }
        else if (helper.shouldShowRequestPermissionRationale(activity, permission)){
            helper.requestPermission(activity, permission);
        }
        else {
            helper.requestPermission(activity, permission);
        }
    }

    public static void onPermissionRequestCallback(PermissionCallback callback, int requestCode, String[] permissions, int[] grantResults) {
        if (!Utils.is23()) {
            Logger.warn("当前系统版本低于6.0，sdk23");
            return;
        }
        if (callback == null)
            return;
        for (int i = 0, size = permissions.length; i < size; i++) {
            if (grantResults[i] == Permissions.PERMISSION_GRANTED) {
                callback.onPermissionGranted(permissions[i], requestCode);
            } else {
                callback.onPermissionDenied(permissions[i], requestCode);
            }
        }
    }

    public interface PermissionCallback {

        void onPermissionGranted(String permission, int requestCode);

        void onPermissionDenied(String permission, int requestCode);

    }
}
