package com.softdesign.devintensive2.ui.activities

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.softdesign.devintensive2.R
import com.softdesign.devintensive2.utils.ConstantManager
import java.lang.Exception

class BaseActivity : AppCompatActivity() {
    private val TAG: String = ConstantManager.TAG_PREFIX + "BaseActivity"
    protected lateinit var mProgressDialog: ProgressDialog
    fun showProgress() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(this, R.style.custom_dialog)
            mProgressDialog.setCancelable(false)
            mProgressDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            mProgressDialog.show()
            mProgressDialog.setContentView(R.layout.progress_splash)
        }
    }

    fun hideProgress() {

    }

    fun showError(message: String, error: Exception) {
        showToast(message)
        //@toString() maybe wrong
        Log.e(TAG, error.toString())
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG)
    }
}