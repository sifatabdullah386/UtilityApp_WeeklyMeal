package com.example.weeklymeal.database.constants;

public class MealItems {
    // Meal Items Table name
    public static final String TABLE_MEAL_ITEMS = "meal_items";

    // Meal Items Column names
    public static final String KEY_ITEM_ID = "id";
    public static final String KEY_WEEK_ID = "week_id";
    public static final String KEY_MEAL_TYPE_ID = "meal_id";
    public static final String KEY_ITEM_NAME = "name";

    public static String CREATE_MEAL_ITEMS = "CREATE TABLE " +
            TABLE_MEAL_ITEMS + "(" +
            KEY_ITEM_ID + " INTEGER PRIMARY KEY," +
            KEY_WEEK_ID + " TEXT," +
            KEY_MEAL_TYPE_ID + " TEXT," +
            KEY_ITEM_NAME + " TEXT" + ")";
}
