package com.example.todolist.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class ScreenUtils {
    public static boolean isFullScreen(Activity activity) {
        return (activity.getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) != 0;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displaymetrics = context.getResources().getDisplayMetrics();
        return displaymetrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics displaymetrics = context.getResources().getDisplayMetrics();
        return displaymetrics.heightPixels;
    }

    public static int getRealScreenWidth(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)    {
            Point size = new Point();
            activity.getWindowManager().getDefaultDisplay().getRealSize(size);
            return size.x;
        } else {
            DisplayMetrics displaymetrics = activity.getResources().getDisplayMetrics();
            return displaymetrics.widthPixels;
        }
    }

    public static int getRealScreenHeight(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)    {
            Point size = new Point();
            activity.getWindowManager().getDefaultDisplay().getRealSize(size);
            return size.y;
        } else {
            DisplayMetrics displaymetrics = activity.getResources().getDisplayMetrics();
            return displaymetrics.heightPixels;
        }
    }
}
