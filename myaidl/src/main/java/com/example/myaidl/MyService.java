package com.example.myaidl;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.jar.Attributes;

/**
 * Created by mym_0314 on 2016/5/5.
 */
public class MyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    class MyBinder extends IMyAidlInterface.Stub {
        @Override
        public void getPerson(String account, String pwd, Person person) throws RemoteException {
            String name = person.getName();
            int age = person.getAge();
            Log.e("Service", "Service");
            Intent intent = new Intent(MyService.this, Main2Activity.class);
            Bundle bundle = new Bundle();
            bundle.putString("account", account);
            bundle.putString("pwd", pwd);
            bundle.putString("name", name);
            bundle.putInt("age", age);
            intent.putExtras(bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
