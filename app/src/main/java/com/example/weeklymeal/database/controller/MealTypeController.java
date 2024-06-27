package com.example.weeklymeal.database.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.weeklymeal.database.SQLiteHandler;
import com.example.weeklymeal.database.constants.MealType;
import com.example.weeklymeal.model.MealTypeModel;

import java.util.ArrayList;

public class MealTypeController {
    private final SQLiteHandler sqLiteHandler;

    public MealTypeController(Context context) {
        sqLiteHandler = new SQLiteHandler(context);
    }

    public static void insertMealTypesByDefault(String week_id,SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        for (String itemName : MealType.DEFAULT_DATA) {
            values.put(MealType.KEY_WEEK_ID, week_id);
            values.put(MealType.KEY_TYPE_NAME, itemName);
            db.insert(MealType.TABLE_MEAL_TYPE, null, values);
        }

        Log.d("Default Meal type inserted into sqlite: ", String.valueOf(values));
    }

    public void addMealItems(int id, String name) {
        SQLiteDatabase db = sqLiteHandler.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MealType.KEY_MEAL_NAME, name);
        db.update(MealType.TABLE_MEAL_TYPE, cv, String.format("%s = ?", "id"), new String[]{String.valueOf(id)});
        db.close();
        Log.d("Meal Items information updated", String.valueOf(id));
    }

    public ArrayList<MealTypeModel> getMealTypes(int week_id) {
        SQLiteDatabase db = sqLiteHandler.getReadableDatabase();
        Cursor mealTypeList = db.rawQuery("SELECT * FROM " + MealType.TABLE_MEAL_TYPE + " WHERE " + MealType.KEY_WEEK_ID + " = ?", new String[]{String.valueOf(week_id)});
        ArrayList<MealTypeModel> mealTypeModel = new ArrayList<MealTypeModel>();

        if (mealTypeList.moveToFirst()) {
            do {
                mealTypeModel.add(new MealTypeModel(
                        mealTypeList.getInt(0),
                        mealTypeList.getString(1),
                        mealTypeList.getString(2),
                        mealTypeList.getString(3),
                        mealTypeList.getString(4)));
            } while (mealTypeList.moveToNext());
        }
        mealTypeList.close();
        Log.d("Meal Type List:", String.valueOf(mealTypeList));
        return mealTypeModel;
    }
}
