package com.example.httppostget;

import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by mym_0314 on 2016/5/3.
 */
public class HttpUtils {


    public static void httpPost(String url) {
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("X-Auth-Token", "token");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestMethod("POST");
            int code = conn.getResponseCode();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void httpGet(String url){
        InputStream  is = null;
        try {
            URL httpUrl = new URL(url);
           HttpURLConnection  conn= (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            int code = conn.getResponseCode();
            if(code ==200){
               is =  conn.getInputStream();
                int n;
                if(is!=null){

                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
