package com.example.weeklymeal.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weeklymeal.R;
import com.example.weeklymeal.adapter.FavouriteListAdapter;
import com.example.weeklymeal.adapter.ShoppingListAdapter;
import com.example.weeklymeal.database.controller.FavouriteController;
import com.example.weeklymeal.model.FavouriteModel;
import com.example.weeklymeal.model.ShoppingListModel;

import java.util.ArrayList;

public class FavouriteRecipesActivity extends AppCompatActivity {

    CardView backBtn;
    RecyclerView favouriteList;
    FavouriteListAdapter favouriteListAdapter;
    FavouriteController favouriteController;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_recipes);

        // Set status bar background color
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorBackground));

        // Set status bar content to dark (black) icons
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        favouriteController = new FavouriteController(this);
        backBtn = findViewById(R.id.cv_back);
        favouriteList = findViewById(R.id.rv_favourite_list);

        favouriteList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        backBtn.setOnClickListener(v -> {
            Intent mI = new Intent(FavouriteRecipesActivity.this, Settings.class);
            startActivity(mI);
        });

        getFavouriteList();
    }

    public void getFavouriteList() {
        ArrayList<FavouriteModel> fav = favouriteController.getAllFavourites();
        favouriteListAdapter = new FavouriteListAdapter(FavouriteRecipesActivity.this, fav);
        favouriteList.setAdapter(favouriteListAdapter);
    }
}