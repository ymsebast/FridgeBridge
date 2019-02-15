package com.example.testapp;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FBApp {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceFood;
    private List<Food> foods = new ArrayList<>();
    public interface DataStatus{
        void DataIsLoaded(List<Food> foods, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FBApp() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceFood = mDatabase.getReference("Food");
    }
    public void readFoods(final DataStatus dataStatus) {
        mReferenceFood.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    Food food = keyNode.getValue(Food.class);
                    foods.add(food);
                }
                dataStatus.DataIsLoaded(foods,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
