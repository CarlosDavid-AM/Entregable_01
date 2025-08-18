package com.example.entregable_e01.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "collection";
    public static final String TABLE_WASTE = "waste";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_WASTE + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "workerName TEXT NOT NULL," +
                "wasteType TEXT NOT NULL," +
                "quantity REAL NOT NULL," +
                "fecha TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_WASTE);
        onCreate(db);
    }
}
