package com.example.utils;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by mym_0314 on 2016/5/11.
 */
public class ExternalStorageUtils {

    public static void writeToExternal(String name,String content) throws IOException {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File external = Environment.getExternalStorageDirectory();
            File file = new File(external, name);
            FileOutputStream fos = new FileOutputStream(file);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos,"utf-8"),8192);
            bw.write(content);
            bw.flush();
            bw.close();
        }
    }
    public static String readFromExternal(String name) throws IOException {
        String content = "";
        FileInputStream fis=null;
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File file = new File(Environment.getExternalStorageDirectory(),name);
            if (!file.exists())return null;
            fis = new FileInputStream(file);
            content = IOUtils.getStringFromIO(fis);
    }
        if(fis!=null) {
            fis.close();
        }
        return content;
    }

}
