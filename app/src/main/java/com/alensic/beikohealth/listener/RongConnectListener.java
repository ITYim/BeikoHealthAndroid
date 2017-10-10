package com.alensic.beikohealth.listener;

import com.yim.base.utils.Logger;

import io.rong.imlib.RongIMClient;

/**
 * @author zym
 * @since 2017-08-29 11:28
 */
public class RongConnectListener implements RongIMClient.ConnectionStatusListener{
    private static final String TAG = "RongIM";
    @Override
    public void onChanged(ConnectionStatus connectionStatus) {
        switch (connectionStatus) {
            case CONNECTING:    // 正在连接
                Logger.d(TAG, "融云正在连接");
                break;
            case CONNECTED:     // 连接成功
                Logger.d(TAG, "融云连接成功");
                break;
            case DISCONNECTED:  // 断开连接
                Logger.d(TAG, "融云断开连接");
                break;
            case NETWORK_UNAVAILABLE:   // 网络不可用
                Logger.d(TAG, "融云连接异常：网络不可用");
                break;
            case KICKED_OFFLINE_BY_OTHER_CLIENT:    // 该用户在其他端口登录，本机被踢下线
                Logger.d(TAG, "融云连接异常：它端登录，本机下线");
                break;
            case SERVER_INVALID:    // 服务器异常
                Logger.d(TAG, "融云连接异常：融云服务器异常");
                break;
            case TOKEN_INCORRECT:   // token错误
                Logger.d(TAG, "融云连接异常：token错误");
                break;
        }
    }
}
