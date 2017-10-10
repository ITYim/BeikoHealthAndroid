package com.alensic.beikohealth.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alensic.beikohealth.helper.WeChatManager;
import com.alensic.beikohealth.utils.ToastUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.yim.base.utils.Logger;


/**
 * @author zym
 * @since 2017-08-08 17:37
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = new View(this);
        view.setBackgroundColor(Color.TRANSPARENT);
        setContentView(view);
        Logger.d("WXEntryActivity-onCreate");
        try{
            if (!WeChatManager.handleIntent(getIntent(), this)) {
                Logger.d("handle intent false");
                finish();
            }
        } catch(Exception e) {
            e.printStackTrace();
            Logger.d("handle intent exception");
            finish();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        WeChatManager.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Logger.d(baseReq.openId + "、" + baseReq.transaction);
    }

    @Override
    public void onResp(BaseResp baseResp) {
        ToastUtils.showToast("响应：" + baseResp.errCode + " " + baseResp.errStr);
        // 发起微信登录授权请求后，拉起微信登录界面，用户操作后的回调在这里执行
        if (baseResp.errCode == 0) {
            // 用户同意授权
            String code = baseResp.transaction;
        } else {
            ToastUtils.showToast(baseResp.errStr);
            finish();
        }
        Logger.d(baseResp.openId + "、" + baseResp.transaction + "、" + baseResp.errCode + "、" + baseResp.errStr);
    }
}
