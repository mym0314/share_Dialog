package com.example.jianshu.utils;

import android.util.Log;

import com.example.jianshu.myview.MyListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mym_0314 on 2016/3/23.
 */
public class JsonUtils {
    public static <T> T getObjectFromJson(String json, Class<T> t) throws IllegalAccessException, InstantiationException {
        JSONObject obj = null;
        try {
            obj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JsonUtils", "Json格式错误");
            e.printStackTrace();
        }
        T instance = t.newInstance();
        Field[] fields = t.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            try {
                if (f.getType() == String.class) {
                    f.set(instance, obj.getString(f.getName()));
                } else if (f.getType() == int.class) {
                    f.setInt(instance, obj.getInt(f.getName()));
                } else if (f.getType() == Double.class) {
                    f.setDouble(instance, obj.getDouble(f.getName()));
                }
            } catch (JSONException e) {
                Log.e("JsonUtils", "Json格式错误");
                e.printStackTrace();
            }
        }
        return instance;
    }

    public static <T> List<T> getListFromJsonArray(String json, Class<T> t) {
        List<T> mList = null;
        try {
            JSONArray array = new JSONArray(json);
            mList = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                T instance = getObjectFromJson(obj.toString(), t);
                mList.add(instance);
            }
        } catch (JSONException e) {
            Log.e("JsonUtils", "Json格式错误");
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return mList;
    }

}
