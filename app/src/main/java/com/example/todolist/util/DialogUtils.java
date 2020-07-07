package com.example.todolist.util;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import androidx.fragment.app.FragmentManager;

public class DialogUtils {
    public static Dialog getNewDialog(Activity activity) {
        Dialog dialog;
        if (ScreenUtils.isFullScreen(activity)) {
            dialog = new Dialog(activity, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        } else {
            dialog = new Dialog(activity, android.R.style.Theme_DeviceDefault_Light_NoActionBar);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

                if (AppSettings.isLightTheme(activity)) {
                    dialog.getWindow().setStatusBarColor(Color.WHITE);
                    dialog.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); // set status text dark
                } else {
                    dialog.getWindow().setStatusBarColor(AppSettings.getAppColor(activity));
                }
            }
        }

        dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Translucent;
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        return dialog;
    }

    public static Dialog getNewDialog(Activity activity, int animation) {
        Dialog dialog;
        if (ScreenUtils.isFullScreen(activity)) {
            dialog = new Dialog(activity, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        } else {
            dialog = new Dialog(activity, android.R.style.Theme_DeviceDefault_Light_NoActionBar);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

                if (AppSettings.isLightTheme(activity)) {
                    dialog.getWindow().setStatusBarColor(Color.WHITE);
                    dialog.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); // set status text dark
                } else {
                    dialog.getWindow().setStatusBarColor(AppSettings.getAppColor(activity));
                }
            }
        }

        dialog.getWindow().getAttributes().windowAnimations = animation;
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        return dialog;
    }

    public static Dialog getNewDialog(Activity activity, boolean isShowKeyboard) {
        Dialog dialog;
        if (ScreenUtils.isFullScreen(activity)) {
            dialog = new Dialog(activity, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        } else {
            dialog = new Dialog(activity, android.R.style.Theme_DeviceDefault_Light_NoActionBar);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

                if (AppSettings.isLightTheme(activity)) {
                    dialog.getWindow().setStatusBarColor(Color.WHITE);
                    dialog.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); // set status text dark
                } else {
                    dialog.getWindow().setStatusBarColor(AppSettings.getAppColor(activity));
                }
            }
        }

        dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Translucent;
        dialog.getWindow().setSoftInputMode(isShowKeyboard ? WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE : WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        return dialog;
    }

    public static Dialog getNewDialog(Activity activity, boolean isShowKeyboard, boolean isLightTheme) {
        Dialog dialog;
        if (ScreenUtils.isFullScreen(activity)) {
            dialog = new Dialog(activity, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        } else {
            dialog = new Dialog(activity, android.R.style.Theme_DeviceDefault_Light_NoActionBar);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

                if (isLightTheme) {
                    dialog.getWindow().setStatusBarColor(Color.WHITE);
                    dialog.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); // set status text dark
                } else {
                    dialog.getWindow().setStatusBarColor(AppSettings.getAppColor(activity));
                }
            }
        }

        dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Translucent;
        dialog.getWindow().setSoftInputMode(isShowKeyboard ? WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE : WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        return dialog;
    }

    public static Dialog getNewDialog(Activity activity, boolean isShowKeyboard, int color) {
        Dialog dialog;
        if (ScreenUtils.isFullScreen(activity)) {
            dialog = new Dialog(activity, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        } else {
            dialog = new Dialog(activity, android.R.style.Theme_DeviceDefault_Light_NoActionBar);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

                if (AppSettings.isLightTheme(activity)) {
                    dialog.getWindow().setStatusBarColor(Color.WHITE);
                    dialog.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); // set status text dark
                } else {
                    dialog.getWindow().setStatusBarColor(color);
                }
            }
        }

        dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Translucent;
        dialog.getWindow().setSoftInputMode(isShowKeyboard ? WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE : WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        return dialog;
    }

    public static Dialog getNewDialogPhotoVideoBrowser(Activity activity) {
        Dialog dialog;
        if (ScreenUtils.isFullScreen(activity)) {
            dialog = new Dialog(activity, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        } else {
            dialog = new Dialog(activity, android.R.style.Theme_DeviceDefault_Light_NoActionBar);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                dialog.getWindow().setStatusBarColor(Color.BLACK);
            }
        }

        dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Translucent;
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        return dialog;
    }

    public static Dialog getNewDialogTransparent(Activity activity, int animation) {
        Dialog dialog;
        if (ScreenUtils.isFullScreen(activity)) {
            dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        } else {
            dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

                if (AppSettings.isLightTheme(activity)) {
                    dialog.getWindow().setStatusBarColor(Color.WHITE);
                    dialog.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); // set status text dark
                } else {
                    dialog.getWindow().setStatusBarColor(AppSettings.getAppColor(activity));
                }
            }
        }

        dialog.getWindow().getAttributes().windowAnimations = animation;
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        return dialog;
    }

    public static Dialog getNewDialogTransparent(Activity activity, int animation, int color) {
        Dialog dialog;
        if (ScreenUtils.isFullScreen(activity)) {
            dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        } else {
            dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

                if (AppSettings.isLightTheme(activity)) {
                    dialog.getWindow().setStatusBarColor(Color.WHITE);
                    dialog.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); // set status text dark
                } else {
                    dialog.getWindow().setStatusBarColor(color);
                }
            }
        }

        dialog.getWindow().getAttributes().windowAnimations = animation;
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        return dialog;
    }

    public static Dialog getNewDialogHideKeyboard(Activity activity) {
        Dialog dialog;
        if (ScreenUtils.isFullScreen(activity)) {
            dialog = new Dialog(activity, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        } else {
            dialog = new Dialog(activity, android.R.style.Theme_DeviceDefault_Light_NoActionBar);
        }
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        return dialog;
    }

    public static boolean enableShowDialogFragment(FragmentManager fragmentManager, String tagDialogFragment) {
        if (fragmentManager.findFragmentByTag(tagDialogFragment) !=  null) {
            return false;
        }
        return true;
    }
}
