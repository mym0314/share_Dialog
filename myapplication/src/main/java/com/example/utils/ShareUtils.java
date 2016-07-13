package com.example.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.File;

/**
 * Created by mym_0314 on 2016/5/28.
 */
public class ShareUtils {
    private SharedPreferences  sp;
    private SharedPreferences.Editor  editor;

    public ShareUtils(Context ctx , String file){
         sp= ctx.getSharedPreferences(file,Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void saveName(String name){
        editor.putString("name",name);
        editor.commit();
    }
    public String getName(){
        return sp.getString("name","");
    }

    public void saveId(String id){
        editor.putString("id", id);
        editor.commit();
    }
    public String getId(){
        return sp.getString("id", "");
    }

    public void  saveDate(String date){
        editor.putString("date", date);
        editor.commit();
    }
    public String getDate(){
        return sp.getString("date", "");
    }



}
