package com.softdesign.devintensive2.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.softdesign.devintensive2.R
import com.softdesign.devintensive2.data.managers.DataManager
import com.softdesign.devintensive2.data.network.req.UserLoginReq
import com.softdesign.devintensive2.data.network.res.UserModelRes
import com.softdesign.devintensive2.databinding.ActivityAuthBinding
import com.softdesign.devintensive2.utils.ConstantManager
import com.softdesign.devintensive2.utils.NetworkStatusChecker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AuthActivity : BaseActivity(), View.OnClickListener {

    private var mDataManager: DataManager? = null
    private var mSignIn: Button? = null
    private var mRememberPassword: TextView? = null

    private val TAG: String = ConstantManager.TAG_PREFIX + "AuthActivity"
    lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mDataManager = DataManager.getInstance()

        mRememberPassword = binding.tvRemember
        mRememberPassword!!.setOnClickListener(this)
        mSignIn = binding.bLogin
        mSignIn!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.b_login -> signIn()
            R.id.tv_remember -> rememberPassword()
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.coordinatorLayoutAuth, message, Snackbar.LENGTH_LONG).show()
    }

    private fun rememberPassword() {
//        val rememberIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://" +
//                "www.facebook.com/" +
//                "login/" +
//                "identify/" +
//                "?ctx=" +
//                "recover&ars=" +
//                "facebook_login&from_login_screen=" +
//                "0"))
        val rememberIntent = Intent(
            Intent.ACTION_VIEW, Uri.parse(
                "http://" +
                        "www.devintensive" +
                        ".softdesign-apps.ru" +
                        "/forgotpass"
            )
        )
        startActivity(rememberIntent)
    }

    private fun loginSuccess(response: Response<UserModelRes>) {
        showSnackbar(response.body()!!.data.token)
        mDataManager!!.preferencesManagers.saveAuthToken(response.body()!!.data.token)
        mDataManager!!.preferencesManagers.saveUserId(response.body()!!.data.user.id)
//            showSnackbar("Вхід")

        val loginIntent = Intent(this, MainActivity::class.java)
        startActivity(loginIntent)
    }

    private fun signIn() {
        if (NetworkStatusChecker.isNetworkAvailable(this)) {
            val call: Call<UserModelRes> = mDataManager!!.loginUser(
                UserLoginReq(
                    binding.etLoginEmail.text.toString(),
                    binding.etLoginPassword.text.toString()
                )
            )
            call.enqueue(object : Callback<UserModelRes> {
                override fun onResponse(
                    call: Call<UserModelRes>,
                    response: Response<UserModelRes>
                ) {
                    if (response.code() == 200) {
                        loginSuccess(response)
                    } else if (response.code() == 404) {
                        showSnackbar("Невірний логін або пароль")
                    } else {
                        showSnackbar("Все пропалало пане Керівник!!!")
                    }
                }

                override fun onFailure(call: Call<UserModelRes>, t: Throwable) {
                    //TODO: обробити помилки ретрофіту
                }
            })
        } else {
            showSnackbar("Відсутнє з'єднання з мережой, повторіть пізніше")
        }
    }
}