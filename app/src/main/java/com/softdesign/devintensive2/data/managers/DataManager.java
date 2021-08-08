package com.softdesign.devintensive2.data.managers;

public class DataManager {
    private static DataManager INSTANCE = null;

    private final PreferencesManagers mPreferencesManagers;

    public DataManager(){
        this.mPreferencesManagers = new PreferencesManagers();
    }

    public static DataManager getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new DataManager();
        }
        return INSTANCE;
    }

    public PreferencesManagers getPreferencesManagers() {
        return mPreferencesManagers;
    }

}
