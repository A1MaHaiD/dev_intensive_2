package com.softdesign.devintensive2.ui.activities

import android.app.AlertDialog
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.softdesign.devintensive2.R
import com.softdesign.devintensive2.utils.ConstantManager
import kotlin.Exception

open class BaseActivity : AppCompatActivity() {
    private val TAG: String = ConstantManager.TAG_PREFIX + " BaseActivity"
    lateinit var mProgressDialog:AlertDialog
    fun showProgress() {
        mProgressDialog.setContentView(R.layout.progress_splash)
        mProgressDialog.show()
    }

    fun hideProgress() {
        if (mProgressDialog.isShowing){
            mProgressDialog.hide()
        }
    }

    fun showError(message: String, error: Exception) {
        showToast(message)
        //@toString() maybe wrong
        Log.e(TAG, error.toString())
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}

