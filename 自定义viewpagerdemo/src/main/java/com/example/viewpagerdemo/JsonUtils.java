package com.example.viewpagerdemo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mym_0314 on 2016/5/3.
 */
public class JsonUtils {

    public static List getJsonList(String url, String json) {
        List mList = null;
        if (url.equals(Model.ADVERSTURL)) {
            mList = getViewPagerInfo(json);
        }
        return mList;
    }

    private static List<ViewPagerInfo> getViewPagerInfo(String json) {
        List<ViewPagerInfo> list = new ArrayList<>();
        try {
            JSONArray arry = new JSONArray(json);
            for (int i = 0; i < arry.length(); i++) {
                ViewPagerInfo info = new ViewPagerInfo();
                JSONObject obj = arry.getJSONObject(i);
                info.imgpath = obj.getString("imgpath");
                info._id = obj.getString("_id");
                list.add(info);
            }
        } catch (Exception e) {
        }
        return list;
    }


}
