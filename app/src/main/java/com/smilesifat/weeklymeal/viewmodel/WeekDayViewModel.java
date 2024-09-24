package com.smilesifat.weeklymeal.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.smilesifat.weeklymeal.model.WeekNameModel;

import java.util.ArrayList;

public class WeekDayViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<WeekNameModel>> dataList = new MutableLiveData<>();

    public void setDataList(ArrayList<WeekNameModel> data) {
        dataList.setValue(data);
    }

    public LiveData<ArrayList<WeekNameModel>> getDataList() {
        return dataList;
    }

}
