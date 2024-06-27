package com.example.weeklymeal.database.controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.weeklymeal.database.SQLiteHandler;
import com.example.weeklymeal.database.constants.WeekName;
import com.example.weeklymeal.model.WeekNameModel;
import com.example.weeklymeal.utilities.Constants;

import java.util.ArrayList;

public class WeekNameController {
    private final SQLiteHandler sqLiteHandler;
    @SuppressLint("StaticFieldLeak")

    public WeekNameController(Context context) {
        sqLiteHandler = new SQLiteHandler(context);
    }

    // Insert week day name dy Default
    public static void insertWeekDayByDefault(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        for (String itemName : WeekName.DEFAULT_DATA) {
            values.put(WeekName.KEY_DAY_NAME, itemName);
            long week_id = db.insert(WeekName.TABLE_WEEK_NAME, null, values);

            //Insert meal type name by default
            MealTypeController.insertMealTypesByDefault(String.valueOf(week_id), db);
        }

        Log.d("Default Week Name inserted into sqlite: ", String.valueOf(values));
    }

    // Get week name
    public ArrayList<WeekNameModel> getWeekNames() {
        SQLiteDatabase db = sqLiteHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + WeekName.TABLE_WEEK_NAME, null);
        ArrayList<WeekNameModel> weekNameModel = new ArrayList<WeekNameModel>();

        if (cursor.moveToFirst()) {
            do {
                weekNameModel.add(new WeekNameModel(
                        cursor.getInt(0),
                        cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return weekNameModel;
    }
}
