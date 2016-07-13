package com.example.review;

import android.renderscript.ScriptGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by mym_0314 on 2016/6/26.
 */
public class NetUtils {

    private static InputStream getInputStream(String url) {
        InputStream is = null;
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            int code = conn.getResponseCode();
            if (code == 200) {
                is = conn.getInputStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return is;
    }

    public static String getJsonFromNet(String url) {

            InputStream is = getInputStream(url);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String str = null;
            try

            {
                while ((str = br.readLine()) != null) {
                    sb.append(str);
                }
                if (sb.length() > 0)
                    return sb.toString();

            }

            catch(
            IOException e
            )

            {
                e.printStackTrace();
            }

            finally

            {

                try {
                    br.close();
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
    }
}


