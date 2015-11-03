package com.example.suwonsmartapp.androidexam.eventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.suwonsmartapp.androidexam.R;

import de.greenrobot.event.EventBus;

/**
 * Created by junsuk on 2015. 11. 3..
 */
public class EventBusAcivitiy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_eventbus);
    }

    @Override
    protected void onResume() {
        super.onResume();

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        EventBus.getDefault().unregister(this);
    }

    public void onEvent(EventBusTestEvent event) {
//        Toast.makeText(EventBusAcivitiy.this, event.text, Toast.LENGTH_SHORT).show();
    }

}
