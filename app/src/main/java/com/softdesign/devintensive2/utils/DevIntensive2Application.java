package com.softdesign.devintensive2.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.softdesign.devintensive2.data.storage.models.DaoMaster;
import com.softdesign.devintensive2.data.storage.models.DaoSession;

public class DevIntensive2Application extends Application {

    private static Context sContext;
    public static SharedPreferences sSharedPreferences;
    private static DaoSession sDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        DaoMaster.DevOpenHelper helper = new DaoMaster
                .DevOpenHelper(this, "devintensive-db");
    }

    public static SharedPreferences getsSharedPreferences() {
        return sSharedPreferences;
    }

    public static Context getContext() {
        return sContext;
    }

    public static DaoSession getsDaoSession() {
        return sDaoSession;
    }
}
