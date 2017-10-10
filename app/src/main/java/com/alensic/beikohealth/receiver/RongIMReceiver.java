package com.alensic.beikohealth.receiver;

import android.content.Context;

import com.yim.base.utils.Logger;

import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;

/**
 * @author zym
 * @since 2017-08-02 11:27
 */
public class RongIMReceiver extends PushMessageReceiver {
    @Override
    public boolean onNotificationMessageArrived(Context context, PushNotificationMessage pushNotificationMessage) {
        Logger.d("onNotificationMessageArrived");
        return false;
    }

    @Override
    public boolean onNotificationMessageClicked(Context context, PushNotificationMessage pushNotificationMessage) {
        Logger.d("onNotificationMessageClicked");
        return false;
    }
}
