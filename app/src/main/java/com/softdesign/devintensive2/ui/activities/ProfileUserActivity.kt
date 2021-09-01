package com.softdesign.devintensive2.ui.activities

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ListView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.snackbar.Snackbar
import com.softdesign.devintensive2.R
import com.softdesign.devintensive2.data.storage.models.UserDTO
import com.softdesign.devintensive2.databinding.ActivityProfileUserBinding
import com.softdesign.devintensive2.ui.adapters.RepositoriesAdapter
import com.softdesign.devintensive2.utils.ConstantManager
import com.squareup.picasso.Picasso

class ProfileUserActivity : BaseActivity() {

    private val TAG: String = ConstantManager.TAG_PREFIX + " ProfileUserActivity"

    private var mUserAbout: EditText? = null
    private var mCollapsingToolbarLayout: CollapsingToolbarLayout? = null
    private var mCoordinatorLayout: CoordinatorLayout? = null
    private var mRepoListView: ListView? = null
    private var mRepositoriesAdapter: RepositoriesAdapter? = null

    lateinit var binding: ActivityProfileUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.tbProfileUser
        binding.ivUserPhotoProfile
        binding.tvUserInfoRateProfile
        binding.tvUserInfoCodeLineProfile
        binding.tvUserInfoProjectProfile
        mUserAbout = findViewById(R.id.et_about)
        mCollapsingToolbarLayout = binding.ctlProfileUser
        mCoordinatorLayout = binding.coordinatorLayoutProfileUser

        mRepoListView = findViewById(R.id.lv_repositories_list)

        setupToolbar()
        initProfileData()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.tbProfileUser)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initProfileData() {
        val userDTO: UserDTO? = intent.getParcelableExtra(ConstantManager.PARCELABLE_KEY)

        val repositories: MutableList<String>? = userDTO?.repositories
        mRepositoriesAdapter = RepositoriesAdapter(this, repositories)
        mRepoListView?.adapter = mRepositoriesAdapter

        mRepoListView?.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long

            ) {
                Snackbar.make(
                    mCollapsingToolbarLayout!!,
                    "Репозиторій ${repositories!![position]}",
                    Snackbar.LENGTH_LONG
                )
                    .show()
                //TODO: Реалізувати через Intent.ACTION_VIEW перегляд Репозиторія
            }

        }

        mUserAbout?.setText(userDTO?.about)
        binding.tvUserInfoRateProfile.text = userDTO?.rating
        binding.tvUserInfoCodeLineProfile.text = userDTO?.codeLines
        binding.tvUserInfoProjectProfile.text = userDTO?.projects

        mCollapsingToolbarLayout?.title = userDTO?.fullName
        Picasso.get()
            .load(userDTO?.photo)
            .placeholder(R.drawable.user_photo)
            .error(R.drawable.user_photo)
            .into(binding.ivUserPhotoProfile)
    }

    private fun setListViewHeightBasedOnChildren(listView: ListView) {
        if (mRepositoriesAdapter == null) {
            return
        }
        val desireWidth: Int = View.MeasureSpec.makeMeasureSpec(
            listView.width,
            View.MeasureSpec.UNSPECIFIED
        )
        var totalHeight = 0
        var view: View? = null
        for (i in 0 until mRepositoriesAdapter!!.count) {
            view = mRepositoriesAdapter!!.getView(i, view, listView)
            if (i == 0) view.layoutParams = ViewGroup.LayoutParams(
                desireWidth,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            view.measure(desireWidth, View.MeasureSpec.UNSPECIFIED)
            totalHeight += view.measuredHeight
        }
        val params = listView.layoutParams
        params.height = totalHeight + listView.dividerHeight * (mRepositoriesAdapter!!.count -1)
        listView.layoutParams = params
    }
}