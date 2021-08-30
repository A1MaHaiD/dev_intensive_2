package com.softdesign.devintensive2.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.softdesign.devintensive2.R
import com.softdesign.devintensive2.data.managers.DataManager
import com.softdesign.devintensive2.data.network.res.UserData
import com.softdesign.devintensive2.databinding.ActivityUserListBinding
import com.softdesign.devintensive2.ui.adapters.UserAdapter

class UserListActivity : AppCompatActivity() {

    private var mCoordinatorLayout: CoordinatorLayout? = null
    private var mToolbar: Toolbar? = null
    private var mNavigationDrawer: DrawerLayout? = null
    private var mRecyclerView: RecyclerView? = null

    private var mDataManager: DataManager? = null
    private var mUserAdapter: UserAdapter? = null
    private var mUser: ArrayList<UserData>? = null

    lateinit var binding: ActivityUserListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mDataManager = DataManager.getInstance()
        mCoordinatorLayout = findViewById(R.id.coordinator_layout_user_list)
        mToolbar = findViewById(R.id.tb_user_list)
        mNavigationDrawer = findViewById(R.id.nv_user_list)

        setupToobar()
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

    private fun setupToobar() {
        TODO("Not yet implemented")
    }

    private fun setupDrawer() {
        TODO("Not yet implemented")
    }

    private fun loadUsers() {
        TODO("Not yet implemented")
    }
}