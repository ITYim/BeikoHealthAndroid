package com.alensic.beikohealth.mvp;

import android.content.Context;

import com.alensic.beikohealth.http.HttpManager;
import com.alensic.beikohealth.http.SimpleHandler;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author zym
 * @since 2017-08-07 14:21
 */
@SuppressWarnings({"unchecked", "WeakerAccess", "unused"})
public abstract class BasePresenter<V extends BaseView> {
    protected V mvpView;
    protected Context context;

    private CompositeSubscription mCompositeSubscription;

    public BasePresenter(Context context, BaseView mvpView) {
        this.context = context;
        this.mvpView = (V) mvpView;
    }

    /**
     * 销毁对应的页面
     */
    public void destroy() {
        unsubscribe();
        this.mCompositeSubscription = null;
        this.mvpView = null;
    }

    /**
     * 取消订阅，即取消上一次网络请求
     */
    public void unsubscribe() {
        if (mCompositeSubscription != null && !mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    /**
     * 添加回调，统一管理
     *
     * @param subscription rx回调
     */
    public void addSubscription(Subscription subscription) {
        if (subscription == null || subscription.isUnsubscribed())
            return;
        if (mCompositeSubscription == null || mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    /**
     * 后台的Http请求，请求结果前台不展示
     */
    public <T> void doBackgroundRequest(Observable observable, SimpleHandler<T> handler) {
        HttpManager httpManager = HttpManager.getManager();
        httpManager.doBackgroundRequest(observable, handler);
    }

    /**
     * 后台请求，结合页面展示各种状态
     * @param handler 必须使用new SimpleHandler(Context)
     */
    public <T> void doRequest(Observable observable, SimpleHandler<T> handler) {
        HttpManager httpManager = HttpManager.getManager();
        httpManager.doRequest(observable, handler);
    }

    /**
     * 后台请求，结合页面展示各种状态
     * @param handler 必须使用 new SimpleHandler();
     */
    public <T> void doToastRequest(Context context, Observable observable, SimpleHandler<T> handler) {
        HttpManager httpManager = HttpManager.getManager();
        httpManager.doToastRequest(context, observable, handler);
    }

}
