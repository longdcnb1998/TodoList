package com.example.todolist;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

public class ColorUtils {

    @TargetApi(23) @SuppressWarnings("deprecation")
    public static int getColor(Context context, int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(id);
        } else {
            //noinspection deprecation
            return context.getResources().getColor(id);
        }
    }
}
