package com.smilesifat.weeklymeal.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smilesifat.weeklymeal.R;
import com.smilesifat.weeklymeal.listeners.MealTypeListeners;
import com.smilesifat.weeklymeal.model.MealTypeModel;

import java.util.ArrayList;

public class MealTypeAdapter extends BaseAdapter {
    Context context;
    ArrayList<MealTypeModel> mealTypeModel;
    MealTypeListeners mealTypeListeners;
    TextView mealTypeName, mealItemName;
    ImageView mealTypeIcon, addItemIcon, favouriteIcon;
    int weekId;

    public MealTypeAdapter(Context context, ArrayList<MealTypeModel> mealTypeModel, MealTypeListeners mealTypeListeners, int weekId) {
        this.context = context;
        this.mealTypeModel = mealTypeModel;
        this.mealTypeListeners = mealTypeListeners;
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

    @SuppressLint({"ViewHolder", "SetTextI18n"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.card_view_meal_type, parent, false);

        mealTypeName = convertView.findViewById(R.id.tv_meal_type_name);
        mealItemName = convertView.findViewById(R.id.tv_add_meal);
        mealTypeIcon = convertView.findViewById(R.id.iv_meal_type_icon);
        addItemIcon = convertView.findViewById(R.id.iv_add_meal_item);
        favouriteIcon = convertView.findViewById(R.id.iv_favourite);

        mealTypeName.setText(mealTypeModel.get(position).getName());

        if (String.valueOf(mealTypeModel.get(position).getMeal_name()).equals("null")) {
            mealItemName.setText("Add Meal");
            mealItemName.setTextColor(context.getColor(R.color.shimmer_color));
        } else {
            mealItemName.setText(mealTypeModel.get(position).getMeal_name());
        }

        addItemIcon.setOnClickListener(view -> mealTypeListeners.onAddMealClick(mealTypeModel.get(position).getId(), weekId, mealTypeModel.get(position).getName()));
        favouriteIcon.setOnClickListener(view -> mealTypeListeners.onAddFavouriteClick(mealTypeModel.get(position).getId(), mealTypeModel.get(position).getMeal_name()));
        mealItemName.setOnClickListener(view -> mealTypeListeners.updateMealClick(mealTypeModel.get(position).getId(), weekId, mealTypeModel.get(position).getName(),mealTypeModel.get(position).getMeal_name()));

        return convertView;
    }
}