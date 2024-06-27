package com.example.weeklymeal.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.weeklymeal.database.constants.ShoppingList;
import com.example.weeklymeal.database.constants.MealItems;
import com.example.weeklymeal.database.constants.FavouriteRecipes;
import com.example.weeklymeal.database.constants.WeekName;
import com.example.weeklymeal.database.constants.MealType;
import com.example.weeklymeal.database.controller.MealItemsController;
import com.example.weeklymeal.database.controller.MealTypeController;
import com.example.weeklymeal.database.controller.WeekNameController;
import com.example.weeklymeal.database.controller.ShoppingListController;

@SuppressWarnings("unchecked")
public class SQLiteHandler extends SQLiteOpenHelper {
    private static final String TAG = SQLiteHandler.class.getName();
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "weekly_meal";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create all tables from here
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WeekName.CREATE_WEEK_NAME);
        db.execSQL(MealType.CREATE_MEAL_TYPE);
        db.execSQL(MealItems.CREATE_MEAL_ITEMS);
        db.execSQL(ShoppingList.CREATE_SHOPPING_LIST);
        db.execSQL(FavouriteRecipes.CREATE_FAVOURITE_RECIPES);
        Log.d(TAG, "Database table created");

        //By Default Values
        WeekNameController.insertWeekDayByDefault(db);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + MealType.TABLE_MEAL_TYPE);
        db.execSQL("DROP TABLE IF EXISTS " + WeekName.TABLE_WEEK_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MealItems.TABLE_MEAL_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + ShoppingList.TABLE_SHOPPING_LIST);
        db.execSQL("DROP TABLE IF EXISTS " + FavouriteRecipes.TABLE_FAVOURITE_RECIPES);

        // Create tables again
        onCreate(db);
    }
}
