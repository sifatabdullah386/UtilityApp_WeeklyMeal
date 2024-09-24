package com.smilesifat.weeklymeal.database.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.smilesifat.weeklymeal.database.SQLiteHandler;
import com.smilesifat.weeklymeal.database.constants.FavouriteRecipes;
import com.smilesifat.weeklymeal.model.FavouriteModel;

import java.util.ArrayList;

public class FavouriteController {
    private final SQLiteHandler sqLiteHandler;

    public FavouriteController(Context context) {
        sqLiteHandler = new SQLiteHandler(context);
    }

    public void addFavourites(String meal_type_id, String name) {
        SQLiteDatabase db = sqLiteHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put(FavouriteRecipes.KEY_FAVOURITE_ID, id);
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

    public void emptyAllFavourites() {
        SQLiteDatabase db = sqLiteHandler.getWritableDatabase();
        db.delete(FavouriteRecipes.TABLE_FAVOURITE_RECIPES, null, null);
        db.close();
        Log.d("Deleted all favourites info from sqlite", "");
    }
}
