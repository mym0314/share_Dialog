package com.example.testdemo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mym_0314 on 2016/5/18.
 * private String _id;
 * private String imgpath;
 * private String advertId;
 */
public class JsonUtils {

    public static List getJsonFromStr(String json) throws JSONException {
        JSONArray arr = new JSONArray(json);
        List<Bean> mList = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {

            JSONObject obj = arr.getJSONObject(i);
            String _id = obj.getString("_id");
            String imgpath = obj.getString("imgpath");
            String advertId = obj.getString("advertId");
            Bean bean = new Bean(_id, imgpath, advertId);
            mList.add(bean);
        }
        return mList;
    }
}
