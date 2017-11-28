package com.mahmoud.mohammed.onlineshopping.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mahmoud.mohammed.onlineshopping.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    protected void showViews(View... views) {
        for (View v : views)
            if (v != null)
                v.setVisibility(View.VISIBLE);
    }

    protected void hideViews(View... views) {
        for (View v : views)
            if (v != null)
                v.setVisibility(View.GONE);
    }
}
