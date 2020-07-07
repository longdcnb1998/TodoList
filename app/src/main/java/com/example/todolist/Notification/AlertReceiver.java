package com.example.todolist.Notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.todolist.MainActivity;
import com.example.todolist.R;

import java.util.Calendar;

public class AlertReceiver extends BroadcastReceiver {
    public static String channelID = "channelID";
    public static String channelName = "channelName";
    private PendingIntent pendingIntent;
    private NotificationManager notificationManager;


    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationChannel channel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
            getManager(context).createNotificationChannel(channel);
        }

        Intent notificationIntent = new Intent(context, MainActivity.class);
        pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (Calendar.getInstance().get(Calendar.MINUTE) < 10) {
            NotificationController.showNotification(context, pendingIntent, 1, R.drawable.ic__alarm, context.getResources().getString(R.string.titleAlarm) + " " + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":0" + Calendar.getInstance().get(Calendar.MINUTE), "", R.raw.notification);
        } else
            NotificationController.showNotification(context, pendingIntent, 1, R.drawable.ic__alarm, context.getResources().getString(R.string.titleAlarm) + " " + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE), "", R.raw.notification);
    }

    private NotificationManager getManager(Context context) {
        if (notificationManager == null) {
            notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return notificationManager;
    }


}

