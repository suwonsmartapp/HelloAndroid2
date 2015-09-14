
package com.example.suwonsmartapp.androidexam.parsing.json;

/**
 * Created by junsuk on 15. 9. 14..
 */
public class Weather {
    private long time;
    private String temp;
    private String description;

    public Weather(long time, String temp, String description) {
        this.description = description;
        this.temp = temp;
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "time: " + time + ", temp: " + temp + ", desc: " + description;
    }
}
