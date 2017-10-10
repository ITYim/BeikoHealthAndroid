package com.alensic.beikohealth.helper;

import org.greenrobot.eventbus.EventBus;

/**
 * 事件订阅和取消，执行
 * 替代onActivityForResult等事件传递
 * Created by zym on 2016/8/29 0029.
 */
public class EventManager {

    public static void register(Object context) {
        if (!EventBus.getDefault().isRegistered(context)) {
            EventBus.getDefault().register(context);
        }
    }

    public static void unregister(Object context) {
        if (EventBus.getDefault().isRegistered(context)) {
            EventBus.getDefault().unregister(context);
        }
    }

    public static void post(EventEntity object) {
        EventBus.getDefault().post(object);
    }

    public static void postSticky(EventEntity object) {
        EventBus.getDefault().postSticky(object);
    }
}
