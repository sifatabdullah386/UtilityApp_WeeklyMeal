package com.example.weeklymeal.database.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.weeklymeal.database.SQLiteHandler;
import com.example.weeklymeal.database.constants.FavouriteRecipes;
import com.example.weeklymeal.database.constants.MealItems;
import com.example.weeklymeal.model.MealItemModel;

import java.util.ArrayList;

public class MealItemsController {
    private final SQLiteHandler sqLiteHandler;

    public MealItemsController(Context context) {
        sqLiteHandler = new SQLiteHandler(context);
    }

    public void addMealItems(String id, String meal_type_id,String week_id,  String name) {
        SQLiteDatabase db = sqLiteHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MealItems.KEY_ITEM_ID, id);
        values.put(MealItems.KEY_MEAL_TYPE_ID, meal_type_id);
        values.put(MealItems.KEY_WEEK_ID, week_id);
        values.put(MealItems.KEY_ITEM_NAME, name);
        long insert_id = db.insert(MealItems.TABLE_MEAL_ITEMS, null, values);
        db.close();
        Log.d("New Meal Items inserted into sqlite: ", String.valueOf(insert_id));
    }

    public ArrayList<MealItemModel> getAllMealItems() {
        SQLiteDatabase db = sqLiteHandler.getReadableDatabase();
        Cursor mealItems = db.rawQuery("SELECT * FROM " + MealItems.TABLE_MEAL_ITEMS, null);
        ArrayList<MealItemModel> mealItemModel = new ArrayList<MealItemModel>();

        if (mealItems.moveToFirst()) {
            do {
                mealItemModel.add(new MealItemModel(
                        mealItems.getInt(0),
                        mealItems.getString(1),
                        mealItems.getString(2),
                        mealItems.getString(3)));
            } while (mealItems.moveToNext());
        }
        mealItems.close();

        Log.d("Meal Item List", mealItems.toString());

        return mealItemModel;
    }

    public void emptyMealItems() {
        SQLiteDatabase db = sqLiteHandler.getWritableDatabase();
        db.delete(MealItems.TABLE_MEAL_ITEMS, null, null);
        db.close();
        Log.d("Deleted all meal items from sqlite", "");
    }

    public void updateMealItems(int id, String name) {
        SQLiteDatabase db = sqLiteHandler.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MealItems.KEY_ITEM_NAME, name);
        db.update(MealItems.TABLE_MEAL_ITEMS, cv, String.format("%s = ?", "id"), new String[]{String.valueOf(id)});
        db.close();
        Log.d("Meal Items information updated", String.valueOf(id));
    }

    public void mealItemDelete(int id) {
        SQLiteDatabase db = sqLiteHandler.getWritableDatabase();
        String rowId = String.valueOf(id);
        db.delete(MealItems.TABLE_MEAL_ITEMS, MealItems.KEY_ITEM_ID + "=" + rowId, null);
        db.close();
        Log.d("Meal Items information deleted", "");
    }
}
