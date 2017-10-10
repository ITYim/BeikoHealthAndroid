package com.yim.base.permission;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;

/**
 * @author zym
 * @since 2017-08-24 11:30
 */
@SuppressWarnings("unused")
public class Permissions {

    public static final int PERMISSION_GRANTED = PackageManager.PERMISSION_GRANTED;
    public static final int PERMISSION_DENIED = PackageManager.PERMISSION_DENIED;

    public static final String UNKNOWN_PERMISSION = "UNKNOWN_PERMISSION";
    public static final int UNKNOWN_PERMISSION_CODE = 0x0;

    public interface PHONE {
        String PERMISSION_PHONE = Manifest.permission.CALL_PHONE;
        int CODE_PHONE = 0x01;
    }

    public interface CAMERA {
        String PERMISSION_CAMERA = Manifest.permission.CAMERA;
        int CODE_CAMERA = 0x02;
    }

    public interface LOCATION {
        String PERMISSION_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
        int CODE_FINE_LOCATION = 0x03;
        String PERMISSION_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
        int CODE_COARSE_LOCATION = 0x04;
    }

    @SuppressLint("InlinedApi")
    public interface STORAGE {
        String PERMISSION_WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        int CODE_WRITE_EXTERNAL_STORAGE = 0x05;
        String PERMISSION_READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
        int CODE_READ_EXTERNAL_STORAGE = 0x06;
    }

    public interface MICROPHONE {
        String PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;
        int CODE_RECORD_AUDIO = 0x07;
    }

}
