package com.softdesign.devintensive2.ui.activities

import android.os.Bundle
import com.softdesign.devintensive2.databinding.ActivityAuthBinding
import com.softdesign.devintensive2.utils.ConstantManager


class AuthActivity : BaseActivity() {
    private val TAG: String = ConstantManager.TAG_PREFIX + "AuthActivity"
    lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}