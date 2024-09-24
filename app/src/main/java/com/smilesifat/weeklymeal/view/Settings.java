package com.smilesifat.weeklymeal.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.smilesifat.weeklymeal.R;
import com.smilesifat.weeklymeal.database.controller.MealTypeController;

import java.util.Objects;

public class Settings extends AppCompatActivity {

    CardView backBtn;
    RelativeLayout shoppingList, favouriteRecipe, reset;
    MealTypeController mealTypeController;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mealTypeController = new MealTypeController(this);

        // Set status bar background color
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorBackground));

        // Set status bar content to dark (black) icons
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        // All Reference Ids of XML
        backBtn = findViewById(R.id.cv_back);
        shoppingList = findViewById(R.id.rl_shopping_list);
        favouriteRecipe = findViewById(R.id.rl_favourite_recipes);
        favouriteRecipe = findViewById(R.id.rl_favourite_recipes);
        reset = findViewById(R.id.rl_reset);

        //All Click Events
        backBtn.setOnClickListener(v -> {
            Intent mI = new Intent(Settings.this, MainActivity.class);
            startActivity(mI);
        });

        shoppingList.setOnClickListener(v -> {
            Intent sI = new Intent(Settings.this, ShoppingListActivity.class);
            startActivity(sI);
        });

        favouriteRecipe.setOnClickListener(v -> {
            Intent fI = new Intent(Settings.this, FavouriteRecipesActivity.class);
            startActivity(fI);
        });

        reset.setOnClickListener(v -> {
            resetMealItems(Settings.this);
        });
    }

    @SuppressLint("SetTextI18n")
    private void resetMealItems(Context context) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_reset_confirmation);

        TextView cancel, yes;

        cancel = dialog.findViewById(R.id.tv_cancel);
        yes = dialog.findViewById(R.id.tv_yes);

        yes.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                mealTypeController.emptyMealItems();
                dialog.dismiss();
                Toast.makeText(context, "All the meal items cleared successfully done", Toast.LENGTH_LONG).show();
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