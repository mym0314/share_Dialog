package com.example.myaidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private MyService.MyBinder binder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.setAction("com.example.myaidl.Service");
        MyCon  con = new MyCon();
        bindService(intent, con, BIND_AUTO_CREATE);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            binder.getPerson("123","123455",new Person("nihao",12));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private class  MyCon implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
              binder =  (MyService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
