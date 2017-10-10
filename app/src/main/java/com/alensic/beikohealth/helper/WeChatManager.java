package com.alensic.beikohealth.helper;

import android.content.Context;
import android.content.Intent;

import com.alensic.beikohealth.BuildConfig;
import com.alensic.beikohealth.utils.ToastUtils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yim.base.utils.Logger;

import java.util.UUID;

/**
 * @author zym
 * @since 2017-08-08 17:25
 */
public class WeChatManager {

    private static WeChatManager INSTANCE = new WeChatManager();
    private static IWXAPI wxApi;

    private WeChatManager() {
    }

    public static WeChatManager getInstance() {
        return INSTANCE;
    }

    public static void registerToWX(Context context) {
        String appId = BuildConfig.WX_APPID;
        wxApi = WXAPIFactory.createWXAPI(context, appId, true);
        boolean registered = wxApi.registerApp(appId);
        Logger.d("注册到微信" + (registered ? "成功" : "失败"));
    }

    public static boolean handleIntent(Intent intent, IWXAPIEventHandler handler) {
        if (wxApi == null) {
            ToastUtils.showToast("未注册到微信");
            return false;
        }
        return wxApi.handleIntent(intent, handler);
    }

    public static void shareText(String text, boolean wechatFriend) {
        if (wxApi == null) {
            ToastUtils.showToast("未注册到微信");
            return;
        }
        // 分享内容实体
        WXMediaMessage wxMediaMessage = new WXMediaMessage();
        wxMediaMessage.description = text;
        wxMediaMessage.mediaObject = new WXTextObject(text);

        // 分享请求实体
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = UUID.randomUUID().toString();
        req.scene = wechatFriend ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
        req.message = wxMediaMessage;

        // 发起请求
        boolean sendReq = wxApi.sendReq(req);
        Logger.d("发送文本分享请求" + (sendReq ? "成功" : "失败"));
    }

    public static void weChatLogin() {
        if (wxApi == null) {
            ToastUtils.showToast("未注册到微信");
            return;
        }
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";  // 授权域
        req.state = "beiko_wechat";
        wxApi.sendReq(req);
    }

}
