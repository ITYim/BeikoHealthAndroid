package com.yim.http;

/**
 *
 * @author zym
 * @since 2017-08-09 12:20
 */
public interface HttpCallback<T> {

    void onStart();

    void onSuccess(T data);

    void onComplete();

    void onError(Throwable e);

    void originalObj(RestfulData<T> obj);
}
