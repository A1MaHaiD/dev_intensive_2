package com.softdesign.devintensive2.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.softdesign.devintensive2.R
import com.softdesign.devintensive2.databinding.ActivityAuthBinding
import com.softdesign.devintensive2.utils.ConstantManager


class AuthActivity : BaseActivity(), View.OnClickListener {

    private var mSignIn: Button? = null
    private var mRememberPassword: TextView? = null


    private val TAG: String = ConstantManager.TAG_PREFIX + "AuthActivity"
    lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mRememberPassword = binding.tvRemember
        mRememberPassword!!.setOnClickListener(this)
        mSignIn = binding.bLogin
        mSignIn!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.b_login -> loginSuccess()
            R.id.tv_remember -> rememberPassword()
        }
    }

    private fun showSnackbar(message:String){
        Snackbar.make(binding.coordinatorLayoutAuth, message,Snackbar.LENGTH_LONG).show()
    }

    private fun rememberPassword(){
//        val rememberIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://" +
//                "www.facebook.com/" +
//                "login/" +
//                "identify/" +
//                "?ctx=" +
//                "recover&ars=" +
//                "facebook_login&from_login_screen=" +
//                "0"))
        val rememberIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://" +
                "www.devintensive" +
                ".softdesign-apps.ru" +
                "/forgotpass"))
        startActivity(rememberIntent)
    }

    private fun loginSuccess(){
        showSnackbar("Вхід")
    }
}