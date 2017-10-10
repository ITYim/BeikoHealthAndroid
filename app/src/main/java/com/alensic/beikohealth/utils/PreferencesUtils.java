package com.alensic.beikohealth.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.alensic.beikohealth.base.GlobalParams;

/**
 * 一个SharePreference类，存储数据
 */
public class PreferencesUtils {

    private static final String BEIKO_PREFERENCES = "BEIKO_PREFERENCES";
    private static PreferencesUtils mPref = null;
    private SharedPreferences mSharePreference = null;
    private Editor mEditor = null;

    public static final String PUSH_REGISTRATION_ID = "PUSH_REGISTRATION_ID";
    public static final String ALIAS_SAVED = "JPUSH_ALIAS_SAVE_SUCCESS";

    public void saveStringData(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public String getStringData(String key) {
        return mSharePreference.getString(key, "");
    }

    public void saveIntData(String key, int value) {
        mEditor.putInt(key, value);
        mEditor.commit();
    }

    public int getIntData(String key, int defaultValue) {
        return mSharePreference.getInt(key, defaultValue);
    }

    public void saveLongData(String key, long value) {
        mEditor.putLong(key, value);
        mEditor.commit();
    }

    public long getLongData(String key, long defaultValue) {
        return mSharePreference.getLong(key, defaultValue);
    }

    public void saveFloatData(String key, float value) {
        mEditor.putFloat(key, value);
        mEditor.commit();
    }

    public float getFloatData(String key) {
        return mSharePreference.getFloat(key, 0);
    }

    public void saveBooleanData(String key, boolean value) {
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    public boolean getBooleanData(String key) {
        return mSharePreference.getBoolean(key, false);
    }

    public boolean getBooleanData(String key, boolean defValue) {
        return mSharePreference.getBoolean(key, defValue);
    }


    public static PreferencesUtils getInstance() {
        if (mPref == null) {
            mPref = new PreferencesUtils();
        }
        return mPref;
    }

    private PreferencesUtils() {
        mSharePreference = GlobalParams.getContext().getSharedPreferences(BEIKO_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharePreference.edit();
    }

    /**
     * 清空 SharedPreferences
     */
    public void clear() {
        mEditor.clear();
        mEditor.commit();
    }

    /**
     * 移除某个字段
     */
    public void remove(String key) {
        mEditor.remove(key);
        mEditor.commit();
    }
}
