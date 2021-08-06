package com.softdesign.devintensive2.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
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
    private lateinit var mUserInfo: List<EditText>
    private var mCurrentEditMode: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Log.d(TAG, "onCreate")

        mUserInfoInit()
        setupToolbar()
        setupDrawable()
        onClickEditor()
        onClickCall()
        onClickFloatButton()

        if (savedInstanceState == null) {
            showSnackBar("активити запускаеться впервые")
            //активити запускаеться впервые
        } else {
            mCurrentEditMode = savedInstanceState.getInt(ConstantManager.EDIT_MODE_KEY, 0)
            changeEditMode(mCurrentEditMode)
            showSnackBar("активность уже создавалась")
            //активность уже создавалась
        }
    }

    private fun onClickEditor() {
        Log.e(TAG, "onClickEditor")
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
        mUserInfo = ArrayList()
        (mUserInfo as ArrayList<EditText>).add(binding.etPhone)
        (mUserInfo as ArrayList<EditText>).add(binding.etEmail)
        (mUserInfo as ArrayList<EditText>).add(binding.etFacebook)
        (mUserInfo as ArrayList<EditText>).add(binding.etGithub)
        (mUserInfo as ArrayList<EditText>).add(binding.etAbout)
        Log.e(TAG, "mUserInfoInit")
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
        outState.putInt(ConstantManager.EDIT_MODE_KEY, mCurrentEditMode)
    }

    private fun showSnackBar(message: String) {
        Log.e(TAG, "showSnackBar")
        Snackbar.make(binding.coordinatorLayoutMain, message, LENGTH_LONG)
            .show()
    }

    private fun setupToolbar() {
        Log.e(TAG, "setupToolbar")
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
        Log.e(TAG, "onClickFloatButton")
        binding.fabMain.setOnClickListener {
            showSnackBar("click")
        }
    }

    /**
     *      переключаємо режим редагування (switch mode edition)
     *      @param mode якщо 1 режим редагування, якщо 0 режим перегляду ()
     */
    private fun changeEditMode(mode: Int) {
        Log.e(TAG, "changeEditMode")
        if (mode == 1) {
            binding.fabMain.setImageResource(R.drawable.ic_baseline_done_24)
            Log.e(TAG, "changeEditMode_1")
            for (userValue: EditText in mUserInfo) {
                userValue.isEnabled = true
                userValue.isFocusable = true
                userValue.isFocusableInTouchMode = true
            }
        } else {
            binding.fabMain.setImageResource(R.drawable.ic_baseline_create_24)
            Log.e(TAG, "changeEditMode_0")
            for (userValue: EditText in mUserInfo) {
                userValue.isEnabled = false
                userValue.isFocusable = false
                userValue.isFocusableInTouchMode = false
            }
        }
    }

    private fun loadUserInfoValue() {

    }

    private fun saveUserInfoValue() {

    }
}