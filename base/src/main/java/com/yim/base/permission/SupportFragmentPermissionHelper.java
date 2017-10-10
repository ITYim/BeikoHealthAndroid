package com.yim.base.permission;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

/**
 * @author zym
 * @since 2017-08-24 14:01
 */
@TargetApi(Build.VERSION_CODES.M)
class SupportFragmentPermissionHelper extends BasePermissionHelper<Fragment> {

    SupportFragmentPermissionHelper(Fragment host) {
        super(host);
    }

    @Override
    public boolean checkPermission(Fragment fragment, String permission) {
        return ActivityCompat.checkSelfPermission(fragment.getContext(), permission) == Permissions.PERMISSION_GRANTED;
    }

    @Override
    public void requestPermission(Fragment instance, String permission) {
        instance.requestPermissions(new String[]{permission}, Utils.getCodeByPermission(permission));
    }

    @Override
    public boolean shouldShowRequestPermissionRationale(Fragment fragment, String permission) {
        return fragment.shouldShowRequestPermissionRationale(permission);
    }

}
