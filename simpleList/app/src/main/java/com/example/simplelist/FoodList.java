package com.example.simplelist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FoodList extends AppCompatActivity {

    TextView tFood;
    TextView tValue;
    TextView tUnit;
    TextView tGm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        tFood = (TextView) findViewById(R.id.textFood);
        tValue = (TextView) findViewById(R.id.textValue);
        tUnit = (TextView) findViewById(R.id.textUnit);
        tGm = (TextView) findViewById(R.id.textGm);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String food_string = extras.getString("EXTRA_FOOD");
        String value_string = extras.getString("EXTRA_VALUE");
        String unit_string = extras.getString("EXTRA_UNIT");
        String gm_string = extras.getString("EXTRA_GM");

        tFood.setText(food_string);
        tValue.setText(value_string);
        tUnit.setText(unit_string);
        tGm.setText(gm_string);
    }
}
