package com.softdesign.devintensive2.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.ActionBar
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar
import com.softdesign.devintensive2.R
import com.softdesign.devintensive2.databinding.ActivityMainBinding
import com.softdesign.devintensive2.utils.ConstantManager

class MainActivity : BaseActivity() {

    private val TAG: String = ConstantManager.TAG_PREFIX + "MainActivity"

    private lateinit var binding: ActivityMainBinding
    private lateinit var mUserInfo: ArrayList<EditText>
    private var mCurrentEditMode: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Log.d(TAG, "onCreate")

        mUserInfoInit()
        onClickEditor()
        onClickCall()
        setupToolbar()
        setupDrawable()
        onClickFloatButton()

        if (savedInstanceState != null) {
            showSnackBar("активити запускаеться впервые")
            //активити запускаеться впервые
        } else {
            showSnackBar("активность уже создавалась")
            //активность уже создавалась
        }
    }

    private fun onClickEditor() {
        binding.fabMain.setOnClickListener {
            mCurrentEditMode = if (mCurrentEditMode == 0) {
                changeEditMode(1)
                1
            } else {
                changeEditMode(0)
                0
            }
        }
    }

    private fun mUserInfoInit() {
        mUserInfo.add(binding.etPhone)
        mUserInfo.add(binding.etEmail)
        mUserInfo.add(binding.etFacebook)
        mUserInfo.add(binding.etGithub)
        mUserInfo.add(binding.etAbout)
    }

    private fun onClickCall() {
        binding.ivCall.setOnClickListener {
//            showProgress()
//            runWithDelay()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    fun runWithDelay() {
//        val handler = android.os.Handler()
//        handler.postDelayed({ //TODO: Выполнить с задержкой
//            hideProgress()
//        }, 5000)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.coordinatorLayoutMain, message, LENGTH_LONG)
            .show()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.tbMain)
        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            binding.dlMain.openDrawer(GravityCompat.START)
        return super.onOptionsItemSelected(item)
    }

    private fun setupDrawable() {
        binding.nvMain.setNavigationItemSelectedListener(
            NavigationView.OnNavigationItemSelectedListener() {
                showSnackBar(it.title.toString())
                it.setChecked(true)
                binding.dlMain.closeDrawer(GravityCompat.START)
                return@OnNavigationItemSelectedListener false
            })
    }

    private fun onClickFloatButton() {
        binding.fabMain.setOnClickListener {
            showSnackBar("click")
        }
    }

    /**
     *      переключаємо режим редагування (switch mode edition)
     *      @param mode якщо 1 режим редагування, якщо 0 режим перегляду ()
     */
    private fun changeEditMode(mode: Int) {
        if (mode == 1) {
            for (userValue: EditText in mUserInfo) {
                userValue.isEnabled = true
                userValue.isFocusable = true
                userValue.isFocusableInTouchMode = true
            }
        } else {
            for (userValue: EditText in mUserInfo) {
                userValue.isEnabled = false
                userValue.isFocusable = false
                userValue.isFocusableInTouchMode = true
            }
        }
    }

    private fun loadUserInfoValue() {

    }

    private fun saveUserInfoValue() {

    }
}