
package com.example.suwonsmartapp.androidexam.filemanager.event;

import java.io.File;

/**
 * Created by junsuk on 15. 11. 18..
 */
public class ChangePathEvent implements EventBusEvent {
    public File file;

    public ChangePathEvent(File file) {
        this.file = file;
    }

}
