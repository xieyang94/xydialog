/**
 * Copyright(c)2015
 * All right reserved.
 */
package com.xiey94.xydialog.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * @author rulang1
 * @Date 2014�?12�?2�?
 * @包名 cn.wzh.common.Common
 * @说明 �?些长用变量的说明
 */
public class XyCommon {
    /**
     * @param context
     * @return 当前屏幕的宽�?
     */
    public static int getWidth(Context context) {
        DisplayMetrics dm2 = context.getResources().getDisplayMetrics();
        return dm2.widthPixels;
    }

    /**
     * @param context
     * @return 当前屏幕的宽高度
     */
    public static int getHeight(Context context) {
        DisplayMetrics dm2 = context.getResources().getDisplayMetrics();
        return dm2.heightPixels;
    }

    /**
     * @param context
     * @param dipValue
     * @return 将dp 装换成PX
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * @param context
     * @param pxValue
     * @return 将px �? dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * @param pxValue
     * @return 将px值转换为sp值
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * @param spValue
     * @return 将px值转换为sp值
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}
