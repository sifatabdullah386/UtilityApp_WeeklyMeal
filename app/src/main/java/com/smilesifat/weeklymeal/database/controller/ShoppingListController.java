package com.smilesifat.weeklymeal.database.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.smilesifat.weeklymeal.database.SQLiteHandler;
import com.smilesifat.weeklymeal.database.constants.MealType;
import com.smilesifat.weeklymeal.database.constants.ShoppingList;
import com.smilesifat.weeklymeal.database.constants.WeekName;
import com.smilesifat.weeklymeal.model.ShoppingListModel;

import java.util.ArrayList;

public class ShoppingListController {
    private final SQLiteHandler sqLiteHandler;

    public ShoppingListController(Context context) {
        sqLiteHandler = new SQLiteHandler(context);
    }

    public void addShoppingList(String name) {
        SQLiteDatabase db = sqLiteHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ShoppingList.KEY_LIST_NAME, name);
        long insert_id = db.insert(ShoppingList.TABLE_SHOPPING_LIST, null, values);
        db.close();
        Log.d("New ShoppingList inserted into sqlite: ", String.valueOf(insert_id));
    }

    public ArrayList<ShoppingListModel> getShoppingList() {
        SQLiteDatabase db = sqLiteHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + ShoppingList.TABLE_SHOPPING_LIST, null);
        ArrayList<ShoppingListModel> shoppingList = new ArrayList<ShoppingListModel>();

        if (cursor.moveToFirst()) {
            do {
                shoppingList.add(new ShoppingListModel(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return shoppingList;
    }

    public void emptyShoppingList() {
        SQLiteDatabase db = sqLiteHandler.getWritableDatabase();
        db.delete(ShoppingList.TABLE_SHOPPING_LIST, null, null);
        db.close();
        Log.d("Deleted all shopping List info from sqlite", "");
    }

    public void updateShoppingList(int id, String name) {
        SQLiteDatabase db = sqLiteHandler.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ShoppingList.KEY_LIST_NAME, name);
        db.update(ShoppingList.TABLE_SHOPPING_LIST, cv, String.format("%s = ?", "id"), new String[]{String.valueOf(id)});
        db.close();
        Log.d("Shopping List information updated", String.valueOf(id));
    }

    public void shoppingListItemDelete(int id) {
        SQLiteDatabase db = sqLiteHandler.getWritableDatabase();
        String rowId = String.valueOf(id);
        db.delete(ShoppingList.TABLE_SHOPPING_LIST, ShoppingList.KEY_SHOPPING_ID + "=" + rowId, null);
        db.close();
        Log.d("Cart information deleted", "");
    }
}
