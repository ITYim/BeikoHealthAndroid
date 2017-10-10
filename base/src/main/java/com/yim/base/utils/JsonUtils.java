package com.yim.base.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * JSON字符串操作工具类
 * @author zym
 */
public class JsonUtils {

    private static Gson gson = null;
    static {
        if (gson == null) {
            gson = new Gson();// GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        }
    }

    public static Map<?, ?> jsonToMap(String jsonStr) {
        Map<?, ?> objMap = null;
        if (gson != null) {
            Type type = new com.google.gson.reflect.TypeToken<Map<?, ?>>() {
            }.getType();
            objMap = gson.fromJson(jsonStr, type);
        }
        return objMap;
    }

    public static Object getJsonValue(String jsonStr, String key) {
        Object rulsObj = null;
        Map<?, ?> rulsMap = jsonToMap(jsonStr);
        if (rulsMap != null && rulsMap.size() > 0) {
            rulsObj = rulsMap.get(key);
        }
        return rulsObj;
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    public static <T> T fromJson(String json, Type type) {
        return gson.fromJson(json, type);
    }

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

}
