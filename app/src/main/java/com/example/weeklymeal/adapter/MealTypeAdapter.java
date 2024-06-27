package com.example.weeklymeal.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weeklymeal.R;
import com.example.weeklymeal.database.controller.MealItemsController;
import com.example.weeklymeal.listeners.MealTypeListeners;
import com.example.weeklymeal.model.MealTypeModel;

import java.util.ArrayList;
import java.util.Objects;

public class MealTypeAdapter extends BaseAdapter {
    Context context;
    MealItemsController mealItemsController;
    ArrayList<MealTypeModel> mealTypeModel;
    MealTypeListeners mealTypeListeners;
    TextView mealTypeName, mealItemName;
    ImageView mealTypeIcon, addItemIcon;
    int weekId;

    public MealTypeAdapter(Context context, ArrayList<MealTypeModel> mealTypeModel, MealTypeListeners mealTypeListeners, MealItemsController mealItemsController, int weekId) {
        this.context = context;
        this.mealTypeModel = mealTypeModel;
        this.mealTypeListeners = mealTypeListeners;
        this.mealItemsController = mealItemsController;
        this.weekId = weekId;
    }

    @Override
    public int getCount() {
        return mealTypeModel.size();
    }

    @Override
    public Object getItem(int position) {
        return mealTypeModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.card_view_meal_type, parent, false);

        mealTypeName = convertView.findViewById(R.id.tv_meal_type_name);
        mealItemName = convertView.findViewById(R.id.tv_add_meal);
        mealTypeIcon = convertView.findViewById(R.id.iv_meal_type_icon);
        addItemIcon = convertView.findViewById(R.id.iv_add_meal_item);

        mealTypeName.setText(mealTypeModel.get(position).getName());

        if(!Objects.equals(mealTypeModel.get(position).getName(), "")){
            mealItemName.setText(mealItemsController.getMealItemById(position));
        }

        addItemIcon.setOnClickListener(view -> mealTypeListeners.onAddMealClick(mealTypeModel.get(position).getId(), weekId, mealTypeModel.get(position).getName()));
        mealItemName.setOnClickListener(view -> mealTypeListeners.updateMealClick(mealTypeModel.get(position).getId(), mealTypeModel.get(position).getName()));

        return convertView;
    }
}