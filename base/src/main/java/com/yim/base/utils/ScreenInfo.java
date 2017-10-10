package com.yim.base.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class ScreenInfo {
    public static int width = 0;
    public static int height = 0;
    public static float density = 0;

    private static boolean init = false;
    public static void init(Context context) {
        if(!init) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            DisplayMetrics outMetrics = new DisplayMetrics();
            display.getMetrics(outMetrics);
            width = outMetrics.widthPixels;
            height = outMetrics.heightPixels;
            density = outMetrics.density;
            init = true;
        }
    }
}
