package com.example.testdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by mym_0314 on 2016/5/18.
 */
public class ImageLoader {
    private Context ctx;
    private static ImageLoader mImageLoader;
    private static Handler handler = new Handler();
    private static final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(5);

    private static LruCache<String, Bitmap> mLruCache = new LruCache<String, Bitmap>((int) (Runtime.getRuntime().maxMemory()/1024/4)) {
        @Override
        protected int sizeOf(String key, Bitmap bitmap) {
            return bitmap.getHeight()/1024*bitmap.getRowBytes();
        }
   };
    private ImageLoader(Context ctx){
            this.ctx = ctx;
    }
    public static ImageLoader getInstance(Context ctx){
        if(mImageLoader==null)
            mImageLoader = new ImageLoader(ctx);
        return mImageLoader;
    }


    public static void imgLoad(final ImageView iv, final String url) {
        final Bitmap bitmap;

        bitmap =mLruCache.get(url);
        if(bitmap !=null){
            iv.setImageBitmap(bitmap);

            return;
        }
        iv.setTag(url);
        THREAD_POOL.submit(new Runnable() {
            @Override
            public void run() {
                InputStream is =null;
                Bitmap bitmap =null;
                try {
                    URL  httpUrl = new URL(url);
                   HttpURLConnection  con = (HttpURLConnection) httpUrl.openConnection();
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.setRequestMethod("POST");
                    con.setConnectTimeout(5000);
                    con.setReadTimeout(5000);
                    Log.e("JSON",url);
                    int code =con.getResponseCode();
                    Log.e("JSON",code+"");
                    if(code ==200){
                        is = con.getInputStream();
                        if(is!=null)
                          bitmap = BitmapFactory.decodeStream(is);
                        final Bitmap finalBitmap = bitmap;
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("JSON",(finalBitmap==null)+"");
                                iv.setImageBitmap(finalBitmap);
                                mLruCache.put(url,finalBitmap);
                            }
                        });
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(is!=null){
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }


}
