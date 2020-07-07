package com.example.todolist;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author hoangcaomobile
 * @since Sunday, April 19, 2015
 */

public class ColorController {

    public static void setBackgroundColor(Context context, View view) {
        view.setBackgroundColor(AppSettings.getAppColor(context));
    }

    public static void setBackgroundColor(View view, int color) {
        view.setBackgroundColor(color);
    }

    public static void setHintColor(Context context, EditText et) {
        et.setHintTextColor(ColorUtils.getColor(context, R.color.gray));
    }

    public static void setTintColor(Context context, ImageView iv, int color) {
        iv.setColorFilter(ColorUtils.getColor(context, color), PorterDuff.Mode.SRC_IN);
    }

    public static void setTintColor(ImageView iv, int color) {
        iv.setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }

    public static void setTintColor(Context context, ImageButton ib, int color) {
        ib.setColorFilter(ColorUtils.getColor(context, color), PorterDuff.Mode.SRC_IN);
    }

    public static void setTintColor(Context context, ImageView iv) {
        iv.setColorFilter(AppSettings.getAppColor(context), PorterDuff.Mode.SRC_IN);
    }

    public static void setTintColor(Context context, ImageButton ib) {
        ib.setColorFilter(AppSettings.getAppColor(context), PorterDuff.Mode.SRC_IN);
    }

    public static void setTintColor(Context context, TextView tv) {
        tv.setTextColor(AppSettings.getAppColor(context));
    }

    public static void setTintColor(Context context, Button bt) {
        bt.setTextColor(AppSettings.getAppColor(context));
    }

    public static void setTintColorWhite(Context context, ImageView iv) {
        iv.setColorFilter(ColorUtils.getColor(context, R.color.white), PorterDuff.Mode.SRC_IN);
    }

    public static void setTintColorGray(Context context, ImageView iv) {
        iv.setColorFilter(ColorUtils.getColor(context, R.color.gray), PorterDuff.Mode.SRC_IN);
    }

    public static void setTintColorCustom(ImageView iv, int colorCode) {
        iv.setColorFilter(colorCode, PorterDuff.Mode.SRC_IN);
    }
}