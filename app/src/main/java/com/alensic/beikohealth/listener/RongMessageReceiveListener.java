package com.alensic.beikohealth.listener;

import android.content.Context;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;

/**
 * @author zym
 * @since 2017-08-29 10:11
 */
public class RongMessageReceiveListener implements RongIMClient.OnReceiveMessageListener {
    private Context context;
    public RongMessageReceiveListener(Context context) {
        this.context = context;
    }

    @Override
    public boolean onReceived(Message message, int i) {
        return false;
    }
}
