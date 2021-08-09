package com.softdesign.devintensive2.data.managers

import android.content.SharedPreferences
import android.net.Uri
import com.softdesign.devintensive2.utils.ConstantManager
import com.softdesign.devintensive2.utils.DevIntensive2Application
import java.util.ArrayList

class PreferencesManager {
    private val mSharedPreferences: SharedPreferences
    fun saveUserProfileData(userFields: List<String?>) {
        val editor = mSharedPreferences.edit()
        for (i in USER_FIELD.indices) {
            editor.putString(USER_FIELD[i], userFields[i])
        }
        editor.apply()
    }

    fun loadUserProfileData(): List<String?> {
        val userFields: MutableList<String?> = ArrayList()
        userFields.add(mSharedPreferences.getString(ConstantManager.USER_PHONE_KEY, "null"))
        userFields.add(mSharedPreferences.getString(ConstantManager.USER_EMAIL_KEY, "null"))
        userFields.add(mSharedPreferences.getString(ConstantManager.USER_FACEBOOK_KEY, "null"))
        userFields.add(mSharedPreferences.getString(ConstantManager.USER_GITHUB_KEY, "null"))
        userFields.add(mSharedPreferences.getString(ConstantManager.USER_ABOUT_KEY, "null"))
        return userFields
    }

    fun saveUserPhoto(uri: Uri?) {
        val editor = mSharedPreferences.edit()
        editor.putString(ConstantManager.USER_PHOTO_KEY, uri.toString())
        editor.apply()
    }

    fun loadUserPhoto(): Uri {
        return Uri.parse(mSharedPreferences
            .getString(ConstantManager.USER_PHOTO_KEY,
                "android.resource://com.softdesign.devintensive2/drawable/user_photo"))
    }

    companion object {
        private val USER_FIELD = arrayOf(
            ConstantManager.USER_PHONE_KEY,
            ConstantManager.USER_EMAIL_KEY,
            ConstantManager.USER_FACEBOOK_KEY,
            ConstantManager.USER_GITHUB_KEY,
            ConstantManager.USER_ABOUT_KEY
        )
    }

    init {
        mSharedPreferences = DevIntensive2Application.getsSharedPreferences()
    }
}