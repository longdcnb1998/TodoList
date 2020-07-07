package com.example.todolist;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {
    public static final String channelID = "channelID";
    public static final String channelName = "ChannelName";
    private NotificationManager mManager;
    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }
    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
        getManager().createNotificationChannel(channel);
        channel.enableLights(true);// bật đèn
        channel.enableVibration(true);// rung
        channel.setLightColor(R.color.colorPrimaryDark);// màu tối
        channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);// chế độ màn hình khóa
        //  getSystemService(Context.NOTIFICATION_SERVICE).createNotificationChannel(channel);
        // tao kenh thong bao
    }
    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        }
        return mManager;
    }
    public NotificationCompat.Builder getChannelNotification() {// trình tạo thông báo
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("Alarm!")
                .setContentText("Your AlarmManager is working.")
                .setSmallIcon(R.drawable.ic__alarm);
    }
    public NotificationCompat.Builder getChannelNotification2(String a, String b) {// trình tạo thông báo
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle(a)
                .setContentText(b)
                .setSmallIcon(R.drawable.ic__alarm);
    }
}