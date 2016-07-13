package com.example.testdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by mym_0314 on 2016/5/18.
 */
public class NetUtils {

    public static String getJsonFromHttp(String url) throws IOException {
        InputStream is = null;
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection con = (HttpURLConnection) httpUrl.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setConnectTimeout(5000);
            con.setConnectTimeout(5000);
            int code = con.getResponseCode();

            if (code == 200) {
                is = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                String str;
                while ((str = br.readLine()) != null) {
                    sb.append(str);
                }
                return sb.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return null;
    }
}
