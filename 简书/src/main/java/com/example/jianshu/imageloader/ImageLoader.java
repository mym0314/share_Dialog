package com.example.jianshu.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jianshu.R;
import com.example.jianshu.utils.HttpUtils;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by mym_0314 on 2016/3/23.
 */
public class ImageLoader {
    private ExecutorService THEAD_POOL = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private Context ctx;
    private MemoryCache cache = new MemoryCache();

    private Handler handler = new Handler();

    public ImageLoader(Context ctx) {
        this.ctx = ctx;
    }

    public void displayImage(final ImageView iv, final String url) {
        Bitmap bitmap;
        bitmap = cache.get(url);
        if (bitmap != null) {
            iv.setImageBitmap(bitmap);
            return;
        }
        if (!HttpUtils.isNet(ctx)) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ctx, "请先打开网络", Toast.LENGTH_SHORT).show();
                }
            });
        }
        iv.setTag(url);
        THEAD_POOL.submit(new Runnable() {
            private Bitmap bitmap;

            @Override
            public void run() {
                bitmap = null;
                bitmap = downLoadBitmap(url);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (bitmap != null) {
                            iv.setImageBitmap(bitmap);
                            cache.set(url, bitmap);
                        } else {
                            iv.setImageResource(R.mipmap.ic_launcher);
                        }
                    }
                });
            }
        });
    }

    public Bitmap downLoadBitmap(String url) {
        InputStream is = HttpUtils.getInputStreamFromHttp(url);

        return BitmapFactory.decodeStream(is);
    }

    public void setCacheType(MemoryCache cache) {
        this.cache = cache;
    }
}



