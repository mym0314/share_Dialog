package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.constant.Constant;

/**
 * Created by mym_0314 on 2016/5/11.
 */
public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        switch (intent.getAction()){
            case Constant.START:
                Log.e("test","Service");
            break;
        }


        return super.onStartCommand(intent, flags, startId);
    }
}
