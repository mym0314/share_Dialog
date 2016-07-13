package com.example.jianshu.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by mym_0314 on 2016/3/23.
 */
public class HttpUtils {

    public static InputStream getInputStreamFromHttp(String url) {
        InputStream is = null;
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setConnectTimeout(3 * 1000);
            conn.setReadTimeout(5 * 1000);
            conn.setRequestMethod("GET");
            int code = conn.getResponseCode();
            if (code == 200) {
                is = conn.getInputStream();
            }
        } catch (MalformedURLException e) {
            Log.e("HttpUtils", "new URL");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("HttpUtils", "openConnection");
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    Log.e("HttpUtils", "is.close");
                    e.printStackTrace();
                }
            }
        }
        return is;
    }

    public static boolean isNet(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] allNetworkInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : allNetworkInfo) {
            if (ni.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;
    }

}
