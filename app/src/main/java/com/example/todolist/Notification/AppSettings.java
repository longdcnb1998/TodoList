package com.example.todolist.Notification;

import android.content.Context;

import com.example.todolist.DataController;

public class AppSettings {

    private static final String appColor = "appColor";
    private static final String appDefaultColor = "appDefaultColor";
    private static final String appTheme = "appTheme";
    private static final String appPassword = "appPassword";
    private static final String fullScreen = "fullScreen";
    private static final String confirmExit = "confirmExit";

    public static final int appThemeLight = 1;
    public static final int appThemeColor = 2;

    // black: #000000
    // dark: #1a2334
    // blue: #00a2e4
    // red: #ff4b26
    // orange: #f6b402

    // BestWishes: #f92557, -449193
    // BeenLove: -65441
    // ScreenRecorder: #ff5252, -44462
    // EventCountdown: -10766113
    // Forum: #cdbf00, -3293440
    // Mirror: #d59f63, -2777245
    // WifiFree: #0f93ef, -15756305

    // AudioRecorder: #f45035, -765899
    // AutumnLiveWallpaer: #de7738, -2197704
    // BatteryInfo: #ef6969, -1087127
    // ChineseChess: #dcba8f, -2311537
    // Compass: #050c12, -16446446
    // DownloadManager: #6ea50f, -9526001
    // Camera365, GIFCamera: #283238, -14142920
    // GIFCreator: #2d527c, -13806980
    // NightLiveWallpaper: #1a2334, -15064268
    // PhotoEffects: #050c12, -6917150
    // Weather: #4c8fcf, -11759665

    public static void setAppColor(Context context, int color) {
        DataController.saveData(context, appColor, color);
    }
    public static int getAppColor(Context context) {
        return DataController.getData(context, appColor, 0);
    }

    public static void setAppDefaultColor(Context context, int color) {
        DataController.saveData(context, appDefaultColor, color);
    }
    public static int getAppDefaultColor(Context context) {
        return DataController.getData(context, appDefaultColor, 0);
    }

    public static void setAppTheme(Context context, int theme) {
        DataController.saveData(context, appTheme, theme);
    }
    public static int getAppTheme(Context context) {
        return DataController.getData(context, appTheme, 0);
    }
    public static boolean isLightTheme(Context context) {
        return getAppTheme(context) == appThemeLight;
    }

    public static void setAppPassword(Context context, String password) {
        DataController.saveData(context, appPassword, password);
    }
    public static String getAppPassword(Context context) {
        return DataController.getData(context, appPassword, "");
    }

    public static void setFullScreen(Context context, boolean isEnable) {
        DataController.saveData(context, fullScreen, isEnable);
    }
    public static boolean isFullScreen(Context context) {
        return DataController.getData(context, fullScreen, false);
    }

    public static void setConfirmExit(Context context, boolean enable) {
        DataController.saveData(context, confirmExit, enable);
    }

    public static boolean isConfirmExit(Context context) {
        return DataController.getData(context, confirmExit, true);
    }
}
