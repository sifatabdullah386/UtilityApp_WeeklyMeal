package com.example.weeklymeal.repository;

import android.util.Log;

import com.example.weeklymeal.database.controller.MealTypeController;
import com.example.weeklymeal.database.controller.WeekNameController;
import com.example.weeklymeal.model.MealTypeModel;
import com.example.weeklymeal.viewmodel.MealTypeViewModel;
import com.example.weeklymeal.viewmodel.WeekDayViewModel;

import java.util.ArrayList;

public class MealTypeRepository {

    public void getMealTypes(int week_id, MealTypeController mealTypeController, MealTypeViewModel mealTypeViewModel) {
        ArrayList<MealTypeModel> mealTypeModels = mealTypeController.getMealTypes(week_id);
        mealTypeViewModel.setDataList(mealTypeModels);
    }
}
