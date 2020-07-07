package com.example.todolist;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class APIConnector {

    public static final MediaType JSON = MediaType.parse("application/json");

    public static void post(final String url, final String json, final Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();

        RequestBody requestBody = RequestBody.create(json, JSON);

        final Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        if (callback != null) {
            client.newCall(request).enqueue(callback);
        }
    }


    public static void get(final String url,final String auth ,final Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();


        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        if (callback != null) {
            client.newCall(request).enqueue(callback);
        }
    }

    public static void put(String url, String json, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();

        RequestBody requestBody = RequestBody.create(json, JSON);

        final Request request = new Request.Builder()
                .url(url)
                .put(requestBody)
                .build();
        if (callback != null) {
            client.newCall(request).enqueue(callback);
        }
    }

    public static void delete(String url, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();


        final Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();
        if (callback != null) {
            client.newCall(request).enqueue(callback);
        }
    }
}
