package com.softdesign.devintensive2.data.managers;

public class DataManager {
    private static DataManager INSTANCE = null;

    private final PreferencesManager mPreferencesManager;

    public DataManager(){
        this.mPreferencesManager = new PreferencesManager();
    }

    public static DataManager getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new DataManager();
        }
        return INSTANCE;
    }

    public PreferencesManager getPreferencesManagers() {
        return mPreferencesManager;
    }

}
