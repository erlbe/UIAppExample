package com.example.telematica.uiappexample.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Erlend on 18.11.2016.
 */

public class BookDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "BookDB";
    private static final int DB_version = 1;

    String sqlString = "CREATE TABLE 'books' ('id' INTEGER PRIMARY KEY, 'name' TEXT)";

    public BookDatabase(Context context) {
        super(context, DB_NAME, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlString);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS 'books");
        onCreate(db);
    }
}
