package com.example.todolist.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class DataController {

    private static String getPackageName(Context context){
        return context.getPackageName();
    }

    public static void saveData(Context context, String key, String data) {
        SharedPreferences sp = context.getSharedPreferences(getPackageName(context), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, data);
        editor.commit();
    }

    public static String getData(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(getPackageName(context), Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    public static String getData(Context context, String key, String defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(getPackageName(context), Context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }

    public static void saveData(Context context, String key, boolean trueOrFalse) {
        SharedPreferences sp = context.getSharedPreferences(getPackageName(context), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, trueOrFalse);
        editor.commit();
    }

    public static boolean getData(Context context, String key, boolean defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(getPackageName(context), Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

    public static void saveData(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(getPackageName(context), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getData(Context context, String key, int defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(getPackageName(context), Context.MODE_PRIVATE);
        return sp.getInt(key, defaultValue);
    }

    public static void saveData(Context context, String key, Set<String> setString) {
        SharedPreferences sp = context.getSharedPreferences(getPackageName(context), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putStringSet(key, setString);
        editor.commit();
    }

    public static Set<String> getData(Context context, String key, Set<String> defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(getPackageName(context), Context.MODE_PRIVATE);
        return sp.getStringSet(key, defaultValue);
    }
}
