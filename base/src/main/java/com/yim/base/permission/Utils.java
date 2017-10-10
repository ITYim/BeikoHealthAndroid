package com.yim.base.permission;

import android.os.Build;

/**
 * @author zym
 * @since 2017-08-24 10:48
 */
public class Utils {

    public static boolean is23() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static int getCodeByPermission(String permission) {
        if (permission == null)
            return Permissions.UNKNOWN_PERMISSION_CODE;
        if (permission.equals(Permissions.PHONE.PERMISSION_PHONE)) {
            return Permissions.PHONE.CODE_PHONE;
        }
        if (permission.equals(Permissions.CAMERA.PERMISSION_CAMERA)) {
            return Permissions.CAMERA.CODE_CAMERA;
        }
        if (permission.equals(Permissions.LOCATION.PERMISSION_FINE_LOCATION)) {
            return Permissions.LOCATION.CODE_FINE_LOCATION;
        }
        if (permission.equals(Permissions.LOCATION.PERMISSION_COARSE_LOCATION)) {
            return Permissions.LOCATION.CODE_COARSE_LOCATION;
        }
        if (permission.equals(Permissions.MICROPHONE.PERMISSION_RECORD_AUDIO)) {
            return Permissions.MICROPHONE.CODE_RECORD_AUDIO;
        }
        if (permission.equals(Permissions.STORAGE.PERMISSION_READ_EXTERNAL_STORAGE)) {
            return Permissions.STORAGE.CODE_READ_EXTERNAL_STORAGE;
        }
        if (permission.equals(Permissions.STORAGE.PERMISSION_WRITE_EXTERNAL_STORAGE)) {
            return Permissions.STORAGE.CODE_WRITE_EXTERNAL_STORAGE;
        }
        return Permissions.UNKNOWN_PERMISSION_CODE;
    }
}
