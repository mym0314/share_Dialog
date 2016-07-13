package com.example.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.StreamHandler;

/**
 * Created by mym_0314 on 2016/5/11.
 */
public class IOUtils {

    public static String getStringFromIO(FileInputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String len;
        StringBuilder sb = new StringBuilder();
        while ((len = br.readLine()) != null) {
            sb.append(len);
        }
        br.close();
        return sb.toString();
    }
}
