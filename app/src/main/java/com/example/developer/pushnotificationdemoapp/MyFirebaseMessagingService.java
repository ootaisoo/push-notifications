package com.example.developer.pushnotificationdemoapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Random;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private final static String CHANNEL_ID = "channel_id";

    private static final String LOG_TAG = MyFirebaseMessagingService.class.getSimpleName();

    private Intent intent;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(LOG_TAG, "onMessageReceived() ");
        Map<String, String> data = remoteMessage.getData();
        String nick = data.get("Nick");
        String body = data.get("body");
        String room = data.get("Room");

        intent = new Intent("custom-event-name");
        intent.putExtra("Nick", nick);
        intent.putExtra("body", body);
        intent.putExtra("Room", room);

        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        broadcastManager.sendBroadcast(intent);

        getNotificationManager().notify(new Random().nextInt(), getNotification(getPendingIntent(nick, body, room)));
    }

    private NotificationManager getNotificationManager(){
        return (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
    }

    private Notification getNotification(PendingIntent pendingIntent){
        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setAutoCancel(true)
                .setContentTitle("New message from server")
                .setContentText("message")
                .setSmallIcon(R.drawable.uf_logo_medium)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setFullScreenIntent(pendingIntent, true)
                .build();
    }

    public PendingIntent getPendingIntent(String nick, String body, String room){
        return PendingIntent.getActivity(this, 0, intent, 0);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel_name";
            String description = "channel_description";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
