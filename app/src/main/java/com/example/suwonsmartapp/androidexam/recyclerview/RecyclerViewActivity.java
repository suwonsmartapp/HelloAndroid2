package com.example.suwonsmartapp.androidexam.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.suwonsmartapp.androidexam.R;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, RecyclerViewFragment.newInstance())
                .commit();
    }
}
