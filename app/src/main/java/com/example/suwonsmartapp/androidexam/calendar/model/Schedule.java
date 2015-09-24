
package com.example.suwonsmartapp.androidexam.calendar.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by junsuk on 15. 9. 10.. 일정
 */
public class Schedule implements Parcelable {

    public static final Parcelable.Creator<Schedule> CREATOR = new Parcelable.Creator<Schedule>() {
        public Schedule createFromParcel(Parcel source) {
            return new Schedule(source);
        }

        public Schedule[] newArray(int size) {
            return new Schedule[size];
        }
    };
    private long id;
    private String date;
    /**
     * 시간
     */
    private int hour;
    /**
     * 분
     */
    private int minute;
    /**
     * 내용
     */
    private String contents;

    public Schedule(int hour, int minute, String contents) {
        this.contents = contents;
        this.hour = hour;
        this.minute = minute;
    }

    public Schedule(long id, String date, int hour, int minute, String contents) {
        this(hour, minute, contents);

        this.id = id;
        this.date = date;
    }

    protected Schedule(Parcel in) {
        this.id = in.readLong();
        this.date = in.readString();
        this.hour = in.readInt();
        this.minute = in.readInt();
        this.contents = in.readString();
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return hour + ":" + minute + " " + contents;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.date);
        dest.writeInt(this.hour);
        dest.writeInt(this.minute);
        dest.writeString(this.contents);
    }
}
