package com.example.weeklymeal.database.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.weeklymeal.database.SQLiteHandler;
import com.example.weeklymeal.database.constants.FavouriteRecipes;
import com.example.weeklymeal.model.FavouriteModel;

import java.util.ArrayList;

public class FavouriteController {
    private final SQLiteHandler sqLiteHandler;

    public FavouriteController(Context context) {
        sqLiteHandler = new SQLiteHandler(context);
    }

    public void addFavourites(String id, String meal_type_id, String name) {
        SQLiteDatabase db = sqLiteHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FavouriteRecipes.KEY_FAVOURITE_ID, id);
        values.put(FavouriteRecipes.KEY_MEAL_ID, meal_type_id);
        values.put(FavouriteRecipes.KEY_FAVOURITE_NAME, name);
        long insert_id = db.insert(FavouriteRecipes.TABLE_FAVOURITE_RECIPES, null, values);
        db.close();
        Log.d("New Favourite inserted into sqlite: ", String.valueOf(insert_id));
    }

    public ArrayList<FavouriteModel> getAllFavourites() {
        SQLiteDatabase db = sqLiteHandler.getReadableDatabase();
        Cursor favouriteList = db.rawQuery("SELECT * FROM " + FavouriteRecipes.TABLE_FAVOURITE_RECIPES, null);
        ArrayList<FavouriteModel> favouriteModel = new ArrayList<FavouriteModel>();

        if (favouriteList.moveToFirst()) {
            do {
                favouriteModel.add(new FavouriteModel(
                        favouriteList.getInt(0),
                        favouriteList.getString(1),
                        favouriteList.getString(2)));
            } while (favouriteList.moveToNext());
        }

        favouriteList.close();

        Log.d("All Favourite List", favouriteList.toString());

        return favouriteModel;
    }

    public ArrayList<FavouriteModel> getFavouritesByMealId(int meal_id) {
        SQLiteDatabase db = sqLiteHandler.getReadableDatabase();
        Cursor favouriteList = db.rawQuery("SELECT * FROM " + FavouriteRecipes.TABLE_FAVOURITE_RECIPES + " WHERE " + FavouriteRecipes.KEY_MEAL_ID + " = ?", new String[]{String.valueOf(meal_id)});
        ArrayList<FavouriteModel> favouriteModel = new ArrayList<FavouriteModel>();

        if (favouriteList.moveToFirst()) {
            do {
                favouriteModel.add(new FavouriteModel(
                        favouriteList.getInt(0),
                        favouriteList.getString(1),
                        favouriteList.getString(2)));
            } while (favouriteList.moveToNext());
        }

        favouriteList.close();

        Log.d("Favourite List", favouriteList.toString());

        return favouriteModel;
    }

//    public int countFavouritesByMealId(int meal_id) {
//        SQLiteDatabase db = sqLiteHandler.getReadableDatabase();
//        Cursor cursor = null;
//        int rowCount = 0;
//
//        try {
//            // Execute query to count rows with the specified ID
//            String countQuery = "SELECT COUNT(*) FROM " + FavouriteRecipes.TABLE_FAVOURITE_RECIPES + " WHERE " + FavouriteRecipes.KEY_MEAL_ID + " = ?";
//            cursor = db.rawQuery(countQuery, new String[]{String.valueOf(room_id)});
//
//            // Extract the count from the cursor
//            if (cursor.moveToFirst()) {
//                rowCount = cursor.getInt(0);
//            }
//        } finally {
//            // Close the cursor and database
//            if (cursor != null) {
//                cursor.close();
//            }
//            db.close();
//        }
//
//        Log.d("Num of Favourites", String.valueOf(rowCount));
//
//        return rowCount;
//    }

    public void emptyAllFavourites() {
        SQLiteDatabase db = sqLiteHandler.getWritableDatabase();
        db.delete(FavouriteRecipes.TABLE_FAVOURITE_RECIPES, null, null);
        db.close();
        Log.d("Deleted all favourites info from sqlite", "");
    }

//    public void updateFavouriteStatus(int id, int status) {
//        SQLiteDatabase db = sqLiteHandler.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(FavouriteRecipes.KEY_STATUS, status);
//        db.update(FavouriteRecipes.TABLE_FAVOURITE_RECIPES, cv, String.format("%s = ?", "id"), new String[]{String.valueOf(id)});
//        db.close();
//        Log.d("Favourite status updated", String.valueOf(id));
//    }

    public void updateFavouriteById(int id, String meal_id, String name) {
        SQLiteDatabase db = sqLiteHandler.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FavouriteRecipes.KEY_MEAL_ID, meal_id);
        cv.put(FavouriteRecipes.KEY_FAVOURITE_NAME, name);
        db.update(FavouriteRecipes.TABLE_FAVOURITE_RECIPES, cv, String.format("%s = ?", "id"), new String[]{String.valueOf(id)});
        db.close();
        Log.d("Favourite information updated", String.valueOf(id));
    }

    public void favouriteItemDelete(int id) {
        SQLiteDatabase db = sqLiteHandler.getWritableDatabase();
        String rowId = String.valueOf(id);
        db.delete(FavouriteRecipes.TABLE_FAVOURITE_RECIPES, FavouriteRecipes.KEY_FAVOURITE_ID + "=" + rowId, null);
        db.close();
        Log.d("Favourite information deleted", "");
    }
}
