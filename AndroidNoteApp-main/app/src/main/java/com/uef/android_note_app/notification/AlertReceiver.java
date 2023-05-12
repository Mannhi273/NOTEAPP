package com.uef.android_note_app.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.uef.android_note_app.R;

import java.util.Calendar;

public class AlertReceiver extends BroadcastReceiver {
    private static final String EXTRA_NOTE_ID = "id";
    private static final String EXTRA_NOTE_TITLE = "title";
    private static final String EXTRA_MESSAGE = "message";

    @Override
    public void onReceive(Context context, Intent intent) {
        String id = intent.getStringExtra(EXTRA_NOTE_ID);
        String title = intent.getStringExtra(EXTRA_NOTE_TITLE);
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        NotificationHelper notificationHelper = new NotificationHelper(context,id,title);
        NotificationCompat.Builder nb = notificationHelper
                .getChannelNotification(id,
                        "You have an alarm note: " + title
                        ,message);

        notificationHelper.getManager().notify(Integer.parseInt(id), nb.build());
    }



}
