package com.yim.base.utils;

import android.support.annotation.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 * Created by zym on 2016/10/28.
 */
public class PatternUtils {

    public static final String REGEX_MATCH_JPUSH_ALIAS = "^[\u4E00-\u9FA50-9A-Za-z@!#$&*+=._|￥]{0,40}$";
    /**
     * 匹配极光别名的正确性
     */
    public static boolean matchJPushAlias(@NonNull String alias) {
        Pattern pattern = Pattern.compile(REGEX_MATCH_JPUSH_ALIAS);
        Matcher matcher = pattern.matcher(alias);
        return matcher.matches();
    }

    /**
     * 正则表达式匹配字符串合法性
     */
    public static boolean match(String regex, @NonNull String alias) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(alias);
        return matcher.matches();
    }

}
