package com.smilesifat.weeklymeal.repository;

import android.util.Log;

import com.smilesifat.weeklymeal.database.controller.MealTypeController;
import com.smilesifat.weeklymeal.database.controller.WeekNameController;
import com.smilesifat.weeklymeal.model.MealTypeModel;
import com.smilesifat.weeklymeal.viewmodel.MealTypeViewModel;
import com.smilesifat.weeklymeal.viewmodel.WeekDayViewModel;

import java.util.ArrayList;

public class MealTypeRepository {

    public void getMealTypes(int week_id, MealTypeController mealTypeController, MealTypeViewModel mealTypeViewModel) {
        ArrayList<MealTypeModel> mealTypeModels = mealTypeController.getMealTypes(week_id);
        mealTypeViewModel.setDataList(mealTypeModels);
    }
}
