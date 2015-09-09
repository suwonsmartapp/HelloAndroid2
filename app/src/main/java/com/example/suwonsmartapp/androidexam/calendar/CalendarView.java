
package com.example.suwonsmartapp.androidexam.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * Created by junsuk on 15. 9. 8.. 달력
 */
public class CalendarView extends GridView implements AdapterView.OnItemClickListener {
    // 코드상에서 생성될 때 호출 하는 생성자
    public CalendarView(Context context) {
        this(context, null);
    }

    // XML 에 정의 되었을 때 호출 되는 생성자
    public CalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    // xml 에서 넘어온 속성을 멤버변수로 셋팅하는 역할을 한다.
    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        setNumColumns(7); // 7열로 설정
        setBackgroundResource(android.R.color.darker_gray); // 배경을 회색으로
        setHorizontalSpacing(1);
        setVerticalSpacing(1);

        // 아이템 클릭 이벤트
        setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (getAdapter() != null) {
            if (getAdapter() instanceof CalendarAdapter) {
                CalendarAdapter adapter = (CalendarAdapter) getAdapter();
                adapter.setSelectedPosition(position);
                adapter.notifyDataSetChanged();
            } else {
                throw new IllegalStateException("CalendarAdapter 를 셋팅 해야 합니다");
            }
        }
    }
}
