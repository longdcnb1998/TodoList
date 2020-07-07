package com.example.todolist.Notification;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.todolist.DataController;

/**
 * @since Monday, June 02, 2014
 * @author hoangcaomobile
 *
 */
public class NotificationController {

	public static String notificationSound = "notificationSound";
	public static String notificationVibrate = "notificationVibrate";
	public static String notificationLight = "notificationLight";

	private static String CHANNEL_ID = "ciusof2020";
	@SuppressLint("NewApi")
	private static void createNotificationChannel(Context context) {
		CharSequence channelName = CHANNEL_ID;
		String channelDesc = "channelDesc";
		// Create the NotificationChannel, but only on API 26+ because
		// the NotificationChannel class is new and not in the support library
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			int importance = NotificationManager.IMPORTANCE_DEFAULT;
			NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
			channel.setDescription(channelDesc);
			// Register the channel with the system; you can't change the importance
			// or other notification behaviors after this
			NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
			assert notificationManager != null;
			NotificationChannel currChannel = notificationManager.getNotificationChannel(CHANNEL_ID);
			if (currChannel == null)
				notificationManager.createNotificationChannel(channel);
		}
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public static void showNotification(Context context, PendingIntent pendingIntent, int id, int smallIcon, String title, String content, int sound) {
		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

		// 13/08/2019
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			createNotificationChannel(context);

			Notification.Builder builder = new Notification.Builder(context, CHANNEL_ID);
			builder.setSmallIcon(smallIcon)
			.setColor(AppSettings.getAppColor(context))
			.setContentTitle(title)
			.setContentText(content)
			.setContentIntent(pendingIntent)
			.setAutoCancel(true);
			
			if (DataController.getData(context, notificationSound, true)) {
				builder.setSound(Uri.parse("android.resource://" + AppUtils.getAppPackage(context) + "/" + sound));
			}
			if (DataController.getData(context, notificationVibrate, true)) {
				// Now we set the vibrate member variable of our Notification
				// After a 100ms delay, vibrate for 200ms then pause for another
				// 100ms and then vibrate for 300ms
				builder.setVibrate(new long[]{100, 200, 100, 300});
			}
			if (DataController.getData(context, notificationLight, true)) {
				builder.setLights(0xa, 234, 2345);
			} 

			notificationManager.notify(id, builder.build());
		} else {
			NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
			builder.setSmallIcon(smallIcon)
			.setColor(AppSettings.getAppColor(context))
			.setContentTitle(title)
			.setContentText(content)
			.setContentIntent(pendingIntent)
			.setAutoCancel(false);

			if (DataController.getData(context, notificationSound, true)) {
				builder.setSound(Uri.parse("android.resource://" + AppUtils.getAppPackage(context) + "/" + sound));
			}
			if (DataController.getData(context, notificationVibrate, true)) {
				// Now we set the vibrate member variable of our Notification
				// After a 100ms delay, vibrate for 200ms then pause for another
				// 100ms and then vibrate for 300ms
				builder.setVibrate(new long[]{100, 200, 100, 300});
			}
			if (DataController.getData(context, notificationLight, true)) {
				builder.setLights(0xa, 234, 2345);
			} 

			notificationManager.notify(id, builder.build());
		}
	}

	public static void cancelNotification(Context context, int id) {
		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		if (id == 0) {
			notificationManager.cancelAll();
		} else {
			notificationManager.cancel(id);
		}
	}
}