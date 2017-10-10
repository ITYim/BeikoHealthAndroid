package com.alensic.beikohealth.base;

import android.app.Activity;

import java.util.LinkedList;

/**
 * 管理Activity的操作
 * Created by zym on 2017/5/16.
 */
public class AppManager {

    private LinkedList<Activity> acts = new LinkedList<>();

    public static AppManager getInstance() {
        return AppManagerHolder.INSTANCE;
    }

    private AppManager() {}

    private static class AppManagerHolder {
        private static final AppManager INSTANCE = new AppManager();
    }

    /**
     * 新建Activity
     */
    public void push(Activity act) {
        acts.add(act);
    }

    /**
     * 退出Activity
     */
    public void pop(Activity act) {
        acts.remove(act);
    }

    /**
     * 退出全部Activity
     */
    public void clearAll() {
        for (Activity act : acts) {
            act.finish();
        }
        acts.clear();
    }

    /**
     * 返回栈顶的Activity
     */
    public Activity getTopActivity() {
        if (acts == null || acts.isEmpty())
            return null;
        return acts.peek();
    }

    /**
     * 返回Activity栈
     */
    public LinkedList<Activity> getActivities() {
        return acts;
    }

    /**
     * 是否存在某Activity
     */
    public boolean existsActivity(Activity activity) {
        for (Activity act: acts) {
            if (act == activity) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否存在某Activity
     */
    public boolean existsActivity(String activityName) {
        if (activityName == null || activityName.isEmpty()) {
            throw new IllegalArgumentException("AppManager#existsActivity Activity名称参数不能为空");
        }
        for (Activity act: acts) {
            if (activityName.equals(act.getClass().getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否存在任何Activity
     */
    public boolean isActivityStackEmpty() {
        return acts.isEmpty();
    }
}
