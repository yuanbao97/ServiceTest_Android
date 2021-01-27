package com.example.servicetest.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.servicetest.R;
import com.example.servicetest.activity.MainActivity;

public class MyService extends Service {

    public class DownloadBinder extends Binder {

        public void startDownload() {
            Log.e(TAG, "startDownload executed");
        }

        public int getProgress() {
            Log.e(TAG, "getProgress executed");
            return 0;
        }
    }

    private static final String TAG = "MyService";
    private DownloadBinder mBinder = new DownloadBinder();
    private NotificationManager manager;
    private Notification notification;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate executed");
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "State";
            String channelName = "状态";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            manager.createNotificationChannel(new NotificationChannel(channelId, channelName, importance));
        }
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        notification = new NotificationCompat.Builder(this, "default")
                .setContentTitle("This is content title")
                .setChannelId("State")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)
//                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .build();

        startForeground(1, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy executed");
    }
}