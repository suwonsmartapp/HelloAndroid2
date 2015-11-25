
package com.example.suwonsmartapp.androidexam.filemanager.event;

/**
 * Created by junsuk on 15. 11. 25..
 */
public class AddTreeViewEvent implements EventBusEvent {
    public String tag;

    public AddTreeViewEvent(String tag) {
        this.tag = tag;
    }
}
