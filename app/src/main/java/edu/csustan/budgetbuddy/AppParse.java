package edu.csustan.budgetbuddy;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.Parse;
import android.app.Application;

public class AppParse extends Application {

    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("mYr260IWiwsxSA2qdiXMA3wQ3Ux8n3AICL7vXDuV")
                .clientKey("hryQYputh23AfEbCPdt9uwKa9K2t36la5SQEGdbf")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}