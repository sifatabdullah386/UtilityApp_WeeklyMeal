package com.example.weeklymeal.database.constants;

public class WeekName {
    // Week Name table
    public static final String TABLE_WEEK_NAME = "week_name";

    // Week Name columns
    public static final String KEY_DAY_ID = "id";
    public static final String KEY_DAY_NAME = "name";

    public static String CREATE_WEEK_NAME = "CREATE TABLE " +
            TABLE_WEEK_NAME + "(" +
            KEY_DAY_ID + " INTEGER PRIMARY KEY," +
            KEY_DAY_NAME + " TEXT" + ")";

    public static final String[] DEFAULT_DATA = {
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday",
            "Sunday"
    };
}
