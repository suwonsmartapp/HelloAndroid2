
package com.example.suwonsmartapp.androidexam.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.suwonsmartapp.androidexam.calendar.model.Schedule;

/**
 * Created by junsuk on 15. 9. 24..
 */
public class ParcelableActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button button = new Button(this);
        button.setText("Parcelable 데이터 전송");

        setContentView(button);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, TargetActivity.class);

        Schedule schedule = new Schedule(10, 10, "바쁘다");

        intent.putExtra("schedule", schedule);

        startActivity(intent);
    }
}
