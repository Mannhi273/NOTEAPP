package com.uef.android_note_app.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.uef.android_note_app.R;

public class NotificationHelper extends ContextWrapper {


    private NotificationManager mMananger;

    public NotificationHelper(Context base, String channelID, String channelName) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannels(channelID,channelName);
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    public void createChannels(String channelID,String channelName){
        NotificationChannel channel = new NotificationChannel(
                channelID,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true);
        channel.enableVibration(true);
        channel.setLightColor(R.color.colorAccent);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        channel.setDescription("this is chanel" + channelID);


        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager(){
        if (mMananger == null){
            mMananger = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mMananger;
    }

    public NotificationCompat.Builder getChannelNotification(String channelID , String title, String message){
        return new NotificationCompat.Builder(getApplicationContext(),channelID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_notification)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM);

    }
}
