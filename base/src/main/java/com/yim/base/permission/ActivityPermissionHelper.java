package com.yim.base.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

/**
 * @author zym
 * @since 2017-08-24 14:01
 */
@TargetApi(Build.VERSION_CODES.M)
class ActivityPermissionHelper extends BasePermissionHelper<Activity> {

    ActivityPermissionHelper(Activity host) {
        super(host);
    }

    @Override
    public boolean checkPermission(Activity activity, String permission) {
        return ActivityCompat.checkSelfPermission(activity, permission) == Permissions.PERMISSION_GRANTED;
    }

    @Override
    public void requestPermission(Activity activity, String permission) {
        activity.requestPermissions(new String[]{permission}, Utils.getCodeByPermission(permission));
    }

    @Override
    public boolean shouldShowRequestPermissionRationale(Activity activity, String permission) {
        return activity.shouldShowRequestPermissionRationale(permission);
    }
}
