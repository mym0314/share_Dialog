package com.example.mytextaidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;

import com.example.myaidl.IMyAidlInterface;
import com.example.myaidl.Person;

public class MainActivity extends Activity {

    private IMyAidlInterface getPerson;
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        person = new Person("小明", 12);
        initService();
    }

    private void initService() {
        Intent intent = new Intent();
        intent.setClassName("com.example.myaidl", "com.example.myaidl.MyService");
        // intent.setAction("com.example.myaidl.Service");
        bindService(intent, con, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(con);
    }

    public void getPerson(View v) {
        try {
            getPerson.getPerson("你好", "123456", person);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private ServiceConnection con = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            getPerson = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

}
