package com.example.weeklymeal.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.weeklymeal.R;

public class Settings extends AppCompatActivity {

    CardView backBtn;

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

        //All Click Events
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mI = new Intent(Settings.this, MainActivity.class);
                startActivity(mI);
            }
        });
    }
}