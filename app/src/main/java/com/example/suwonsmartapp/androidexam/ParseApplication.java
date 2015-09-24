
package com.example.suwonsmartapp.androidexam;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by junsuk on 15. 9. 24..
 */
public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "jKKNLpQXtUjPMKyqatjkIUgFzoImzyAi7tGuMJ4C",
                "3kv4S5nBUwiQVdftXUhEVQqS3vnQ2dbp4evcWm5F");
    }
}
