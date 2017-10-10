package com.alensic.beikohealth.http;

import android.app.Fragment;
import android.content.Context;

import com.alensic.beikohealth.base.GlobalParams;
import com.yim.base.BaseActivity;
import com.yim.base.BaseFragment;
import com.yim.base.BaseFragmentActivity;

import java.lang.ref.SoftReference;
import java.util.Collection;

import rx.Subscriber;

/**
 * @author zym
 * @since 2017-08-25 10:30
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class RequestCallback<T> {
    private SoftReference<Context> mContextRef;

    public RequestCallback(Context context) {
        mContextRef = new SoftReference<>(context);
    }

    public RequestCallback() {
    }

    public void onStart() {
        hideErrorView();
        hideEmptyView();
        showLoadingView();
    }

    public void onSuccess(T data) {
        if (data == null || (data instanceof Collection && ((Collection) data).isEmpty())) {
            showEmptyView();
        }
    }

    public void onComplete() {
        hideLoadingView();
    }

    public void onError(Throwable e) {
        hideLoadingView();
        showErrorView();
    }

    public void originalObj(com.yim.http.RestfulData<T> obj) {
    }

    public void addSubscriber(Subscriber<T> subscriber) {
        if (mContextRef != null && mContextRef.get() != null) {
            if (mContextRef.get() instanceof BaseActivity) {
                ((BaseActivity) mContextRef.get()).addSubscriber(subscriber);
            } else if (mContextRef.get() instanceof BaseFragmentActivity) {
                ((BaseFragmentActivity) mContextRef.get()).addSubscriber(subscriber);
            }
        } else {
            GlobalParams.getApplication().addSubscriber(subscriber);
        }
    }

    private void showLoadingView() {
        if (mContextRef.get() == null) {
            return;
        }
        if (mContextRef.get() instanceof BaseActivity) {
            ((BaseActivity) mContextRef.get()).showLoadingView();
        } else if (mContextRef.get() instanceof BaseFragmentActivity) {
            Fragment fragment = ((BaseFragmentActivity) mContextRef.get()).getFragment();
            if (fragment != null && fragment instanceof BaseFragment) {
                ((BaseFragment) fragment).showLoadingView();
            }
        }
    }

    private void hideLoadingView() {
        if (mContextRef.get() == null) {
            return;
        }
        if (mContextRef.get() instanceof BaseActivity) {
            ((BaseActivity) mContextRef.get()).hideLoadingView();
        } else if (mContextRef.get() instanceof BaseFragmentActivity) {
            Fragment fragment = ((BaseFragmentActivity) mContextRef.get()).getFragment();
            if (fragment != null && fragment instanceof BaseFragment) {
                ((BaseFragment) fragment).hideLoadingView();
            }
        }
    }

    private void showEmptyView() {
        if (mContextRef.get() == null) {
            return;
        }
        if (mContextRef.get() instanceof BaseActivity) {
            ((BaseActivity) mContextRef.get()).showEmptyView();
        } else if (mContextRef.get() instanceof BaseFragmentActivity) {
            Fragment fragment = ((BaseFragmentActivity) mContextRef.get()).getFragment();
            if (fragment != null && fragment instanceof BaseFragment) {
                ((BaseFragment) fragment).showEmptyView();
            }
        }
    }

    private void hideEmptyView() {
        if (mContextRef.get() == null) {
            return;
        }
        if (mContextRef.get() instanceof BaseActivity) {
            ((BaseActivity) mContextRef.get()).hideEmptyView();
        } else if (mContextRef.get() instanceof BaseFragmentActivity) {
            Fragment fragment = ((BaseFragmentActivity) mContextRef.get()).getFragment();
            if (fragment != null && fragment instanceof BaseFragment) {
                ((BaseFragment) fragment).hideEmptyView();
            }
        }
    }

    private void showErrorView() {
        if (mContextRef.get() == null) {
            return;
        }
        if (mContextRef.get() instanceof BaseActivity) {
            ((BaseActivity) mContextRef.get()).showErrorView();
        } else if (mContextRef.get() instanceof BaseFragmentActivity) {
            Fragment fragment = ((BaseFragmentActivity) mContextRef.get()).getFragment();
            if (fragment != null && fragment instanceof BaseFragment) {
                ((BaseFragment) fragment).showErrorView();
            }
        }
    }

    private void hideErrorView() {
        if (mContextRef.get() == null) {
            return;
        }
        if (mContextRef.get() instanceof BaseActivity) {
            ((BaseActivity) mContextRef.get()).hideErrorView();
        } else if (mContextRef.get() instanceof BaseFragmentActivity) {
            Fragment fragment = ((BaseFragmentActivity) mContextRef.get()).getFragment();
            if (fragment != null && fragment instanceof BaseFragment) {
                ((BaseFragment) fragment).hideErrorView();
            }
        }
    }

    public Context getContext() {
        return mContextRef == null ? null : mContextRef.get();
    }

    public void setNoNeedStatusPage() {
        mContextRef = null;
    }
}
