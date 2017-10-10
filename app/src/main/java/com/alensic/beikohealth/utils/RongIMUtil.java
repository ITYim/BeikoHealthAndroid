package com.alensic.beikohealth.utils;

import android.content.Context;
import android.net.Uri;

import com.alensic.beikohealth.base.GlobalParams;
import com.alensic.beikohealth.bean.ImageOnlyExtensionModule;
import com.alensic.beikohealth.http.HttpManager;
import com.alensic.beikohealth.listener.RongConnectListener;
import com.alensic.beikohealth.listener.RongMessageReceiveListener;
import com.yim.base.utils.Logger;

import java.util.List;
import java.util.Map;
import java.util.Random;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author zym
 * @since 2017-08-02 15:59
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class RongIMUtil {
    private static final String TAG = "RongIM";

    public static void init(Context context, String packageName) {
        if (packageName.equals(DeviceUtils.getCurrentProcessName(context))) {
            RongIM.init(context);
            RongIM.setOnReceiveMessageListener(new RongMessageReceiveListener(context));
            RongIM.setConnectionStatusListener(new RongConnectListener());
            resetExtensionModule();
            setUserInfo();
        }
    }

    private static void setUserInfo() {
        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
            @Override
            public UserInfo getUserInfo(String s) {
                return null;
            }
        }, true);
    }

    public static void refreshUserInfo(String userId, String userName, String portraitUri) {
        UserInfo userInfo = new UserInfo(userId, userName, Uri.parse(portraitUri));
        RongIM.getInstance().refreshUserInfoCache(userInfo);
    }

    /**
     * 设置扩展区只能选择图片
     */
    private static void resetExtensionModule() {
        List<IExtensionModule> extensionModules = RongExtensionManager.getInstance().getExtensionModules();
        IExtensionModule module = null;
        for (IExtensionModule m : extensionModules) {
            if (m instanceof DefaultExtensionModule) {
                module = m;
                break;
            }
        }
        RongExtensionManager.getInstance().unregisterExtensionModule(module);
        RongExtensionManager.getInstance().registerExtensionModule(new ImageOnlyExtensionModule());
    }

    /**
     * 连接融云IM服务
     * 1、获取token
     * 2、根据token连接融云IM服务
     *
     * @param userId      用户id
     * @param userName    用户名
     * @param portraitUri 用户头像
     * @return 返回获取token的响应对象，用于更改该网络操作
     */
    @SuppressWarnings("unchecked")
    public static Subscriber connectRongIM(String userId, String userName, String portraitUri) {
        if (RongIM.getInstance().getCurrentConnectionStatus() == RongIMClient.ConnectionStatusListener.ConnectionStatus.CONNECTED) {
            return null;
        }
        HttpManager http = HttpManager.getManager();
        String appKey = GlobalParams.getContext().getString(io.rong.imlib.R.string.RONG_CLOUD_APP_KEY);
        String appSecret = GlobalParams.getContext().getString(io.rong.imlib.R.string.RONG_CLOUD_APP_SECRET);
        String nonce = "" + new Random().nextInt(100000);
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String signature = EncryptUtils.SHAT1(appSecret + nonce + timestamp);
        Subscriber subscriber = new Subscriber<Map>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showToast("获取融云Token异常");
            }

            @Override
            public void onNext(Map map) {
                String token = (String) map.get("token");
                int status = (int) map.get("code");
                if (status == 200) {
                    connectRongIM(token);
                } else {
                    ToastUtils.showToast("获取融云Token失败[" + status + "]");
                }
            }
        };
        http.getService().getToken(userId, userName, portraitUri, appKey, nonce, timestamp, signature)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        return subscriber;
    }

    private static void connectRongIM(String token) {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Logger.e(TAG, "融云服务连接失败[token错误]");
            }

            @Override
            public void onSuccess(String userId) {
                Logger.d(TAG, "融云服务连接成功[" + userId + "]");
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Logger.e(TAG, "融云服务连接失败[" + errorCode.name() + ": " + errorCode.getValue() + "=" + errorCode.getMessage() + "]");
            }
        });
    }

    /**
     * <p>断开与融云服务器的连接。当调用此接口断开连接后，仍然可以接收 Push 消息。</p>
     * <p>若想断开连接后不接受 Push 消息，可以调用{@link #logout()}</p>
     */
    public static void disconnect() {
        RongIMClient.ConnectionStatusListener.ConnectionStatus status = RongIM.getInstance().getCurrentConnectionStatus();
        if (status == RongIMClient.ConnectionStatusListener.ConnectionStatus.CONNECTED ||
                status == RongIMClient.ConnectionStatusListener.ConnectionStatus.CONNECTING) {
            RongIM.getInstance().disconnect();
        }
    }

    /**
     * <p>断开与融云服务器的连接，并且不再接收 Push 消息。</p>
     * <p>若想断开连接后仍然接受 Push 消息，可以调用 {@link #disconnect()}</p>
     * ps:此方法必须在主进程内调用
     */
    public static void logout() {
        RongIM.getInstance().logout();
    }
}
