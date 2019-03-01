package com.example.simplelist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

class NewFood {
    String food;
    String carbs;
    String kcal;
    String fats;
    String protein;

    public NewFood() {
    }

    public NewFood(String food, String carbs, String kcal, String fats, String protein) {
        this.food = food;
        this.carbs = carbs;
        this.kcal = kcal;
        this.fats = fats;
        this.protein = protein;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public String getKcal() {
        return kcal;
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }

    public String getFats() {
        return fats;
    }

    public void setFats(String fats) {
        this.fats = fats;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }
}
public class MainActivity extends AppCompatActivity {

    private Firebase mRef;
    private ArrayList<String> mFood = new ArrayList<>();
    private ArrayList<String> mKcal = new ArrayList<>();
    private ArrayList<String> mCarbs = new ArrayList<>();
    private ArrayList<String> mFats = new ArrayList<>();
    private ArrayList<String> mProtein = new ArrayList<>();
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRef = new Firebase("https://test-43928.firebaseio.com/Food");
        mListView = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mFood);
        mListView.setAdapter(arrayAdapter);
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                NewFood value = dataSnapshot.getValue(NewFood.class);
                mFood.add(value.getFood());
                mKcal.add(value.getKcal());
                mCarbs.add(value.getCarbs());
                mFats.add(value.getFats());
                mProtein.add(value.getProtein());
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String click_food = mFood.get(position);
                String click_carbs = mCarbs.get(position);
                String click_fats = mFats.get(position);
                String click_kcal = mKcal.get(position);
                String click_protein = mProtein.get(position);
                Intent intent = new Intent(MainActivity.this, FoodList.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA_FOOD", click_food);
                extras.putString("EXTRA_CARBS", click_carbs);
                extras.putString("EXTRA_FATS", click_fats);
                extras.putString("EXTRA_KCAL", click_kcal);
                extras.putString("EXTRA_PROTEIN", click_protein);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

    }
}
