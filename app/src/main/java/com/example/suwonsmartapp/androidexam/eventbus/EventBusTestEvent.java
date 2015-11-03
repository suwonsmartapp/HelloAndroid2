package com.example.suwonsmartapp.androidexam.eventbus;

/**
 * Created by junsuk on 2015. 11. 3..
 */
public class EventBusTestEvent implements Event {

    public int number;
    public String text;

    public EventBusTestEvent(int number, String text) {
        this.number = number;
        this.text = text;
    }
}
