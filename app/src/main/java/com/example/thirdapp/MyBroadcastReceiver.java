package com.example.thirdapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;


public class MyBroadcastReceiver extends BroadcastReceiver {
    String id;
    String description;
    int importance;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        android.os.Debug.waitForDebugger();

        if (intent.getAction().matches("com.example.broadcast.MY_NOTIFICATION")) {
            id = "channel_1";
            description = "getTravelsChannel";
            importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(id, description, importance);//Generating channel


            Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.androidauthority.com/"));
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, 0);

            Notification.Builder mBuilder = new Notification.Builder(context, id);
            mBuilder.setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle("travels alerts")
                    .setContentText("got new travel!")
                    .setSound(Uri.parse("android.resource://" +
                            context.getPackageName() + "/" + R.raw.birdssounds))
                    .setContentIntent(pendingIntent);

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
                    .build();

            Uri soundUri = Uri.parse("android.resource://" +
                    context.getPackageName() + "/" + R.raw.birdssounds);

            channel.setSound(soundUri, audioAttributes);


            // Gets an instance of the NotificationManager service
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(id);
            mNotificationManager.notify(001, mBuilder.build());

        }
    }
}
