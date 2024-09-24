package com.smilesifat.weeklymeal.database.constants;

public class FavouriteRecipes {

    // Favourite Recipes table name
    public static final String TABLE_FAVOURITE_RECIPES = "favourite_recipes";

    // Event Column names
    public static final String KEY_FAVOURITE_ID = "id";
    public static final String KEY_MEAL_ID = "meal_id";
    public static final String KEY_FAVOURITE_NAME = "name";

    public static String CREATE_FAVOURITE_RECIPES = "CREATE TABLE " +
            TABLE_FAVOURITE_RECIPES + "(" +
            KEY_FAVOURITE_ID + " INTEGER PRIMARY KEY," +
            KEY_MEAL_ID + " TEXT," +
            KEY_FAVOURITE_NAME + " TEXT" + ")";
}
