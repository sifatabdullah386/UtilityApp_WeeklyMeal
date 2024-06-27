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

//    public void addMealTypes(String name, MealTypeModel mealTypeModel) {
//        SQLiteDatabase db = sqLiteHandler.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(MealType.KEY_HOME_ID, mealTypeModel.getHome_id());
//        values.put(MealType.KEY_ROOM_ID, mealTypeModel.getRoom_id());
//        values.put(MealType.KEY_DEVICE_ID, mealTypeModel.getDevice_id());
//        values.put(MealType.KEY_USER_ID, mealTypeModel.getUser_id());
//        values.put(MealType.KEY_EVENT_TYPE, mealTypeModel.getMealType_type());
//        values.put(MealType.KEY_DESCRIPTION, mealTypeModel.getDescription());
//        long insert_id = db.insert(MealTypes.TABLE_EVENTS, null, values);
//        db.close();
//        Log.d("New MealType inserted into sqlite: ", String.valueOf(insert_id));
//    }

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
                        mealTypeList.getString(3)));
            } while (mealTypeList.moveToNext());
        }
        mealTypeList.close();
        Log.d("Meal Type List:", String.valueOf(mealTypeList));
        return mealTypeModel;
    }

//    public void emptyMealTypes() {
//        SQLiteDatabase db = sqLiteHandler.getWritableDatabase();
//        db.delete(MealType.TABLE_MEAL_TYPE, null, null);
//        db.close();
//        Log.d("Deleted all mealTypes from sqlite", "");
//    }

//    public void updateMealTypes(int id, String device_id, String mealType_type) {
//        SQLiteDatabase db = sqLiteHandler.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(MealType.KEY_DEVICE_ID, device_id);
//        cv.put(MealType.KEY_EVENT_TYPE, mealType_type);
//        db.update(MealType.TABLE_MEAL_TYPE, cv, String.format("%s = ?", "id"), new String[]{String.valueOf(id)});
//        db.close();
//        Log.d("MealType information updated", String.valueOf(id));
//    }
//
//    public void mealTypeItemDelete(int id) {
//        SQLiteDatabase db = sqLiteHandler.getWritableDatabase();
//        String rowId = String.valueOf(id);
//        db.delete(MealType.TABLE_MEAL_TYPE, MealType.KEY_ID + "=" + rowId, null);
//        db.close();
//        Log.d("MealType information deleted", "");
//    }
}
