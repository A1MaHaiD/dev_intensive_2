package com.softdesign.devintensive2.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.softdesign.devintensive2.databinding.ActivityProfileUserBinding
import com.softdesign.devintensive2.utils.ConstantManager

class ProfileUserActivity : AppCompatActivity() {

    private val TAG: String = ConstantManager.TAG_PREFIX + " ProfileUserActivity"

    lateinit var binding: ActivityProfileUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
}