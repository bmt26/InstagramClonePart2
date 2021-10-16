package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Post.class);

        // Register your parse models
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("MW6IoZAUydcWRwQZ60GLf7vBpD7kB34hvngpSNBd")
                .clientKey("FQKpX2O0eUlUk7LDlhDv2rD253hZhShL3nUk4jAU")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
