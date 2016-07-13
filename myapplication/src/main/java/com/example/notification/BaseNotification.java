package com.example.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.constant.Constant;
import com.example.myapplication.R;

/**
 * Created by mym_0314 on 2016/5/12.
 */
public class BaseNotification {
    private static NotificationManager mManager;

    public static void startNotification(Context ctx, int type) {
        mManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = null;
        Log.e("Button", "start");
        switch (type) {
            case Constant.BASE_NOTIFICATION:
                notification = getBaseNotify(ctx);
                break;
        }
        mManager.notify(R.mipmap.ic_launcher, notification);
    }

    private static Notification getBaseNotify(Context ctx) {
        Notification.Builder mBuilder = new Notification.Builder(ctx);
        RemoteViews view = new RemoteViews(ctx.getPackageName(), R.layout.view_basenotification);
        Intent intent = new Intent();
        if (Constant.BASE_NOTIFICATION == 1)
            intent.putExtra("Notification", Constant.BASE_NOTIFICATION);
        else
            intent.putExtra("Notification", 1);
        intent.setAction(Constant.NOTIFICATION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(ctx, Constant.BASE_NOTIFICATION,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);
        view.setOnClickPendingIntent(R.id.cancel, pendingIntent);
        mBuilder.setContent(view);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        return mBuilder.build();
    }

    public static void closeNotification() {
        if (mManager == null) return;
        mManager.cancel(R.mipmap.ic_launcher);
    }


}
