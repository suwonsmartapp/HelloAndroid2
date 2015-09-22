
package com.example.suwonsmartapp.androidexam.calendar.database.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.suwonsmartapp.androidexam.calendar.database.contract.ScheduleContract;
import com.example.suwonsmartapp.androidexam.database.contract.UserContract;

/**
 * Created by junsuk on 15. 9. 18..
 */
public class ScheduleDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Schedule.db";
    public static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            ScheduleContract.ScheduleEntry.TABLE_NAME + " (" +
            ScheduleContract.ScheduleEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ScheduleContract.ScheduleEntry.COLUMN_NAME_HOUR + " INTEGER NOT NULL, " +
            ScheduleContract.ScheduleEntry.COLUMN_NAME_MINUTE + " INTEGER NOT NULL, " +
            ScheduleContract.ScheduleEntry.COLUMN_NAME_CONTENTS + " TEXT NOT NULL, " +
            ScheduleContract.ScheduleEntry.COLUMN_NAME_DATE + " TEXT NOT NULL" +
            ");";

    public ScheduleDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insert(ContentValues values) {
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                ScheduleContract.ScheduleEntry.TABLE_NAME,
                null,
                values);

        return newRowId;
    }

    public Cursor query() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(
                ScheduleContract.ScheduleEntry.TABLE_NAME, // 테이블명
                ScheduleContract.PROJECTION_ALL, // 컬럼명 배열
                null, // WHERE 절의 컬럼명
                null, // WHERE 절의 값
                null, // group by (그룹핑)
                null, // having (그룹핑)
                null // order by (정렬)
                );
        return c;
    }

    public int update(ContentValues values, String selection, String[] selectionArgs) {
        return getReadableDatabase().update(
                ScheduleContract.ScheduleEntry.TABLE_NAME, // 테이블명
                values,
                selection,
                selectionArgs);
    }

    public int delete(String selection, String[] selectionArgs) {
        return getWritableDatabase().delete(UserContract.UserEntry.TABLE_NAME,
                selection,
                selectionArgs);
    }

}
