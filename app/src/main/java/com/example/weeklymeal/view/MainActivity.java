package com.example.weeklymeal.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weeklymeal.R;
import com.example.weeklymeal.adapter.MealTypeAdapter;
import com.example.weeklymeal.adapter.WeekDayAdapter;
import com.example.weeklymeal.database.controller.FavouriteController;
import com.example.weeklymeal.database.controller.MealTypeController;
import com.example.weeklymeal.database.controller.WeekNameController;
import com.example.weeklymeal.listeners.MealTypeListeners;
import com.example.weeklymeal.listeners.WeekDayListeners;
import com.example.weeklymeal.model.MealTypeModel;
import com.example.weeklymeal.model.WeekNameModel;
import com.example.weeklymeal.repository.MealTypeRepository;
import com.example.weeklymeal.repository.WeekDayRepository;
import com.example.weeklymeal.utilities.ExpandableHeightGridView;
import com.example.weeklymeal.viewmodel.MealTypeViewModel;
import com.example.weeklymeal.viewmodel.WeekDayViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ImageView ivSettings;
    RecyclerView weekDaysRecyclerView;
    ExpandableHeightGridView mealTypeGridView;
    WeekDayRepository weekDayRepository;
    MealTypeRepository mealTypeRepository;
    WeekNameController weekNameController;
    MealTypeController mealTypeController;
    FavouriteController favouriteController;
    WeekDayViewModel weekDayViewModel;
    MealTypeViewModel mealTypeViewModel;
    WeekDayAdapter weekDayAdapter;
    MealTypeAdapter mealTypeAdapter;
    WeekDayListeners weekDayListeners;
    MealTypeListeners mealTypeListeners;
    Animation scaleUp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set status bar background color
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorBackground));

        // Set status bar content to dark (black) icons
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        weekDayRepository = new WeekDayRepository();
        mealTypeRepository = new MealTypeRepository();
        weekNameController = new WeekNameController(this);
        mealTypeController = new MealTypeController(this);
        favouriteController = new FavouriteController(this);
        weekDayViewModel = new WeekDayViewModel();
        mealTypeViewModel = new MealTypeViewModel();

        // Load the animation
        scaleUp = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale_up);

        // Method Call for different kinds of clicks
        weekDayListeners = (pos, name) -> {
            getMealTypes(pos);
            mealTypeGridView.startAnimation(scaleUp);
        };

        mealTypeListeners = new MealTypeListeners() {
            @Override
            public void onAddMealClick(int pos, int weekId, String name) {
                addMealById(MainActivity.this, String.valueOf(pos), String.valueOf(weekId), name);
            }

            @Override
            public void onAddFavouriteClick(int pos, String mealItem) {
                addFavouriteItem(MainActivity.this, String.valueOf(pos), mealItem);
            }

            @Override
            public void updateMealClick(int pos, int weekId, String name, String mealItem) {
                updateMealItemById(MainActivity.this, pos, weekId, name, mealItem);
            }
        };

        // All Reference Ids of xml
        ivSettings = findViewById(R.id.iv_settings);
        weekDaysRecyclerView = findViewById(R.id.rv_week_name);
        mealTypeGridView = findViewById(R.id.gv_meal_type_list);
        mealTypeGridView = (ExpandableHeightGridView) findViewById(R.id.gv_meal_type_list);

        weekDaysRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        getWeekDays();
        getMealTypes(1);

        // All Click Events
        ivSettings.setOnClickListener(v -> {
            Intent sI = new Intent(MainActivity.this, Settings.class);
            startActivity(sI);
        });
    }

    private void getMealTypes(int week_id) {
        Log.d("WeekId", String.valueOf(week_id));
        mealTypeRepository.getMealTypes(week_id, mealTypeController, mealTypeViewModel);
        mealTypeViewModel.getDataList().observe(this, new Observer<ArrayList<MealTypeModel>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(ArrayList<MealTypeModel> receivedDataList) {
                if (receivedDataList.isEmpty()) {
                    Log.d("Meal Type List", "No Data Found");
                } else {
                    Log.d("Meal Type List", receivedDataList.get(0).getName());
                    mealTypeAdapter = new MealTypeAdapter(MainActivity.this, receivedDataList, mealTypeListeners, week_id);
                    mealTypeGridView.setAdapter(mealTypeAdapter);
                    mealTypeGridView.setExpanded(true);
                    mealTypeAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void getWeekDays() {
        weekDayRepository.getWeekday(weekNameController, weekDayViewModel);
        weekDayViewModel.getDataList().observe(this, new Observer<ArrayList<WeekNameModel>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(ArrayList<WeekNameModel> receivedDataList) {
                weekDayAdapter = new WeekDayAdapter(MainActivity.this, receivedDataList, weekDayListeners);
                weekDaysRecyclerView.setAdapter(weekDayAdapter);
                weekDayAdapter.notifyDataSetChanged();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void addMealById(Context context, String mealType, String weekId, String name) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_add_meal);

        EditText addMeal;
        TextView title, cancel, save;

        addMeal = dialog.findViewById(R.id.et_add_meal);
        title = dialog.findViewById(R.id.tv_text_title);
        cancel = dialog.findViewById(R.id.tv_cancel);
        save = dialog.findViewById(R.id.tv_save);

        title.setText("Add a Meal/" + name);

        save.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                mealTypeController.addMealItems(Integer.parseInt(mealType), addMeal.getText().toString().trim());
                dialog.dismiss();
                Toast.makeText(context, "Successfully " + name + " meal added.", Toast.LENGTH_LONG).show();
                getMealTypes(Integer.parseInt(weekId));
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @SuppressLint("SetTextI18n")
    private void addFavouriteItem(Context context, String mealType, String mealItem) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_add_meal);

        EditText addRecipe;
        TextView title, cancel, save;

        addRecipe = dialog.findViewById(R.id.et_add_meal);
        title = dialog.findViewById(R.id.tv_text_title);
        cancel = dialog.findViewById(R.id.tv_cancel);
        save = dialog.findViewById(R.id.tv_save);

        title.setText("Add a Favourite Recipe");
        if (String.valueOf(mealItem).equals("null")) {
            addRecipe.setText("Add your Favourite Recipe");
        } else {
            addRecipe.setText(mealItem);
        }


        save.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                favouriteController.addFavourites(mealType, addRecipe.getText().toString().trim());
                dialog.dismiss();
                Toast.makeText(context, "Successfully favourite recipe added.", Toast.LENGTH_LONG).show();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @SuppressLint("SetTextI18n")
    private void updateMealItemById(Context context, int mealType, int weekId, String name, String mealItem) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_add_meal);

        EditText addMeal;
        TextView title, cancel, update;

        addMeal = dialog.findViewById(R.id.et_add_meal);
        title = dialog.findViewById(R.id.tv_text_title);
        cancel = dialog.findViewById(R.id.tv_cancel);
        update = dialog.findViewById(R.id.tv_save);

        title.setText("Update Meal Item/" + name);
        update.setText("Update");

        if (String.valueOf(mealItem).equals("null")) {
            addMeal.setHint("Add Meal");
        } else {
            addMeal.setText(mealItem);
        }

        update.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                mealTypeController.addMealItems(mealType, addMeal.getText().toString().trim());
                dialog.dismiss();
                Toast.makeText(context, "Successfully " + name + " meal updated.", Toast.LENGTH_LONG).show();
                getMealTypes(weekId);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}