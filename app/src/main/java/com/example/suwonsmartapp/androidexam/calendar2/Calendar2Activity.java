
package com.example.suwonsmartapp.androidexam.calendar2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.suwonsmartapp.androidexam.R;

/**
 * Created by junsuk on 15. 9. 9..
 * 구글 제공 CalendarView 사용 예
 */
public class Calendar2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calendar2);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(Calendar2Activity.this, year + "." + month + "." + dayOfMonth,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
