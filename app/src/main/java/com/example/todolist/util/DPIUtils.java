package com.example.todolist.util;

import android.content.Context;

public class DPIUtils {
    public static int dpToPx(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int)(dp * density);
    }
}
