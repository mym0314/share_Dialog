package com.example.jianshu.imageloader;


import android.graphics.Bitmap;

/**
 * Created by mym_0314 on 2016/3/23.
 */
public interface RequirCache {

    Bitmap get(String key);

    void set(String key,Bitmap value);
}
