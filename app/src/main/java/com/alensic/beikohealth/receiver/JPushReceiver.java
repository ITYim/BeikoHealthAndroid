package com.alensic.beikohealth.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.alensic.beikohealth.utils.PreferencesUtils;
import com.yim.base.utils.Logger;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义的极光广播接收者
 * 用来接收服务器推送的信息
 * Created by 郑依民 on 2016/7/28.
 */
public class JPushReceiver extends BroadcastReceiver {

    private static final String TAG = "JPushReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Logger.d(TAG, "EXTRA_ALERT： " + bundle.getString(JPushInterface.EXTRA_ALERT));
        Logger.d(TAG, "EXTRA_EXTRA： " + bundle.getString(JPushInterface.EXTRA_EXTRA));
        Logger.d(TAG, "EXTRA_MESSAGE： " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
        Logger.d(TAG, "EXTRA_NOTIFICATION_TITLE： " + bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE));

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            Logger.d(TAG, "JPush用户注册成功");
            PreferencesUtils.getInstance().saveStringData(PreferencesUtils.PUSH_REGISTRATION_ID, bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID));
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Logger.d(TAG, "接收到推送下来的自定义消息");
            receivingCustomMessage(context, bundle);
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Logger.d(TAG, "接收到推送下来的通知");
            receivingNotification(context, bundle);
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Logger.d(TAG, "用户点击打开了通知");
            openNotification(context, bundle);
        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            boolean connected = bundle.getBoolean(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            Logger.d(TAG, "极光服务连接发生变化:" + connected);
        } else {
            Logger.d(TAG, "Unhandled intent - " + intent.getAction());
        }
    }

    /**
     * 接收通知消息
     */
    private void receivingNotification(Context context, Bundle bundle) {

    }

    /**
     * 接收自定义消息
     */
    private void receivingCustomMessage(Context context, Bundle bundle) {

    }

    /**
     * 打开通知栏的消息
     */
    private void openNotification(Context context, Bundle bundle) {

    }

}
