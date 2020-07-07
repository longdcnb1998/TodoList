package com.example.todolist.Notification;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import com.example.todolist.R;

public class AppUtils {

    private static String TAG = AppUtils.class.getSimpleName();

    public static boolean isAppLanguageVN(Context context) {
        return context.getString(R.string.appLanguage).equals("VN");
    }

    private static String isAppRungning = "isAppRungning";
    public static void vSetAppRunning(Context c, boolean isRunning) {
        SharedPreferences sp = c.getSharedPreferences(TAG, 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(isAppRungning, isRunning);
        editor.commit();
    }

    public static boolean isAppRunning(Context c) {
        SharedPreferences sp = c.getSharedPreferences(TAG, 0);
        return sp.getBoolean(isAppRungning, false);
    }

    public static String getAppName(Context mContext) {
        String appName = "...";
        try {
            int stringId = mContext.getApplicationInfo().labelRes;
            appName = mContext.getString(stringId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appName;
    }

    public static int getAppIcon(Context mContext) {
        int appIcon = android.R.drawable.ic_notification_overlay;
        try {
            appIcon = mContext.getApplicationInfo().icon;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appIcon;
    }

    public static String getAppPackage(Context mContext) {
        return mContext.getPackageName();
    }

    public static boolean isAppInstalled(Context mContext, String mAppPackage) {
        PackageManager pm = mContext.getPackageManager();
        try {
            pm.getPackageInfo(mAppPackage, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static int getAppVersionCode(Activity mContext) {
        int versionCode = 1;
        try {
            versionCode = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    public static String getAppVersionName(Activity mContext) {
        String versionName = "1";
        try {
            versionName = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
