package com.example.weeklymeal.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weeklymeal.R;
import com.example.weeklymeal.database.controller.WeekNameController;
import com.example.weeklymeal.model.WeekNameModel;
import com.example.weeklymeal.repository.WeekDayRepository;
import com.example.weeklymeal.utilities.Constants;
import com.example.weeklymeal.utilities.ExpandableHeightGridView;
import com.example.weeklymeal.utilities.SessionManager;
import com.example.weeklymeal.viewmodel.WeekDayViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView ivSettings;
    RecyclerView weekDaysRecyclerView;
    ExpandableHeightGridView mealTypeGridView;
    SessionManager sessionManager;
    WeekDayRepository weekDayRepository;
    WeekNameController weekNameController;
    WeekDayViewModel weekDayViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set status bar background color
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorBackground));

        // Set status bar content to dark (black) icons
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        sessionManager = new SessionManager(this);
        weekDayRepository = new WeekDayRepository();
        weekNameController=new WeekNameController(this);
        weekDayViewModel=new WeekDayViewModel();

        // All Reference Ids of xml
        ivSettings = findViewById(R.id.iv_settings);
        weekDaysRecyclerView = findViewById(R.id.rv_week_name);
        mealTypeGridView = findViewById(R.id.gv_meal_type_list);

        weekDaysRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        getWeekDays();

        // All Click Events
        ivSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sI = new Intent(MainActivity.this, Settings.class);
                startActivity(sI);
            }
        });
    }

    private void getWeekDays() {
        Log.d("WeekID", sessionManager.getValue(Constants.WEEK_ID));
        weekDayRepository.getWeekday( weekNameController,weekDayViewModel);
        weekDayViewModel.getDataList().observe(this, new Observer<ArrayList<WeekNameModel>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(ArrayList<WeekNameModel> receivedDataList) {
//                roomCategoryAdapter = new RoomCategoryAdapter(getContext(), receivedDataList, roomCategoryListeners);
//                categoryRecyclerView.setAdapter(roomCategoryAdapter);
//                roomCategoryAdapter.notifyDataSetChanged();
            }
        });
    }
}