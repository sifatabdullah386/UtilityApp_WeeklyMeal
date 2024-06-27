package com.example.weeklymeal.listeners;

public interface MealTypeListeners {
    void onAddMealClick(int pos,int weekId, String name);
    void updateMealClick(int pos, String name);
}
