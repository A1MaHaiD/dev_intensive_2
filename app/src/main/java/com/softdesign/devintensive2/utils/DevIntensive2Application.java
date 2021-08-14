package com.softdesign.devintensive2.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class DevIntensive2Application extends Application {

    public static SharedPreferences sSharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public static SharedPreferences getsSharedPreferences() {
        return sSharedPreferences;
    }
    public static Context getContext() {
        return (Context) sSharedPreferences;
    }

}
