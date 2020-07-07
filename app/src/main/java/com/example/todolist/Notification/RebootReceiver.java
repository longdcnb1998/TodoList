package com.example.todolist.Notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

import java.util.Calendar;

public class RebootReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences preferences = context.getSharedPreferences("DataNoti",Context.MODE_PRIVATE);
        int Notiid = preferences.getInt("Notiid",-1);
        String time = preferences.getString("timeStart","");
       if (time.length() > 0 && Notiid!= -1){
           addAlarm(Notiid,time,context);
       }
    }


    private void addAlarm(int id, String timeStart, Context context) {
        String[] time = timeStart.split(":");
        int hours = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intentNotification = new Intent(context, AlertReceiver.class);
        PendingIntent pending = PendingIntent.getBroadcast(context, id, intentNotification, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DATE, 7);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pending);
        }
    }
}


