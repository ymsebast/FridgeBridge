package com.example.simplelist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FoodList extends AppCompatActivity {

    TextView tFood;
    TextView tCarbs;
    TextView tFats;
    TextView tKcal;
    TextView tProtein;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        tFood = (TextView) findViewById(R.id.textFood);
        tKcal = (TextView) findViewById(R.id.textKcal);
        tCarbs = (TextView) findViewById(R.id.textCarbs);
        tFats = (TextView) findViewById(R.id.textFats);
        tProtein = (TextView) findViewById(R.id.textProtein);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String food_string = extras.getString("EXTRA_FOOD");
        String carbs_string = extras.getString("EXTRA_CARBS");
        String fats_string = extras.getString("EXTRA_FATS");
        String kcal_string = extras.getString("EXTRA_KCAL");
        String protein_string = extras.getString("EXTRA_PROTEIN");

        tFood.setText(food_string);
        tKcal.setText(kcal_string);
        tCarbs.setText(carbs_string);
        tFats.setText(fats_string);
        tProtein.setText(protein_string);
    }
}
