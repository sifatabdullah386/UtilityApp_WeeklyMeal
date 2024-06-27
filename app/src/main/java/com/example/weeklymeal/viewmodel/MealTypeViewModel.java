package com.example.weeklymeal.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weeklymeal.model.MealTypeModel;
import com.example.weeklymeal.model.WeekNameModel;

import java.util.ArrayList;

public class MealTypeViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<MealTypeModel>> dataList = new MutableLiveData<>();

    public void setDataList(ArrayList<MealTypeModel> data) {
        dataList.setValue(data);
    }

    public LiveData<ArrayList<MealTypeModel>> getDataList() {
        return dataList;
    }

}
