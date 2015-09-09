
package com.example.suwonsmartapp.androidexam.calendar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.suwonsmartapp.androidexam.R;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private CalendarAdapter mCalendarAdapter;
    private CalendarView mCalendarView;

    private TextView mTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mTitleTextView = (TextView) findViewById(R.id.tv_title);

        // 버튼 이벤트 연결
        findViewById(R.id.btn_prev_month).setOnClickListener(this);
        findViewById(R.id.btn_next_month).setOnClickListener(this);

        // 어댑터 준비
        mCalendarAdapter = new CalendarAdapter(this);

        // View 에 어댑터를 설정
        mCalendarView = (CalendarView) findViewById(R.id.calendar);
        mCalendarView.setAdapter(mCalendarAdapter);

        // 아이템 클릭 이벤트 연결
        mCalendarView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_prev_month:
                mCalendarAdapter.prevMonth();
                break;
            case R.id.btn_next_month:
                mCalendarAdapter.nextMonth();
                break;
        }
        updateTitle();
    }

    private void updateTitle() {
        int year = mCalendarAdapter.getCalendar().get(Calendar.YEAR);
        int month = mCalendarAdapter.getCalendar().get(Calendar.MONTH) + 1;
        mTitleTextView.setText(year + "년 " + month + "월");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setNegativeButton("닫기", null);
        builder.setPositiveButton("저장", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 뭔가 합니다
            }
        });
        View layout = getLayoutInflater().inflate(R.layout.dialog_schedule, null);
        // View layout = LayoutInflator.from(this).inflate(R.layout.dialog_schedule, null);
        builder.setView(layout);

        builder.show();
    }
}
