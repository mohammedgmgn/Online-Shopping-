package com.mahmoud.mohammed.onlineshopping;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by mohammed on 01/12/2017.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
