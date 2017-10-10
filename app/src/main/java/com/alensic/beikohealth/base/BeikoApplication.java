package com.alensic.beikohealth.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import com.alensic.beikohealth.BuildConfig;
import com.alensic.beikohealth.db.DBManager;
import com.alensic.beikohealth.helper.JPushManager;
import com.alensic.beikohealth.utils.DeviceUtils;
import com.alensic.beikohealth.utils.FilePathUtils;
import com.alensic.beikohealth.utils.RongIMUtil;
import com.tencent.bugly.crashreport.CrashReport;
import com.yim.base.utils.Logger;
import com.yim.base.utils.ScreenInfo;

/**
 * 自定义Application，做项目的初始化操作和界面的管理
 * Created by 郑依民 on 2016/7/21.
 */
public class BeikoApplication extends Application implements Application.ActivityLifecycleCallbacks {
    private AppManager mAppManager;
    private static BeikoApplication instance;
    private CompositeSubscription mCompositeSubscription;

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.d("当前进程：" + DeviceUtils.getCurrentProcessName(getApplicationContext()));
        // 初始化主进程
        if (getPackageName().equals(DeviceUtils.getCurrentProcessName(getApplicationContext()))) {
            instance = this;
            mAppManager = AppManager.getInstance();
            ScreenInfo.init(this);
            // 初始化项目根目录文件
            FilePathUtils.init();
            // 极光推送初始化
            JPushManager.init(getApplicationContext());
            JPushManager.buildNotification(getApplicationContext());
            // 初始化Bugly
            initCrashReport(getApplicationContext());
            DBManager.initDatabase(getApplicationContext());
            RongIMUtil.init(getApplicationContext(), getApplicationInfo().packageName);
            registerActivityLifecycleCallbacks(this);
            ButterKnife.setDebug(BuildConfig.DEBUG);
        }
    }

    /**
     * 配置bugly日志上传参数
     */
    private static void initCrashReport(Context context) {
        // Bugly初始化
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        try {
            strategy.setAppVersion(DeviceUtils.getVersionName(context));
        } catch (Exception e) {
            e.printStackTrace();
        }
        strategy.setAppPackageName(context.getPackageName());
        CrashReport.putUserData(context, "DEBUG_MODEL", "(" + AppConfig.DEBUG + ")");
        CrashReport.putUserData(context, "BUILD_TYPE", "(" + BuildConfig.BUILD_TYPE + ")");
        CrashReport.initCrashReport(context, strategy);
    }

    public void addSubscriber(Subscription subscription) {
        if (mCompositeSubscription == null || mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    public void unsubscribe() {
        if (mCompositeSubscription != null && !mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    /**
     * 获取MyApplication实例
     */
    static BeikoApplication getInstance() {
        return instance;
    }

    public AppManager getAppManager() {
        return mAppManager;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (mAppManager != null && activity != null) {
            mAppManager.push(activity);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (mAppManager != null && activity != null) {
            mAppManager.pop(activity);
        }
    }
}
