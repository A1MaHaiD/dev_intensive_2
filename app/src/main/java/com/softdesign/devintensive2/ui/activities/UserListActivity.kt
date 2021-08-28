package com.softdesign.devintensive2.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.softdesign.devintensive2.databinding.ActivityUserListBinding

class UserListActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}