package com.example.mInterface;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by mym_0314 on 2016/5/12.
 */
public interface DBInterface {

    int insert(String dbName, ContentValues values);

    int delete(String dbName);

    Cursor query(String dbName);

    void update(String dbName);
}
