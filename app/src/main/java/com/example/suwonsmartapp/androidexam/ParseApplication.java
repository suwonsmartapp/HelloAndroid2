
package com.example.suwonsmartapp.androidexam;

import android.app.Application;

import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.facebook.FacebookSdk;
import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by junsuk on 15. 9. 24..
 */
public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Parse
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "jKKNLpQXtUjPMKyqatjkIUgFzoImzyAi7tGuMJ4C",
                "3kv4S5nBUwiQVdftXUhEVQqS3vnQ2dbp4evcWm5F");

        ParseInstallation.getCurrentInstallation().saveInBackground();

        // Bootstrap
        TypefaceProvider.registerDefaultIconSets();

        // Facebook sdk
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
