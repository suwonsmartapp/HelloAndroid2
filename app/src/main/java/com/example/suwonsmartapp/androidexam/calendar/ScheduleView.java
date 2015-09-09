
package com.example.suwonsmartapp.androidexam.calendar;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by junsuk on 15. 9. 9..
 */
public class ScheduleView extends CalendarView {
    public ScheduleView(Context context) {
        super(context);
    }

    public ScheduleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScheduleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener listener) {
        super.setOnItemClickListener(listener);

    }
}
