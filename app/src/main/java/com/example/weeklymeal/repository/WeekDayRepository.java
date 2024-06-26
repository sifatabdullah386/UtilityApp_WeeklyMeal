package com.example.weeklymeal.repository;

import com.example.weeklymeal.database.controller.WeekNameController;
import com.example.weeklymeal.model.WeekNameModel;
import com.example.weeklymeal.viewmodel.WeekDayViewModel;

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
