package com.example.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mym_0314 on 2016/5/12.
 */
public class DBhelper extends SQLiteOpenHelper {

   public DBhelper(Context ctx){
       super(ctx,"app.db",null,1);
   }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists user" +
                "(_id integer primary key autoincrement," +
                "name varchar(20)," +
                "sex varchar(20) check (sex in('男','女'))," +
                "age integer," +
                "date varchar(40))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
