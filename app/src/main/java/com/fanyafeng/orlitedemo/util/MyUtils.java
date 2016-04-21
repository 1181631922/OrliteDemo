package com.fanyafeng.orlitedemo.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MyUtils {
    private static String channel = null;

    public static String getChannel(Context context) {
        if (channel == null) {
            ApplicationInfo ai = null;
            channel = "shape_maintext_box";
            try {
                ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);

                if (ai != null) {
                    channel = String.valueOf(ai.metaData.get("UMENG_CHANNEL"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return channel;
    }

    public static int getStatusBarHeight(Context context) {
        try {
            @SuppressWarnings("rawtypes")
            Class clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            Field field = clazz.getField("status_bar_height");

            int id = Integer.parseInt(field.get(object).toString());
            return  context.getResources().getDimensionPixelSize(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    /**
     * Get the screen height.
     *
     * @param context
     * @return the screen height
     */
    public static int getScreenHeight(Context context) {
        if (context != null) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);

            return displayMetrics.heightPixels;
        } else {
            return 1920;
        }
    }

    /**
     * Get the screen width.
     *
     * @param context
     * @return the screen width
     */
    public static int getScreenWidth(Context context) {
        if (context != null) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);

            return displayMetrics.widthPixels;
        } else {
            return 1080;
        }
    }

    public static float getDensity(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.density;
    }

    public static String getMetaValue(Context context, String metaKey) {

        if (context == null || metaKey == null) {
            return null;
        }

        try {
            ApplicationInfo aiApplicationInfo = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);

            if (null != aiApplicationInfo) {
                if (null != aiApplicationInfo.metaData) {
                    return aiApplicationInfo.metaData.getString(metaKey);
                }
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 格式化日期
     * yyyy-MM-dd 转为 yyyy年MM月dd日
     * @param dateStr
     * @return
     */
    public static String formatSystemDateCN(String dateStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String standarTime;

            Date date = simpleDateFormat.parse(dateStr);
            simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
            standarTime = simpleDateFormat.format(date);

            return standarTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateStr;
    }

}
