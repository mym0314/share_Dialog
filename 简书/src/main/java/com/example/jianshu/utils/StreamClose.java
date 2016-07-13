package com.example.jianshu.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by mym_0314 on 2016/3/23.
 */
public class StreamClose {
    public static void close(Closeable close){
        if(close!=null){
            try {
                close.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
