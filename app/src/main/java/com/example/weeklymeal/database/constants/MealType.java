package com.example.weeklymeal.database.constants;

public class MealType {

    //Meal Type Table Name
    public static final String TABLE_MEAL_TYPE = "meal_type";

    // Meal Type Column names
    public static final String KEY_TYPE_ID = "id";
    public static final String KEY_WEEK_ID = "week_id";
    public static final String KEY_TYPE_NAME = "name";
    public static final String KEY_ICON = "icon";

    public static String CREATE_MEAL_TYPE = "CREATE TABLE " +
            TABLE_MEAL_TYPE + "(" +
            KEY_TYPE_ID + " INTEGER PRIMARY KEY," +
            KEY_WEEK_ID + " TEXT," +
            KEY_TYPE_NAME + " TEXT," +
            KEY_ICON + " TEXT" + ")";
    public static final String[] DEFAULT_DATA = {
            "Breakfast",
            "Lunch",
            "Snacks",
            "Dinner"
    };
}
