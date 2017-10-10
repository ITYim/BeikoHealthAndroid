package com.yim.base.utils;

import android.util.Log;

import com.yim.base.BuildConfig;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 日志打印工具
 * @author zym
 * @since 2017-08-09 14:16
 */
@SuppressWarnings({"unchecked", "unused", "WeakerAccess"})
public class Logger {

    private static final boolean DEBUG = BuildConfig.DEBUG;
    private static final String TAG = "zym";

    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void i(String tag, String msg) {
        if(DEBUG)
            Log.i(tag, msg);
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void d(String tag, String msg) {
        if(DEBUG)
            Log.d(tag, msg);
    }

    public static void v(String msg) {
        v(TAG, msg);
    }

    public static void v(String tag, String msg) {
        if(DEBUG)
            Log.v(tag, msg);
    }

    public static void warn(String msg) {
        warn(TAG, msg);
    }

    public static void warn(String tag, String msg) {
        if(DEBUG)
            Log.w(tag, msg);
    }

    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        if(DEBUG)
            Log.e(tag, msg);
    }

    public static void print(Map<Object, Object> map) {
        print(TAG, map);
    }

    public static void print(String tag, Map<Object, Object> map) {
        if (!DEBUG || map == null)
            return;
        if (map.isEmpty()) {
            d("[ ]");
            return;
        }
        StringBuilder sb = new StringBuilder("[");
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            sb.append(entry.getKey()).append(" = ").append(entry.getValue()).append(", ");
        }
        int start = sb.lastIndexOf(", ");
        sb.delete(start, start + 2);
        d(tag, sb.append("]").toString());
    }

    public static void printObj(Object obj) {
        printObj(TAG, obj);
    }

    public static void printObj(String tag, Object obj) {
        if (!DEBUG || obj == null)
            return;
        if (obj instanceof String ||
                obj instanceof Integer ||
                obj instanceof Boolean ||
                obj instanceof Short ||
                obj instanceof Long ||
                obj instanceof Float ||
                obj instanceof Double) {
            d(tag, obj.toString());
        }
        else if (obj instanceof Map) {
            print(tag, (Map<Object, Object>) obj);
        }
        else if (obj instanceof Class) {
            d(tag, ((Class) obj).getName());
        }
        else {
            String baseString = obj.toString();
            if (baseString.contains("@")) { // Object的toString方法
                try{
                    Field[] fields = obj.getClass().getDeclaredFields();
                    StringBuilder sb = new StringBuilder();
                    sb.append(obj.getClass().getSimpleName()).append("{");
                    for (Field field : fields) {
                        boolean accessible = field.isAccessible();
                        if (!accessible) {
                            field.setAccessible(true);
                        }
                        sb.append(field.getName()).append("=").append(field.get(obj)).append(", ");
                        if (!accessible) {
                            field.setAccessible(false);
                        }
                    }
                    if (sb.indexOf("=") != -1) {
                        int start = sb.lastIndexOf(",");
                        sb.delete(start, start + 2);
                    }
                    d(tag, sb.append("}").toString());
                } catch(Exception e) {
                    e.printStackTrace();
                }
            } else {    // 对象重写了toString方法，按照该方法方法返回值打印
                d(tag, baseString);
            }
        }
    }
}
