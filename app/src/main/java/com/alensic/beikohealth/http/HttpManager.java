package com.alensic.beikohealth.http;

import android.content.Context;

import com.alensic.beikohealth.base.GlobalParams;
import com.yim.http.HttpCallback;
import com.yim.http.HttpRequester;
import com.yim.http.RestfulData;

import okhttp3.OkHttpClient;
import rx.Observable;
import rx.Subscriber;

/**
 * @author zym
 * @since 2017-08-25 10:27
 */
public class HttpManager {
    private HttpRequester<HttpService> mRequester;

    public static HttpManager getManager() {
        return HttpHolder.HOLDER;
    }

    private HttpManager() {
        if (mRequester == null) {
            OkHttpClient.Builder httpBuilder = HttpRequester.getDefaultHttpBuilder();
            //noinspection unchecked
            mRequester = HttpRequester.createInstance(HttpService.class, httpBuilder);
        }
    }

    private static class HttpHolder {
        private static final HttpManager HOLDER = new HttpManager();
    }

    /**
     * 后台的Http请求，请求结果前台不展示
     */
    public <T> void doBackgroundRequest(Observable observable, final SimpleHandler<T> handler) {
        HttpCallback<T> httpCallback = new HttpCallback<T>() {
            @Override
            public void onStart() {
                if (handler != null)
                    handler.onStart();
            }

            @Override
            public void onSuccess(T data) {
                if (handler != null)
                    handler.onSuccess(data);
            }

            @Override
            public void onComplete() {
                if (handler != null)
                    handler.onComplete();
            }

            @Override
            public void onError(Throwable e) {
                if (handler != null)
                    handler.onError(e);
            }

            @Override
            public void originalObj(RestfulData<T> obj) {
                if (handler != null)
                    handler.originalObj(obj);
            }
        };
        Subscriber<T> subscriber = mRequester.request(observable, httpCallback);
        if (handler != null) {
            handler.setNoNeedStatusPage();
            handler.addSubscriber(subscriber);
        } else {
            GlobalParams.getApplication().addSubscriber(subscriber);
        }
    }

    /**
     * 后台请求，结合页面展示各种状态
     * @param handler 必须使用new SimpleHandler(Context,
     */
    public <T> void doRequest(Observable observable, final SimpleHandler<T> handler) {
        HttpCallback<T> httpCallback = new HttpCallback<T>() {
            @Override
            public void onStart() {
                if (handler != null)
                    handler.onStart();
            }

            @Override
            public void onSuccess(T data) {
                if (handler != null)
                    handler.onSuccess(data);
            }

            @Override
            public void onComplete() {
                if (handler != null)
                    handler.onComplete();
            }

            @Override
            public void onError(Throwable e) {
                if (handler != null)
                    handler.onError(e);
            }

            @Override
            public void originalObj(RestfulData<T> obj) {
                if (handler != null)
                    handler.originalObj(obj);
            }
        };
        Subscriber<T> subscriber = mRequester.request(observable, httpCallback);
        if (handler != null) {
            handler.addSubscriber(subscriber);
        } else {
            GlobalParams.getApplication().addSubscriber(subscriber);
        }
    }

    /**
     * 后台请求，结合页面展示各种状态
     * @param handler 必须使用 new SimpleHandler();
     */
    public <T> void doToastRequest(Context context, Observable observable, final SimpleHandler<T> handler) {
        HttpCallback<T> httpCallback = new HttpCallback<T>() {
            @Override
            public void onStart() {
                if (handler != null)
                    handler.onStart();
            }

            @Override
            public void onSuccess(T data) {
                if (handler != null)
                    handler.onSuccess(data);
            }

            @Override
            public void onComplete() {
                if (handler != null)
                    handler.onComplete();
            }

            @Override
            public void onError(Throwable e) {
                if (handler != null)
                    handler.onError(e);
            }

            @Override
            public void originalObj(RestfulData<T> obj) {
                if (handler != null)
                    handler.originalObj(obj);
            }
        };
        Subscriber<T> subscriber = mRequester.request(context, observable, httpCallback);
        if (handler != null) {
            handler.setNoNeedStatusPage();
            handler.addSubscriber(subscriber);
        } else {
            GlobalParams.getApplication().addSubscriber(subscriber);
        }
    }

    public HttpService getService() {
        return mRequester.getService();
    }
}
