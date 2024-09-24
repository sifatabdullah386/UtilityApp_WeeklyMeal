package com.smilesifat.weeklymeal.repository;

import com.smilesifat.weeklymeal.database.controller.WeekNameController;
import com.smilesifat.weeklymeal.model.WeekNameModel;
import com.smilesifat.weeklymeal.viewmodel.WeekDayViewModel;

import java.util.ArrayList;

public class WeekDayRepository {
    private ArrayList<WeekNameModel> weekNameModel;

    public void getWeekday(WeekNameController weekNameController, WeekDayViewModel weekDayViewModel) {
        if (weekNameController.getWeekNames() != null) {
            weekNameModel = weekNameController.getWeekNames();
        }

        weekDayViewModel.setDataList(weekNameModel);
    }
}
