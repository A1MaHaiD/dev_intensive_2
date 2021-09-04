package com.softdesign.devintensive2.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.softdesign.devintensive2.R
import com.softdesign.devintensive2.data.managers.DataManager
import com.softdesign.devintensive2.data.network.req.UserLoginReq
import com.softdesign.devintensive2.data.network.res.Repo
import com.softdesign.devintensive2.data.network.res.UserData
import com.softdesign.devintensive2.data.network.res.UserListRes
import com.softdesign.devintensive2.data.network.res.UserModelRes
import com.softdesign.devintensive2.data.storage.models.Repository
import com.softdesign.devintensive2.data.storage.models.RepositoryDao
import com.softdesign.devintensive2.data.storage.models.User
import com.softdesign.devintensive2.data.storage.models.UserDao
import com.softdesign.devintensive2.databinding.ActivityAuthBinding
import com.softdesign.devintensive2.utils.AppConfig
import com.softdesign.devintensive2.utils.ConstantManager
import com.softdesign.devintensive2.utils.NetworkStatusChecker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class AuthActivity : BaseActivity(), View.OnClickListener {

    private var mDataManager: DataManager? = null
    private var mRepositoryDao: RepositoryDao? = null
    private var mUserDao: UserDao? = null

    private var mSignIn: Button? = null
    private var mRememberPassword: TextView? = null

    private val TAG: String = ConstantManager.TAG_PREFIX + " AuthActivity"
    lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mDataManager = DataManager.getInstance()
        mUserDao = mDataManager!!.daoSession.userDao
        mRepositoryDao = mDataManager!!.daoSession.repositoryDao

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

    private fun loginSuccess(userModel: UserModelRes) {
        showSnackbar(userModel.data.token)
        mDataManager!!.preferencesManagers.saveAuthToken(userModel.data.token)
        mDataManager!!.preferencesManagers.saveUserId(userModel.data.user.id)
        saveUserValues(userModel)
        saveUserInDb()

        val handler =  Handler()
        handler.postDelayed(object : Runnable {
                override fun run() {
//                val loginIntent = Intent(this@AuthActivity, MainActivity::class.java)
                    val loginIntent = Intent(this@AuthActivity, UserListActivity::class.java)
                    startActivity(loginIntent)
                }
            }, AppConfig.START_DELAY.toLong())
//            showSnackbar("Вхід")


    }

    private fun signIn() {
        if (NetworkStatusChecker.isNetworkAvailable(this)) {
            val call: Call<UserModelRes> = mDataManager!!.loginUser(
//                Date().toString(),
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
                        loginSuccess(response.body()!!)
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

    private fun saveUserValues(userModel: UserModelRes) {
        val userValues = intArrayOf(
            userModel.data.user.profileValues.rait,
            userModel.data.user.profileValues.rait,
            userModel.data.user.profileValues.rait
        )
        mDataManager!!.preferencesManagers.saveUserProfileValues(userValues)
    }

    private fun saveUserInDb() {
        val call: Call<UserListRes> = mDataManager!!.userListFromNetwork

        call.enqueue(object : Callback<UserListRes> {
            override fun onResponse(call: Call<UserListRes>, response: Response<UserListRes>) {
                try {
                    if (response.code() == 200) {

                        val allRepositories: MutableList<Repository> = ArrayList()
                        val allUsers: MutableList<User> = ArrayList()

                        for (userRes: UserData in response.body()!!.data) {
                            allRepositories.addAll(getRepoListFromUserRes(userRes))
                            allUsers.add(User(userRes))
                        }

                        mRepositoryDao!!.insertOrReplaceInTx(allRepositories)
                        mUserDao!!.insertOrReplaceInTx(allUsers)

                    } else {
                        showSnackbar("Список користувачів не можливо отримати")
                        Log.e(TAG, "onResponse: ${response.errorBody()?.source().toString()}")
                    }
//                    mUsers = response.body()?.data
//                    mUsersAdapter = UsersAdapter(mUsers, object : UserVH.CustomClickListener{
//                        override fun onUserItemClickListener(position: Int) {
////                            showSnackbar("Користувач з індексом $position")
//                            val mUserDTO = UserDTO(mUsers!![position])
//
//                            val profileIntent: Intent =
////                                Intent(this@UserListActivity, ProfileUserActivity::class.java)
//                            profileIntent.putExtra(ConstantManager.PARCELABLE_KEY ,mUserDTO)
//
//                            startActivity(profileIntent)
//
//                        }
//
//                    })
//                    mRecyclerView?.adapter = mUsersAdapter
                } catch (e: NullPointerException) {
                    e.printStackTrace()
                    showSnackbar("Щось пішло не по плану")
                }

            }

            override fun onFailure(call: Call<UserListRes>, t: Throwable) {
                //TODO: Обробити помилки
            }

        })
    }

    private fun getRepoListFromUserRes(userData: UserData): List<Repository> {
        val userId: String = userData.id

        val repository: MutableList<Repository> = ArrayList()
        for (repositoryRes: Repo in userData.repositories.repo) {
            repository.add(Repository(repositoryRes, userId))
        }
        return repository
    }
}