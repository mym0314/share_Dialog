package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.example.constant.Constant;
import com.example.notification.BaseNotification;

/**
 * Created by mym_0314 on 2016/5/11.
 */
public class StartBroadCast extends BroadcastReceiver {
    private MyConnection  conn;
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case Constant.START:
                conn = new MyConnection();
                Intent service = new Intent();
                service.setAction(Constant.START);
                context.startService(service);
               // context.bindService(service,conn,Context.BIND_AUTO_CREATE);
                break;
            case Constant.NOTIFICATION:
                int type=intent.getIntExtra("Notification",0);
                if(type==Constant.BASE_NOTIFICATION){
                BaseNotification.startNotification(context,type);
            }else{
                BaseNotification.closeNotification();
            }
                break;
        }
    }

    public class MyConnection implements ServiceConnection{
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
