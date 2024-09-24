package com.smilesifat.weeklymeal.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smilesifat.weeklymeal.R;
import com.smilesifat.weeklymeal.adapter.ShoppingListAdapter;
import com.smilesifat.weeklymeal.adapter.WeekDayAdapter;
import com.smilesifat.weeklymeal.database.controller.ShoppingListController;
import com.smilesifat.weeklymeal.model.MealTypeModel;
import com.smilesifat.weeklymeal.model.ShoppingListModel;

import java.util.ArrayList;

public class ShoppingListActivity extends AppCompatActivity {

    CardView backBtn, resetBtn;
    EditText addShopping;
    TextView save;
    RecyclerView shoppingList;
    ShoppingListController shoppingListController;
    ShoppingListAdapter shoppingListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        // Set status bar background color
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorBackground));

        // Set status bar content to dark (black) icons
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        shoppingListController = new ShoppingListController(this);

        backBtn = findViewById(R.id.cv_back);
        resetBtn = findViewById(R.id.cv_reset);
        addShopping = findViewById(R.id.et_add_shopping_items);
        save = findViewById(R.id.tv_save);
        shoppingList = findViewById(R.id.rv_shopping_items);

        shoppingList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        getShoppingList();

        backBtn.setOnClickListener(v -> {
            Intent mI = new Intent(ShoppingListActivity.this, Settings.class);
            startActivity(mI);
        });

        save.setOnClickListener(v -> {
            shoppingListController.addShoppingList(addShopping.getText().toString().trim());
            getShoppingList();
        });

        resetBtn.setOnClickListener(v -> {
            shoppingListController.emptyShoppingList();
            getShoppingList();
        });

    }

    public void getShoppingList() {
        ArrayList<ShoppingListModel> shoppingListModels = shoppingListController.getShoppingList();
        shoppingListAdapter = new ShoppingListAdapter(ShoppingListActivity.this, shoppingListModels);
        shoppingList.setAdapter(shoppingListAdapter);
        addShopping.getText().clear();
    }
}