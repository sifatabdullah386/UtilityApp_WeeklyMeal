package com.example.weeklymeal.listeners;

public interface MealTypeListeners {
    void onAddMealClick(int pos, int weekId, String name);

    void onAddFavouriteClick(int pos, String mealItem);

    void updateMealClick(int pos, int weekId, String name, String mealItem);
}
