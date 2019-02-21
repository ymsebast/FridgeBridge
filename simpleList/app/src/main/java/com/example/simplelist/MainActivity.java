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
    int gm;
    String unit;
    String value;

    public NewFood() {

    }

    public NewFood(String food, int gm, String unit, String value) {
        this.food = food;
        this.gm = gm;
        this.unit = unit;
        this.value = value;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public int getGm() {
        return gm;
    }

    public void setGm(Integer gm) {
        this.gm = gm;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
public class MainActivity extends AppCompatActivity {

    private Firebase mRef;
    private ArrayList<String> mFood = new ArrayList<>();
    private ArrayList<Integer> mGm = new ArrayList<>();
    private ArrayList<String> mUnit = new ArrayList<>();
    private ArrayList<String> mValue = new ArrayList<>();
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
                mGm.add(value.getGm());
                mUnit.add(value.getUnit());
                mValue.add(value.getValue());
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
                String click_gm = mGm.get(position).toString();
                String click_unit = mUnit.get(position);
                String click_value = mValue.get(position);
                Intent intent = new Intent(MainActivity.this, FoodList.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA_FOOD", click_food);
                extras.putString("EXTRA_GM", click_gm);
                extras.putString("EXTRA_UNIT", click_unit);
                extras.putString("EXTRA_VALUE", click_value);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

    }
}
