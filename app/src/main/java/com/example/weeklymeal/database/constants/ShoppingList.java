package com.example.weeklymeal.database.constants;

public class ShoppingList {
    // Shopping List table name
    public static final String TABLE_SHOPPING_LIST = "device_type";

    // Shopping List Column names
    public static final String KEY_SHOPPING_ID = "id";
    public static final String KEY_LIST_NAME = "name";
    public static final String KEY_DESCRIPTION = "description";

    public static String CREATE_SHOPPING_LIST = "CREATE TABLE " +
            TABLE_SHOPPING_LIST + "(" +
            KEY_SHOPPING_ID + " INTEGER PRIMARY KEY," +
            KEY_LIST_NAME + " TEXT," +
            KEY_DESCRIPTION + " TEXT" + ")";
}
