package com.example.myapplication;

import android.app.Application;

import com.example.bean.ContentBean;
import com.example.db.DBhelper;
import com.example.thread.ServiceThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {
    private DBhelper dBhelper;
    @Override
    public void onCreate() {
        super.onCreate();
        dBhelper = new DBhelper(this);
    }
}
