package com.softdesign.devintensive2.ui.activities

import android.os.Bundle
import android.widget.EditText
import android.widget.ListView
import com.google.android.material.appbar.CollapsingToolbarLayout
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
    private var mRepoListView: ListView? = null

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
        val repositoriesAdapter = RepositoriesAdapter(this, repositories)
        mRepoListView?.adapter = repositoriesAdapter

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
}