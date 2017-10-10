package com.yim.base;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;

import com.yim.base.utils.Logger;
import com.yim.base.utils.SwipeLayoutHelper;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Activity基类
 * 用于使用Fragment展示的Activity，Activity不做业务和界面逻辑操作
 * @author zym
 * @since 2017-07-28 15:33
 */
public class BaseFragmentActivity extends FragmentActivity {

    private final String TAG = this.getClass().getSimpleName();
    public static final String KEY_FULL_FRAGMENT_NAME = "full_fragment_name";
    public static final String KEY_SIMPLE_FRAGMENT_NAME = "simple_fragment_name";
    public static final String KEY_FRAGMENT_ARGUMENT = "fragment_argument";

    private CompositeSubscription mCompositeSubscription;
    private String fragmentPackage;
    private Fragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setSwipeBackHelper();
        setContentView(R.layout.layout_base_fragment);
        Bundle extras = getIntent().getExtras();
        if (extras == null || extras.isEmpty()) {
            Log.d(TAG, "Extras is null");
            finish();
            return;
        }
        fragmentPackage = getPackageName() + ".fragment";
        String fullFragmentName = extras.getString(KEY_FULL_FRAGMENT_NAME, "");
        String simpleFragmentName = extras.getString(KEY_SIMPLE_FRAGMENT_NAME, "");
        Bundle fragmentArgument = extras.getBundle(KEY_FRAGMENT_ARGUMENT);
        mFragment = buildFragment(fullFragmentName, simpleFragmentName);
        if (mFragment == null) {
            finish();
            return;
        }
        mFragment.setArguments(fragmentArgument);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_root, mFragment, mFragment.getClass().getSimpleName());
        fragmentTransaction.commit();
    }

    /**
     * 根据名称创建Fragment
     * @param fullFragmentName      Fragment全类名
     * @param simpleFragmentName    Fragment名，会补充上默认包名
     */
    @SuppressWarnings("unchecked")
    private Fragment buildFragment(String fullFragmentName, String simpleFragmentName) {
        if (TextUtils.isEmpty(fullFragmentName) && TextUtils.isEmpty(simpleFragmentName)) {
            Log.e(TAG, "Fragment name cannot be null");
            return null;
        }
        String fragmentName = buildFragmentName(fullFragmentName, simpleFragmentName);
        Log.d(TAG, "Fragment that want to create is " + fragmentName);
        try {
            Class<Fragment> fragmentClass = (Class<Fragment>) Class.forName(fragmentName);
            return fragmentClass.newInstance();
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "Cannot find " + fragmentName);
            e.printStackTrace();
            return null;
        } catch (ClassCastException e) {
            Log.e(TAG, fragmentName + " is not a Fragment");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            Log.e(TAG, "Create fragment fail");
            e.printStackTrace();
            return null;
        }
    }

    private String buildFragmentName(String fullFragmentName, String simpleFragmentName) {
        String fragmentName;
        if (!TextUtils.isEmpty(fullFragmentName)) {
            fragmentName = fullFragmentName;
        } else {
            fragmentName = fragmentPackage + "." + simpleFragmentName;
        }
        return fragmentName;
    }

    /**
     * 设置SwipeBack效果
     */
    private void setSwipeBackHelper() {
        if (isSwipe()) {
            SwipeLayoutHelper.init(this);
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (isSwipe()) {
            SwipeLayoutHelper.postCreate(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (mCompositeSubscription!=null && mCompositeSubscription.hasSubscriptions()) {
            Logger.d(TAG, "unSubscribe");
            mCompositeSubscription.unsubscribe();
        }
        if (isSwipe()) {
            SwipeLayoutHelper.destroy(this);
        }
        super.onDestroy();
    }

    public void addSubscriber(Subscription subscription) {
        Logger.d(TAG, "addSubscriber");
        if (mCompositeSubscription == null || mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    /**
     * 是否开启SwipeBack模式
     * 允许子类重写
     */
    protected boolean isSwipe() {
        return true;
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public void disableSwipe() {
        if (isSwipe()) {
            SwipeLayoutHelper.disable(this);
        }
    }

    public void setSwipeEdge(float edge) {
        if (isSwipe()) {
            SwipeLayoutHelper.setSwipeEdge(this, edge);
        }
    }
}
