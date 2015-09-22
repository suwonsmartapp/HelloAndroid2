
package com.example.suwonsmartapp.androidexam.calendar.database.facade;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.example.suwonsmartapp.androidexam.calendar.database.contract.ScheduleContract;
import com.example.suwonsmartapp.androidexam.calendar.database.helper.ScheduleDbHelper;
import com.example.suwonsmartapp.androidexam.calendar.model.Schedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by junsuk on 15. 9. 22.. 스케쥴 관리 Facade 클래스
 */
public class ScheduleFacade {
    private ScheduleDbHelper mHelper;
    private SimpleDateFormat mFormat;

    public ScheduleFacade(Context context) {
        mHelper = ScheduleDbHelper.getInstance(context);

        mFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    /**
     * 스케쥴 추가
     *
     * @param calendar
     * @param schedule
     * @return 성공, 실패
     */
    public boolean addSchedule(Calendar calendar, Schedule schedule) {
        ContentValues values = new ContentValues();

        values.put(ScheduleContract.ScheduleEntry.COLUMN_NAME_DATE,
                mFormat.format(calendar.getTime()));
        values.put(ScheduleContract.ScheduleEntry.COLUMN_NAME_HOUR, schedule.getHour());
        values.put(ScheduleContract.ScheduleEntry.COLUMN_NAME_MINUTE, schedule.getMinute());
        values.put(ScheduleContract.ScheduleEntry.COLUMN_NAME_CONTENTS, schedule.getContents());

        return mHelper.getWritableDatabase().insert(
                ScheduleContract.ScheduleEntry.TABLE_NAME,
                null,
                values
                ) != -1;
    }

    public List<Schedule> getSchedule(Calendar calendar) {
        String calStr = mFormat.format(calendar.getTime());

        Cursor cursor = mHelper.getReadableDatabase().query(
                ScheduleContract.ScheduleEntry.TABLE_NAME,
                ScheduleContract.PROJECTION_ALL,
                ScheduleContract.ScheduleEntry.COLUMN_NAME_DATE + "=?",
                new String[] {
                    calStr
                },
                null,
                null,
                ScheduleContract.ScheduleEntry.COLUMN_NAME_DATE + " DESC"
                );

        return cursorToList(cursor);
    }

    @NonNull
    private List<Schedule> cursorToList(Cursor cursor) {
        List<Schedule> list = new ArrayList<>();

        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            int hour = cursor.getInt(cursor
                    .getColumnIndexOrThrow(ScheduleContract.ScheduleEntry.COLUMN_NAME_HOUR));
            int minute = cursor.getInt(cursor
                    .getColumnIndexOrThrow(ScheduleContract.ScheduleEntry.COLUMN_NAME_MINUTE));
            String contents = cursor.getString(cursor
                    .getColumnIndexOrThrow(ScheduleContract.ScheduleEntry.COLUMN_NAME_CONTENTS));

            Schedule schedule = new Schedule(hour, minute, contents);
            list.add(schedule);
        }
        cursor.close();
        return list;
    }
}
