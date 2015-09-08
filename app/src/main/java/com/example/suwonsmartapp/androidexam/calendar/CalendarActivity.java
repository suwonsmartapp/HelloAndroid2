
package com.example.suwonsmartapp.androidexam.calendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.suwonsmartapp.androidexam.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    private List<Calendar> mList;
    private CalendarAdapter mCalendarAdapter;
    private CalendarView mCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mList = new ArrayList<>();

        // 오늘
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

        // 마지막 날
        int lastDay = calendar.getActualMaximum(Calendar.DATE);

        // 이달의 첫 번째 날
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int firstDay = calendar.get(Calendar.DAY_OF_WEEK);

        // 공백
        for (int i = 1; i < firstDay; i++) {
            mList.add(null);
        }
        // 이번 달 달력 데이터
        for (int i = 1; i <= lastDay; i++) {
            mList.add(new GregorianCalendar(year, month, i));
        }

        // 어댑터 준비
        mCalendarAdapter = new CalendarAdapter(this, mList);

        // View 에 어댑터를 설정
        mCalendarView = (CalendarView) findViewById(R.id.calendar);
        mCalendarView.setAdapter(mCalendarAdapter);
    }

}
