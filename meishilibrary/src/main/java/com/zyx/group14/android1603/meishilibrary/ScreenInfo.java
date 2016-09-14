package com.zyx.group14.android1603.meishilibrary;

import android.content.Context;

/**
 * Created by yixin on 2016/9/8.
 */
public class ScreenInfo {
    public static float getDensity(Context context){
        return context.getResources().getDisplayMetrics().density;
    }
    public static int dpToPx(Context context, int dp){
        return (int)(dp*getDensity(context)+0.5f);
    }
}
