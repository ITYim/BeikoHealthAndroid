package com.yim.http;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 * @author zym
 * @since 2017-08-09 12:22
 */
public class HttpRequester<IService> {
    private static final long CONNECT_TIME = 20000L;
    private static final long WRITE_TIME = 20000L;
    private static final long READ_TIME = 20000L;

    private static HttpRequester INSTANCE;
    private final IService service;
    private final OkHttpClient mHttpClient;

    /**
     * 创建Http请求对象
     * @param tClass 生成url接口的class对象
     * @throws IllegalArgumentException 如果传入的class不是接口，会抛出非法参数异常
     */
    @SuppressWarnings("unchecked")
    public static HttpRequester createInstance(Class tClass, OkHttpClient.Builder builder) {
        if (INSTANCE == null) {
            synchronized (HttpRequester.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpRequester(tClass, builder);
                }
            }
        }
        return INSTANCE;
    }

    private HttpRequester(Class<IService> clazz, OkHttpClient.Builder builder) {
        OkHttpClient.Builder httpClientBuilder;
        if (builder == null) {
            httpClientBuilder = getDefaultHttpBuilder();
        } else {
            httpClientBuilder = builder;
        }
        mHttpClient = httpClientBuilder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(mHttpClient)
                .build();
        service = retrofit.create(clazz);
    }

    public static OkHttpClient.Builder getDefaultHttpBuilder() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(CONNECT_TIME, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIME, TimeUnit.MILLISECONDS)
                .readTimeout(READ_TIME, TimeUnit.MILLISECONDS);
    }

    public OkHttpClient getHttpClient() {
        return mHttpClient;
    }

    @SuppressWarnings("unchecked")
    public <T> Subscriber<T> request(Observable observable, HttpCallback<T> callback) {
        Subscriber<T> subscriber = new HttpResponse(callback);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        return subscriber;
    }

    /**
     * 请求过程，如果发生错误，会有toast提示
     */
    @SuppressWarnings("unchecked")
    public <T> Subscriber<T> request(Context context, Observable observable, HttpCallback<T> callback) {
        Subscriber<T> subscriber = new HttpResponse(context, callback);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        return subscriber;
    }

    public IService getService() {
        return service;
    }
}
