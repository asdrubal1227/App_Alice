package com.example.brayanasdrubal.appalice;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // For our recurring task, we'll just display a message
        Toast.makeText(context, "App Alice", Toast.LENGTH_SHORT).show();
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_app2)
                        .setTicker("App Alice te recuerda")
                        .setContentTitle("App Alice")
                        .setContentText("Es hora de que entrenes de nuevo")
                        .setLargeIcon(BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_app2))
                        .setAutoCancel(true);
        // Patrón de vibración: 1 segundo vibra, 0.5 segundos para, 1 segundo vibra
        long[] pattern = new long[]{2000,1000,2000};
// Uso en API 11 o mayor
        builder.setVibrate(pattern);

        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(5, builder.build());
    }
}