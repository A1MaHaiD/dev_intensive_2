package com.softdesign.devintensive2.ui.activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
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

//    lateinit var mCallImg: ImageView

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Log.d(TAG, "onCreate")

//        mCallImg = findViewById(R.id.iv_call)
//        mCallImg.setOnClickListener(this)

        binding.ivCall.setOnClickListener {
//            showProgress()
//            runWithDelay()
        }
        setupToolbar()
        setupDrawable()

        if (savedInstanceState != null) {
            showSnackBar("активити запускаеться впервые")
            //активити запускаеться впервые
        } else {
            showSnackBar("активность уже создавалась")
            //активность уже создавалась
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

//    fun runWithDelay() {
//        val handler = android.os.Handler()
//        handler.postDelayed({ //TODO: Выполнить с задержкой
//            hideProgress()
//        }, 5000)
//    }

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
}