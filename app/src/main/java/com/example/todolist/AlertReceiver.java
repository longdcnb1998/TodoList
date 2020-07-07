package com.example.todolist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String a= intent.getStringExtra("a");
        String b = intent.getStringExtra("b");
        NotificationHelper notificationHelper = new NotificationHelper(context);// khởi tạo kênh thông báo
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification2(a,b);// trình tạo thông báo
    // tạo ra title, icon...
        notificationHelper.getManager().notify(1, nb.build());// thông báo thay đổi
      // nb.build() =Notification
    }
}
