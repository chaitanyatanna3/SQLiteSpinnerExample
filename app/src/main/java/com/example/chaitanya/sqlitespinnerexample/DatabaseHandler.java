package com.example.chaitanya.sqlitespinnerexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by chaitanyatanna on 3/8/16.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    //database version
    private static final int DATABASE_VERSION = 1;

    //database name
    private static final String DATABASE_NAME = "spinnerExample";

    //table name
    private static final String TABLE_LABELS = "labels";

    //column names
    private static final String KEY_ID = "Lid";
    private static final String KEY_NAME = "name";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creating tables

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_LABELS
                 + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME
                + " TEXT)";
        db.execSQL(CREATE_CATEGORIES_TABLE);
    }

    //upgrading database

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LABELS);

        onCreate(db);
    }

    //inserting new label
    public void insertLabel(String label) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, label);

        //inserting new row
        db.insert(TABLE_LABELS, null, values);
        db.close();
    }

    //get all labels
    public List<String> getAllLabels() {
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " +TABLE_LABELS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return labels;
    }
}
