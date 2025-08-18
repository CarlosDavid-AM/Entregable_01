package com.example.entregable_e01.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.entregable_e01.Entity.Trash;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DBTrash extends DBHelper {

    Context context;

    public DBTrash(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public ArrayList<Trash> getAllTrash() {
        Trash trash = null;

        ArrayList<Trash> trashList = new ArrayList<>();

        DBHelper dbHelper = new DBHelper(context);

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_WASTE, null);

            while (cursor.moveToNext()) {
                trash = new Trash();
                trash.setId(cursor.getInt(0));
                trash.setWorkerName(cursor.getString(1));
                trash.setWasteType(cursor.getString(2));
                trash.setQuantity(cursor.getDouble(3));
                trash.setFecha(LocalDateTime.parse(cursor.getString(4)));

                trashList.add(trash);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return trashList;
    }

    public Trash getTrashById(int id) {
        Trash trash = null;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_WASTE + " WHERE id = ?", new String[]{String.valueOf(id)});

            if (cursor.moveToFirst()) {
                trash = new Trash();
                trash.setId(cursor.getInt(0));
                trash.setWorkerName(cursor.getString(1));
                trash.setWasteType(cursor.getString(2));
                trash.setQuantity(cursor.getDouble(3));
                trash.setFecha(LocalDateTime.parse(cursor.getString(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return trash;
    }

    public long addTrashById(Trash trash) {
        long getPk = 0;

        try {
            DBHelper dbHelper = new DBHelper(context);

            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put("workerName", trash.getWorkerName());
            values.put("wasteType", trash.getWasteType());
            values.put("quantity", trash.getQuantity());
            values.put("fecha", trash.getFecha().toString());

            getPk = db.insert(TABLE_WASTE, null, values);
        } catch (Exception e) {
            e.toString();
        }

        return getPk;
    }

    public int updateTrashById(Trash trash) {
        int recordsAffected = 0;
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            String[] parameters = {
                    String.valueOf(trash.getId())
            };

            ContentValues values = new ContentValues();

            values.put("workerName", trash.getWorkerName());
            values.put("wasteType", trash.getWasteType());
            values.put("quantity", trash.getQuantity());
            values.put("fecha", trash.getFecha().toString());

            recordsAffected = db.update(TABLE_WASTE, values, "id=?", parameters);
        } catch (Exception e) {
            e.toString();
        }

        return recordsAffected;
    }

    public void deleted(int id) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[]  parameters = { String.valueOf(id) };
        db.delete(TABLE_WASTE, "id=?", parameters);
        db.close();
    }
}
