package com.example.fridgebridge;

import android.app.Application;

import com.firebase.client.Firebase;

public class FridgeBridgeFirebase extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
