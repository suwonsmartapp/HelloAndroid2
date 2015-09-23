
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
                ScheduleContract.ScheduleEntry.COLUMN_NAME_HOUR + " ASC, " +
                        ScheduleContract.ScheduleEntry.COLUMN_NAME_MINUTE + " ASC"
                );

        return cursorToList(cursor);
    }

    @NonNull
    private List<Schedule> cursorToList(Cursor cursor) {
        List<Schedule> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            int hour = cursor.getInt(cursor
                    .getColumnIndexOrThrow(ScheduleContract.ScheduleEntry.COLUMN_NAME_HOUR));
            int minute = cursor.getInt(cursor
                    .getColumnIndexOrThrow(ScheduleContract.ScheduleEntry.COLUMN_NAME_MINUTE));
            String contents = cursor.getString(cursor
                    .getColumnIndexOrThrow(ScheduleContract.ScheduleEntry.COLUMN_NAME_CONTENTS));
            long id = cursor.getLong(cursor
                    .getColumnIndexOrThrow(ScheduleContract.ScheduleEntry._ID));
            String date = cursor.getString(cursor
                    .getColumnIndexOrThrow(ScheduleContract.ScheduleEntry.COLUMN_NAME_DATE));

            Schedule schedule = new Schedule(id, date, hour, minute, contents);
            list.add(schedule);
        }
        cursor.close();
        return list;
    }

    /**
     * 스케쥴 삭제
     * @param schedule 삭제 할 스케쥴
     * @return 성공/실패
     */
    public boolean removeSchedule(Schedule schedule) {
        return mHelper.getWritableDatabase().delete(
                ScheduleContract.ScheduleEntry.TABLE_NAME,
                // TODO Date 비교 조건 추가
                ScheduleContract.ScheduleEntry.COLUMN_NAME_HOUR + "=? AND " +
                        ScheduleContract.ScheduleEntry.COLUMN_NAME_MINUTE + "=?",
                new String[] {
                        "" + schedule.getHour(), "" + schedule.getMinute()
                }
                ) != 0;
    }

    /**
     * 스케쥴 수정
     *
     * @param schedule 기존 데이타에 변경 할 값을 셋팅 해서 받아야 함
     * @return the number of rows affected
     */
    public int modifySchedule(Schedule schedule) {
        ContentValues values = new ContentValues();
        values.put(ScheduleContract.ScheduleEntry.COLUMN_NAME_HOUR, schedule.getHour());
        values.put(ScheduleContract.ScheduleEntry.COLUMN_NAME_MINUTE, schedule.getMinute());
        values.put(ScheduleContract.ScheduleEntry.COLUMN_NAME_CONTENTS, schedule.getContents());

        String whereClause = ScheduleContract.ScheduleEntry._ID + "=?";

        String[] whereArgs = {
            schedule.getId() + ""
        };

        return mHelper.getWritableDatabase().update(
                ScheduleContract.ScheduleEntry.TABLE_NAME,
                values,
                whereClause,
                whereArgs
                );
    }
}
