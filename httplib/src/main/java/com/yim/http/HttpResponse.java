package com.yim.http;

import android.content.Context;
import android.widget.Toast;

import java.lang.ref.SoftReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Http请求过程回调
 *
 * @author zym
 * @since 2017-07-31 10:29
 */
public class HttpResponse<T> extends Subscriber<RestfulData<T>> {
    private HttpCallback<T> mCallback;
    private boolean backgroundRequest;  // 是否是后台请求，如果是：请求结果不在前台显示（错误的时候前台不感知），否则相反
    private SoftReference<Context> contextRef;

    public HttpResponse(HttpCallback<T> callback) {
        this.mCallback = callback;
    }

    public HttpResponse(Context context, HttpCallback<T> callback) {
        contextRef = new SoftReference<>(context);
        this.mCallback = callback;
    }

    @Override
    public void onStart() {
        if (mCallback != null)
            mCallback.onStart();
    }

    @Override
    public void onCompleted() {
        if (mCallback != null)
            mCallback.onComplete();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (contextRef != null && contextRef.get() != null) {
            if (e instanceof ConnectException || e instanceof SocketTimeoutException) {
                Toast.makeText(contextRef.get(), "网络连接失败", Toast.LENGTH_SHORT).show();
            } else if (e instanceof HttpException) {
                int code = ((HttpException) e).code();
                if (code == 500) {
                    // 服务器异常
                    Toast.makeText(contextRef.get(), "服务器异常", Toast.LENGTH_SHORT).show();
                } else if (code == 404) {
                    // 404 not found, url错误
                    Toast.makeText(contextRef.get(), "404 Not Found!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(contextRef.get(), "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        }
        if (mCallback != null) {
            mCallback.onError(e);
        }
    }

    @Override
    public void onNext(RestfulData<T> restfulData) {
        if (!"OK".equals(restfulData.getErrcode()) && contextRef != null && contextRef.get() != null) {
            Toast.makeText(contextRef.get(), restfulData.getMsg(), Toast.LENGTH_SHORT).show();
            return;
        }
        if (mCallback != null) {
            mCallback.originalObj(restfulData);
            mCallback.onSuccess(restfulData.getData());
        }
    }
}
