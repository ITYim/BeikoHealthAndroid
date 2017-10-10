package com.alensic.beikohealth.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Looper;
import android.telephony.TelephonyManager;

import com.alensic.beikohealth.base.GlobalParams;

import java.util.List;

/**
 * 获取手机设备信息工具类
 * Created by 郑依民 on 2016/7/30.
 */
public class DeviceUtils {

    /**
     * 获取手机IMEI号
     * @param context
     * @return 每台手机的IMEI号是全球唯一
     */
    public static String getIMEI(Context context) {
        TelephonyManager TelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = TelephonyMgr.getDeviceId();
        return imei;
    }

    /**
     * 判断当前是否位于主线程
     * @return
     */
    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
//        return Thread.currentThread().getName().equals("main");
    }

    public static boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) GlobalParams.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo!=null && networkInfo.isConnected() && networkInfo.isAvailable();
    }

    public static boolean isWifiNetwork() {
        ConnectivityManager cm = (ConnectivityManager) GlobalParams.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    public static boolean isMobileNetwork() {
        ConnectivityManager cm = (ConnectivityManager) GlobalParams.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }

    /**
     * 检测服务是否运行
     * @param context context
     * @param serviceName service全类名
     * @return true Or false
     */
    public static boolean isServiceRunning(Context context, String serviceName) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services = am.getRunningServices(Integer.MAX_VALUE);
        if (services==null || services.size()==0)
            return false;
        for (ActivityManager.RunningServiceInfo info : services) {
            if (info.service.getClassName().equals(serviceName)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 判断应用是否在前台运行
     */
    public static boolean isAppFront(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : runningAppProcesses) {
            if (info.processName.equals(context.getPackageName())) {
                return info.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;
            }
        }
        return false;
    }

    public static String getVersionName(Context context) throws Exception {
        if (context == null) {
            PackageInfo packageInfo = GlobalParams.getContext().getPackageManager().getPackageInfo(GlobalParams.getContext().getPackageName(), 0);
            return packageInfo.versionName;
        }
        else {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        }
    }

    public static int getVersionCode(Context context) throws Exception {
        if (context == null) {
            PackageInfo packageInfo = GlobalParams.getContext().getPackageManager().getPackageInfo(GlobalParams.getContext().getPackageName(), 0);
            return packageInfo.versionCode;
        }
        else {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        }
    }

    /**
     * 获取android系统版本号
     */
    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取当前进程名称
     */
    public static String getCurrentProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
