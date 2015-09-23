
package com.example.suwonsmartapp.androidexam.calendar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.suwonsmartapp.androidexam.R;
import com.example.suwonsmartapp.androidexam.calendar.adapter.CalendarAdapter;
import com.example.suwonsmartapp.androidexam.calendar.adapter.TodoAdapter;
import com.example.suwonsmartapp.androidexam.calendar.database.facade.ScheduleFacade;
import com.example.suwonsmartapp.androidexam.calendar.model.Schedule;
import com.example.suwonsmartapp.androidexam.calendar.view.CalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {

    private CalendarAdapter mCalendarAdapter;
    private CalendarView mCalendarView;

    private TextView mTitleTextView;

    private ListView mTodoListView;
    private TodoAdapter mTodoAdapter;

    // 모든 일정 (저장소) ->
    private ScheduleFacade mScheduleFacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mScheduleFacade = new ScheduleFacade(this);

        mTitleTextView = (TextView) findViewById(R.id.tv_title);

        // 버튼 이벤트 연결
        findViewById(R.id.btn_prev_month).setOnClickListener(this);
        findViewById(R.id.btn_next_month).setOnClickListener(this);

        mCalendarView = (CalendarView) findViewById(R.id.calendar);
        mTodoListView = (ListView) findViewById(R.id.lv_todo);

        // 어댑터 준비
        // View 에 어댑터를 설정
        mCalendarAdapter = new CalendarAdapter(this);
        mCalendarView.setAdapter(mCalendarAdapter);

//        List<Schedule> test = new ArrayList<>();
//        test.add(new Schedule(5, 30, "땡땡이"));
//        test.add(new Schedule(6, 30, "밥 먹기"));
//        test.add(new Schedule(7, 30, "잠자기"));
//        mTodoAdapter = new TodoAdapter(this, test);
//        mTodoListView.setAdapter(mTodoAdapter);


        // 아이템 클릭 이벤트 연결
        mCalendarView.setOnItemLongClickListener(this);
        mCalendarView.setOnItemClickListener(this);

        // 컨텍스트 메뉴 연결
        registerForContextMenu(mTodoListView);
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
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        View layout = getLayoutInflater().inflate(R.layout.dialog_schedule, null);
        final TimePicker timePicker = (TimePicker) layout.findViewById(R.id.picker_time);
        final EditText editText = (EditText) layout.findViewById(R.id.et_schedule);

        // 롱 클릭한 곳의 Calendar 객체를 얻음
        final Calendar calendar = (Calendar) mCalendarAdapter.getItem(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setNegativeButton("닫기", null);
        builder.setPositiveButton("저장", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 6.0에서 deprecated 됨
                // getCurrentHour() -> getHour()
                // getCurrentMinute() -> getMinute()
                Schedule schedule = new Schedule(timePicker.getCurrentHour(),
                        timePicker.getCurrentMinute(),
                        editText.getText().toString());

                // 현재 날짜에 연결 된 일정 리스트를 얻음
                List<Schedule> list = mScheduleFacade.getSchedule(calendar);

                // 만약 리스트가 없으면 초기화 한다
                if (list == null) {
                    list = new ArrayList<>();
                }
                // 지금 추가 할 스케쥴을 추가
                list.add(schedule);

                // 전체 일정에 오늘 날짜와 스케쥴을 연결하여 추가
                mScheduleFacade.addSchedule(calendar, schedule);

                // 어댑터에 표시할 스케쥴 리스트를 전달
                mTodoAdapter = new TodoAdapter(CalendarActivity.this, list);
                // 리스트뷰에 어댑터를 연결
                mTodoListView.setAdapter(mTodoAdapter);
            }
        });

        builder.setView(layout);

        builder.show();
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        loadSchedule(position);
    }

    private void loadSchedule(int position) {
        // 선택 된 것으로 하고 색상 변경
        mCalendarAdapter.setSelectedPosition(position);
        mCalendarAdapter.notifyDataSetChanged();

        // 현재 날짜에 연결 된 일정 리스트를 얻음
        Calendar calendar = (Calendar) mCalendarAdapter.getItem(position);
        List<Schedule> list = mScheduleFacade.getSchedule(calendar);

        if (list == null) {
            list = Collections.emptyList();
        }
        // 어댑터에 표시할 스케쥴 리스트를 전달
        mTodoAdapter = new TodoAdapter(CalendarActivity.this, list);
        // 리스트뷰에 어댑터를 연결
        mTodoListView.setAdapter(mTodoAdapter);
    }

    // 컨텍스트 메뉴 생성
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    // 컨텍스트 메뉴 처리
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // 아답터의 정보를 얻을 수 있는 객체
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        switch (item.getItemId()) {
            case R.id.menu_delete:
                // TODO 삭제를 확인 하는 다이얼로그 띄우기
                Schedule remove = (Schedule) mTodoAdapter.getItem(info.position);
                if (mScheduleFacade.removeSchedule(remove)) {
                    Toast.makeText(CalendarActivity.this, "삭제 하였습니다", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CalendarActivity.this, "에러 발생", Toast.LENGTH_SHORT).show();
                }
                // 달력을 다시 클릭한 것처럼 해서 DB에서 데이타를 다시 로드
                loadSchedule(mCalendarAdapter.getSelectedPosition());
                return true;
            case R.id.menu_modify:
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
