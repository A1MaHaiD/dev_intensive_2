package com.softdesign.devintensive2.utils

import android.app.Application
import android.content.SharedPreferences

//@sSharedPreferences maybe must be abstract
class DevIntensive2Application(val sSharedPreferences: SharedPreferences) : Application() {
}