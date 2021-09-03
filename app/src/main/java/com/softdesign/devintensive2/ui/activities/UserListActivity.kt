package com.softdesign.devintensive2.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.softdesign.devintensive2.R
import com.softdesign.devintensive2.data.managers.DataManager
import com.softdesign.devintensive2.data.network.res.UserData
import com.softdesign.devintensive2.data.network.res.UserListRes
import com.softdesign.devintensive2.data.storage.models.UserDTO
import com.softdesign.devintensive2.databinding.ActivityUserListBinding
import com.softdesign.devintensive2.ui.adapters.UsersAdapter
import com.softdesign.devintensive2.ui.adapters.ViewHolders.UserVH
import com.softdesign.devintensive2.utils.ConstantManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserListActivity : AppCompatActivity() {

    private val TAG: String = ConstantManager.TAG_PREFIX + " UserListActivity"

    private var mCoordinatorLayout: CoordinatorLayout? = null
    private var mToolbar: androidx.appcompat.widget.Toolbar? = null
    private var mNavigationDrawer: DrawerLayout? = null
    private var mRecyclerView: RecyclerView? = null

    private var mDataManager: DataManager? = null
    private var mUsersAdapter: UsersAdapter? = null
    private var mUsers: List<UserData>? = null
//    private var mUserDTO:UserDTO? = null

    lateinit var binding: ActivityUserListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mDataManager = DataManager.getInstance()
        mCoordinatorLayout = binding.coordinatorLayoutUserList
        mToolbar = binding.tbUserList
        mNavigationDrawer = findViewById(R.id.nv_user_list)
        mRecyclerView = findViewById(R.id.rv_user_list)

        var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
        mRecyclerView?.layoutManager = linearLayoutManager

        setupToolbar()
        setupDrawer()
        loadUsers()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            mNavigationDrawer?.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    fun showSnackbar(message: String) {
        Snackbar.make(mCoordinatorLayout!!, message, Snackbar.LENGTH_LONG).show()
    }

    private fun setupToolbar() {
        setSupportActionBar(mToolbar)
        var actionBar: androidx.appcompat.app.ActionBar? = supportActionBar

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupDrawer() {
        // TODO: Ралізувати перехід в іншу актівіті при кліку по елементі меню в NavigationDrawer
    }

    private fun loadUsers() {
        val call: Call<UserListRes> = mDataManager!!.userListFromNetwork

        call.enqueue(object : Callback<UserListRes> {
            override fun onResponse(call: Call<UserListRes>, response: Response<UserListRes>) {
                try {
                    mUsers = response.body()?.data
                    mUsersAdapter = UsersAdapter(mUsers, object : UserVH.CustomClickListener{
                        override fun onUserItemClickListener(position: Int) {
//                            showSnackbar("Користувач з індексом $position")
                            val mUserDTO = UserDTO(mUsers!![position])

                            val profileIntent: Intent =
                                Intent(this@UserListActivity, ProfileUserActivity::class.java)
                            profileIntent.putExtra(ConstantManager.PARCELABLE_KEY ,mUserDTO)

                            startActivity(profileIntent)

                            //TODO: По відкриттю кліком відкрити нове Activity та передати на нього данні користувача
                        }

                    })
                    mRecyclerView?.adapter = mUsersAdapter
                } catch (e: NullPointerException) {
                    Log.e(TAG, e.toString())
                    showSnackbar("Щось пішло не по плану")
                }

            }

            override fun onFailure(call: Call<UserListRes>, t: Throwable) {
                //TODO: Обробити помилки
            }

        })
    }
}