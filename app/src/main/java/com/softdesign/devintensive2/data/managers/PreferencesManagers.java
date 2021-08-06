package com.softdesign.devintensive2.data.managers;

import android.content.SharedPreferences;

import com.softdesign.devintensive2.utils.ConstantManager;
import com.softdesign.devintensive2.utils.DevIntensive2Application;

import java.util.ArrayList;
import java.util.List;

public class PreferencesManagers {
    private SharedPreferences mSharedPreferences;

    private static final String [] USER_FIELD = {
            ConstantManager.USER_PHONE_KEY,
            ConstantManager.USER_EMAIL_KEY,
            ConstantManager.USER_FACEBOOK_KEY,
            ConstantManager.USER_GITHUB_KEY,
            ConstantManager.USER_ABOUT_KEY,
    };

    public PreferencesManagers(){
        mSharedPreferences = DevIntensive2Application.getsSharedPreferences();
    }

    public void saveUserProfileData(List<String> userFields){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        for (int i=0;i<USER_FIELD.length;i++){
            editor.putString(USER_FIELD[i], userFields.get(i));
        }
        editor.apply();
    }

    public List<String> loadUserProfileData(){
        List<String> userFields = new  ArrayList<>();
        userFields.add(mSharedPreferences.getString(ConstantManager.USER_PHONE_KEY,"null"));
        userFields.add(mSharedPreferences.getString(ConstantManager.USER_EMAIL_KEY,"null"));
        userFields.add(mSharedPreferences.getString(ConstantManager.USER_FACEBOOK_KEY,"null"));
        userFields.add(mSharedPreferences.getString(ConstantManager.USER_GITHUB_KEY,"null"));
        userFields.add(mSharedPreferences.getString(ConstantManager.USER_ABOUT_KEY,"null"));
        return userFields;
    }
}
