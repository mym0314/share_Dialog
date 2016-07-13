package com.example.jianshu.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by mym_0314 on 2016/3/23.
 */
public class DataUtils {
    public static void setJsonToCache(Context ctx, String name, String json) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File(new File(ctx.getExternalCacheDir().toString()), name));
        fos.write(json.getBytes());
        fos.close();
    }
    public static String getJsonFromCache(Context ctx,String name) throws FileNotFoundException {
        File  file = new File(new File(ctx.getExternalCacheDir().toString()),name);
        if(!file.exists())
            return null;
        FileInputStream fis = new FileInputStream(file);
        String json = null;
        try {
            json= getStringFromInputStream(fis);
        } catch (IOException e) {
            Log.e("getStringFromStream","readLine");
        }
        return json;

    }


    public static String getStringFromInputStream(InputStream is) throws IOException {
        if(is==null)
            return null;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while((line=br.readLine())!=null){
            sb.append(line);
        }
        return sb.toString();
    }
}
