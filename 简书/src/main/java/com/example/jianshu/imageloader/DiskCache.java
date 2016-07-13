package com.example.jianshu.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.jianshu.utils.StreamClose;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by mym_0314 on 2016/3/23.
 */
public class DiskCache implements RequirCache {
    private File parent;

    public DiskCache(Context ctx) {
        if (ctx.getExternalCacheDir() != null) {
            parent = new File(ctx.getExternalCacheDir().toString());
        }
    }


    @Override
    public Bitmap get(String key) {
        File file = new File(parent, key);
        if (!file.exists())
            return null;
        Bitmap bitmap = BitmapFactory.decodeFile(file.toString());

        return bitmap;
    }

    @Override
    public void set(String key, Bitmap value) {
        File file = new File(parent, key);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            StreamClose.close(fos);
        }
        value.compress(Bitmap.CompressFormat.PNG, 100, fos);
    }
}
