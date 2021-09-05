package com.softdesign.devintensive2.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.core.view.MenuItemCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.softdesign.devintensive2.R
import com.softdesign.devintensive2.data.managers.DataManager
import com.softdesign.devintensive2.data.network.res.UserData
import com.softdesign.devintensive2.data.storage.models.User
import com.softdesign.devintensive2.data.storage.models.UserDTO
import com.softdesign.devintensive2.databinding.ActivityUserListBinding
import com.softdesign.devintensive2.ui.adapters.UsersAdapter
import com.softdesign.devintensive2.ui.adapters.ViewHolders.UserVH
import com.softdesign.devintensive2.utils.ConstantManager

class UserListActivity : AppCompatActivity() {

    private val TAG: String = ConstantManager.TAG_PREFIX + " UserListActivity"

    private var mCoordinatorLayout: CoordinatorLayout? = null
    private var mToolbar: androidx.appcompat.widget.Toolbar? = null
    private var mNavigationDrawer: DrawerLayout? = null
    private var mRecyclerView: RecyclerView? = null

    private var mDataManager: DataManager? = null
    private var mUsersAdapter: UsersAdapter? = null
    private var mUsers: List<UserData>? = null
    private var mSearchItem: MenuItem? = null
    private var mQuery: String? = null
//    private var mUserDTO:UserDTO? = null

    private var mHandler: Handler? = null

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

        val linearLayoutManager = LinearLayoutManager(this)
        mRecyclerView?.layoutManager = linearLayoutManager

        mHandler = Handler(Looper.getMainLooper())

        setupToolbar()
        setupDrawer()
        loadUsersFromDb()
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
        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        mSearchItem = menu?.findItem(R.id.m_search_action)
        val searchView: SearchView = mSearchItem?.actionView as SearchView
        searchView.queryHint = "Введіть Ім'я користувача"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //TODO: Пошук викликати тут
                showUserByQuery(newText!!)
                return false
            }

        })
        return super.onPrepareOptionsMenu(menu)
    }

    private fun showUsers(users: MutableList<User>) {
        mUsers = users.invoke()
        mUsersAdapter = UsersAdapter(mUsers, object : UserVH.CustomClickListener {
            override fun onUserItemClickListener(position: Int) {
//                            showSnackbar("Користувач з індексом $position")
                val mUserDTO = UserDTO(mUsers!![position])

                val profileIntent: Intent =
                    Intent(this@UserListActivity, ProfileUserActivity::class.java)
                profileIntent.putExtra(ConstantManager.PARCELABLE_KEY, mUserDTO)

                startActivity(profileIntent)
            }
        })
        mRecyclerView?.swapAdapter(mUsersAdapter, false)

    }

    private fun showUserByQuery(query: String) {
        mQuery = query

        val searchUsers = object : Runnable {
            override fun run() {
                showUsers(mDataManager!!.getUserListByName(mQuery))
            }
        }
        mHandler?.removeCallbacks(searchUsers)
        mHandler!!.postDelayed(
            searchUsers, ConstantManager.SEARCH_DELAY.toLong()
//            object : Runnable {
//                override fun run() {
//                    TODO("Not yet implemented")
//                }
//            }
        )

    }

    private fun setupDrawer() {
        // TODO: Ралізувати перехід в іншу актівіті при кліку по елементі меню в NavigationDrawer
    }

    private fun loadUsersFromDb() {

        if (mDataManager?.userListFromDb()?.size == 0) {
            showSnackbar("Список користувачів не може бути завантажений")
        } else {
            //TODO: Пошук по базі данних
            showUsers(mDataManager!!.userListFromDb)

//            mUsersAdapter = UsersAdapter(mUsers, object : UserVH.CustomClickListener {
//                override fun onUserItemClickListener(position: Int) {
////                            showSnackbar("Користувач з індексом $position")
//                    val mUserDTO = UserDTO(mUsers!![position])
//
//                    val profileIntent: Intent =
//                        Intent(this@UserListActivity, ProfileUserActivity::class.java)
//                    profileIntent.putExtra(ConstantManager.PARCELABLE_KEY, mUserDTO)
//
//                    startActivity(profileIntent)
//
//                }
//
//            })
//            mRecyclerView?.adapter = mUsersAdapter
        }
//        val call: Call<UserListRes> = mDataManager!!.userListFromNetwork
//
//        call.enqueue(object : Callback<UserListRes> {
//            override fun onResponse(call: Call<UserListRes>, response: Response<UserListRes>) {
//                try {
//                    mUsers = response.body()?.data
//
//                    mRecyclerView?.adapter = mUsersAdapter
//                } catch (e: NullPointerException) {
//                    Log.e(TAG, e.toString())
//                    showSnackbar("Щось пішло не по плану")
//                }
//
//            }
//
//            override fun onFailure(call: Call<UserListRes>, t: Throwable) {
//                //TODO: Обробити помилки
//            }
//
//        })
    }
}

private operator fun <E> MutableList<E>.invoke(): List<UserData>? {
    TODO("Not yet implemented")
}
