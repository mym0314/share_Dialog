package com.example.jianshu.imageloader;

import android.graphics.Bitmap;
import android.util.LruCache;


public class MemoryCache implements RequirCache {

    private LruCache<String,Bitmap>  lruCache;

    public MemoryCache(){
        int maxSize = (int) (Runtime.getRuntime().maxMemory()/1024);
        int maxCache = maxSize/4;

        lruCache = new LruCache<String,Bitmap>(maxCache){
            @Override
            protected int sizeOf(String key, Bitmap value) {


                return  value.getRowBytes()/1024*value.getHeight();
            }
        };
    }

    @Override
    public Bitmap get(String key) {
        return lruCache.get(key);
    }

    @Override
    public void set(String key, Bitmap value) {
            lruCache.put(key,value);
    }
}
