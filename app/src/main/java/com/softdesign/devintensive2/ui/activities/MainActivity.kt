package com.softdesign.devintensive2.ui.activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.softdesign.devintensive2.databinding.ActivityMainBinding
import com.softdesign.devintensive2.utils.ConstantManager


class MainActivity : AppCompatActivity() {

    private val TAG: String = ConstantManager.TAG_PREFIX + "MainActivity"

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate")
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
}