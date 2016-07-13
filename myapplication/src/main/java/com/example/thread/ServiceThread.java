package com.example.thread;



import com.example.bean.ContentBean;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;


public class ServiceThread extends Thread {
    private Socket mSocket;
    private InputStream is;

    public ServiceThread(Socket socket) {
        this.mSocket = socket;
    }

    @Override
    public void run() {
        try {
            is = mSocket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            ContentBean bean = (ContentBean) ois.readObject();
           // MyApplication.mList.add(bean);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
