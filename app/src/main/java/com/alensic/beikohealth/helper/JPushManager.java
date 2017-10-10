package com.alensic.beikohealth.helper;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alensic.beikohealth.R;
import com.alensic.beikohealth.base.AppConfig;
import com.alensic.beikohealth.base.GlobalParams;
import com.alensic.beikohealth.utils.PreferencesUtils;
import com.yim.base.common.ICallBack;
import com.yim.base.utils.Logger;
import com.yim.base.utils.PatternUtils;

import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
/**
 * 获取极光推送信息的工具类
 * Created by 郑依民 on 2016/7/30.
 */
@SuppressWarnings("unused")
public class JPushManager {

    private static final int WHAT_SET_ALIAS = 0x1;
    private ICallBack<Boolean> mCallBack;
    private AliasHandler mHandler;

    private JPushManager() {
        mHandler = new AliasHandler(this);
    }

    public static JPushManager getDefault() {
        return JPushHolder.INSTANCE;
    }

    /**
     * 极光推送初始化
     *
     * @param context ApplicationContext
     */
    public static void init(Context context) {
        JPushInterface.setDebugMode(AppConfig.DEBUG);
        JPushInterface.init(context);
        JPushInterface.setLatestNotificationNumber(context, AppConfig.J_PUSH_NOTIFICATION_MAX_COUNT);
    }

    /**
     * 定制极光推送通知栏样式
     */
    public static void buildNotification(Context context) {
        /*  默认布局    */
        BasicPushNotificationBuilder basicPushNotificationBuilder = new BasicPushNotificationBuilder(context);
        basicPushNotificationBuilder.statusBarDrawable = R.mipmap.ic_launcher;
        JPushInterface.setDefaultPushNotificationBuilder(basicPushNotificationBuilder);
    }

    /**
     * 集成了 JPush SDK 的应用程序在第一次成功注册到 JPush 服务器时
     * JPush 服务器会给客户端返回一个唯一的该设备的标识 - RegistrationID。
     *
     * @param context ApplicationContext
     * @return RegistrationID
     */
    public static String getRegistrationID(Context context) {
        String id = PreferencesUtils.getInstance().getStringData(PreferencesUtils.PUSH_REGISTRATION_ID);
        if (!TextUtils.isEmpty(id)) {
            return id;
        }
        return JPushInterface.getRegistrationID(context.getApplicationContext());
    }

    /**
     * 获取当前Push服务的连接状态
     *
     * @param context ApplicationContext
     * @return 是否已连接
     */
    public static boolean getJPushConnectState(Context context) {
        return JPushInterface.getConnectionState(context.getApplicationContext());
    }

    /**
     * 设置别名
     *
     * @param alias 别名
     */
    public void setAlias(String alias) {
        try {
            if (mHandler.hasMessages(WHAT_SET_ALIAS)) {
                mHandler.removeMessages(WHAT_SET_ALIAS);
            }
            if (PatternUtils.matchJPushAlias(new String(alias.getBytes(), "UTF-8"))) {
                setAliasCallBack(null);
                Message msg = Message.obtain(mHandler);
                msg.what = WHAT_SET_ALIAS;
                msg.obj = alias;
                mHandler.sendMessage(msg);
            } else {
                Logger.e("别名不合法");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置别名
     *
     * @param alias 别名
     */
    public void setAlias(String alias, ICallBack<Boolean> callback) {
        setAliasCallBack(callback);
        try {
            if (mHandler.hasMessages(WHAT_SET_ALIAS)) {
                mHandler.removeMessages(WHAT_SET_ALIAS);
            }
            if (PatternUtils.matchJPushAlias(new String(alias.getBytes(), "UTF-8"))) {
                Message msg = Message.obtain(mHandler);
                msg.what = WHAT_SET_ALIAS;
                msg.obj = alias;
                mHandler.sendMessage(msg);
            } else {
                Logger.e("别名不合法");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void setAliasCallBack(ICallBack<Boolean> callBack) {
        mCallBack = callBack;
    }

    private TagAliasCallback tagAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> set) {
            boolean success = (code == 0);
            if (success) {
                if (mCallBack != null) {
                    mCallBack.callback(Boolean.TRUE);
                } else {
                    PreferencesUtils.getInstance().saveBooleanData(PreferencesUtils.ALIAS_SAVED, false);
                }
            } else {
                if (mCallBack != null)
                    mCallBack.callback(Boolean.FALSE);
                Message msg = Message.obtain(mHandler);
                msg.what = WHAT_SET_ALIAS;
                msg.obj = alias;
                mHandler.sendMessageDelayed(msg, AppConfig.ALIAS_SETTING_DELAY);
            }
            Logger.d("设置别名\"" + alias + (success ? "\"成功" : "\"失败"));
        }
    };

    private static class AliasHandler extends Handler {
        private WeakReference<JPushManager> wr;

        public AliasHandler(JPushManager instance) {
            wr = new WeakReference<>(instance);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT_SET_ALIAS:
                    String alias = (String) msg.obj;
                    TagAliasCallback callback = null;
                    if (wr.get() != null) {
                        callback = wr.get().tagAliasCallback;
                    }
                    JPushInterface.setAlias(GlobalParams.getContext(), alias, callback);
                    break;
            }
        }
    }

    public static void setTag(Context context, String[] tags) {
        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList(tags));
        JPushInterface.setTags(context, set, new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                boolean success = (i == 0);
                Logger.d("设置标签" + set.toString() + (success ? "成功" : "失败"));
            }
        });
    }

    public static void setAliasAndTags(Context context, String alias, String[] tags) {
        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList(tags));
        JPushInterface.setAliasAndTags(context, alias, set, new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                boolean success = (i == 0);
                Logger.d("设置标签 " + s + "，标签" + set.toString() + (success ? "成功" : "失败"));
            }
        });
    }

    /**
     * 取消通知栏的通知信息
     *
     * @param context ApplicationContext
     */
    public static void clearAllNotifications(Context context) {
        JPushInterface.clearAllNotifications(context.getApplicationContext());
    }

    /**
     * 取消通知栏的通知信息
     *
     * @param context        ApplicationContext
     * @param notificationId 通知ID
     */
    public static void clearNotification(Context context, int notificationId) {
        JPushInterface.clearNotificationById(context, notificationId);
    }

    /**
     * 在 Android 6.0 及以上的系统上，需要去请求一些用到的权限
     */
    public static void requestPermission(Context context) {
        if (context != null && context instanceof Activity)
            JPushInterface.requestPermission(context);
    }

    /**
     * 激活后台极光推送
     */
    public static void wakeUpJPushService(Context context, final Handler handler, final String params) {
//        HttpSubscriber<Object> subscriber = new HttpSubscriber<>(new HttpCallBack<Object>() {
//            @Override
//            public void callback(Object result) {
//            }
//
//            @Override
//            public void onError(String msg) {
//                if (handler != null)
//                    handler.sendEmptyMessageDelayed(0, 5 * 1000);
//            }
//        }, context, true, false);
//        HttpManager.createInstance().doRequest(new HttpResponse(subscriber) {
//            @Override
//            public Observable getObservable(HttpService methods) {
//                return methods.wakeUpJPushService(params);
//            }
//        });
    }

    private static class JPushHolder {
        private static final JPushManager INSTANCE = new JPushManager();
    }
}
