package com.example.weeklymeal.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.weeklymeal.R;

public class Settings extends AppCompatActivity {

    CardView backBtn;
    RelativeLayout shoppingList, favouriteRecipe, reset;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

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
            Intent fI = new Intent(Settings.this, ResetActivity.class);
            startActivity(fI);
        });
    }
}