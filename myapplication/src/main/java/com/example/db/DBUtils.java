package com.example.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bean.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by mym_0314 on 2016/5/12.
 */
public class DBUtils {
    private SQLiteDatabase db;
    private DBhelper su;
    private Context ctx;

    public DBUtils(Context ctx) {
        this.ctx = ctx;
        su = new DBhelper(ctx);
//      db = ctx.openOrCreateDatabase("com.example.", Context.MODE_PRIVATE, null);

    }

    public void saveData(String name, User user) {
        db = su.getWritableDatabase();
        db.beginTransaction();
        ContentValues values = new ContentValues();

        values.put("name", user.getName());
        values.put("sex", user.getSex());
        values.put("age", user.getAge());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String date = sdf.format(user.getDate());
        values.put("date", date);
        db.beginTransaction();
        db.insert(name, null, values);
        db.setTransactionSuccessful();
        db.close();
        //db.insert("name",)
    }

    public void queryDate(String sname) {

        db = su.getReadableDatabase();
        db.beginTransaction();
        Cursor cursor = db.query(sname, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String sex = cursor.getString(cursor.getColumnIndex("sex"));
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            System.out.println(_id);
            System.out.println(name);
            System.out.println(age);
            System.out.println(date);
            System.out.println(sex);
        }
        db.setTransactionSuccessful();
    }

    public void close() {
        if (su != null)
            su = null;
        if (db != null)
            db.close();

    }

}
