package com.softdesign.devintensive2.ui.activities

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.View.FOCUSABLE
import android.view.View.NOT_FOCUSABLE
import android.widget.EditText
import androidx.appcompat.app.ActionBar
import androidx.core.view.GravityCompat
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.LayoutParams
import com.google.android.material.appbar.AppBarLayout.LayoutParams.*
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar
import com.softdesign.devintensive2.R
import com.softdesign.devintensive2.data.managers.DataManager
import com.softdesign.devintensive2.databinding.ActivityMainBinding
import com.softdesign.devintensive2.databinding.UserLayoutContentBinding
import com.softdesign.devintensive2.utils.ConstantManager
import com.softdesign.devintensive2.utils.ConstantManager.*
import java.io.File
import java.io.IOException
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : BaseActivity() {

    private val TAG: String = ConstantManager.TAG_PREFIX + "MainActivity"

    private lateinit var mDataManager: DataManager
    private lateinit var binding: ActivityMainBinding
    private lateinit var bindingChild: UserLayoutContentBinding
    private lateinit var mUserInfoViews: MutableList<EditText>
    private var mCurrentEditMode: Int = 0
    private var mAppBarParam: LayoutParams? = null
    private var mAppBarLayout: AppBarLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        bindingChild = UserLayoutContentBinding.inflate(layoutInflater)
        val viewChild = bindingChild.root
        setContentView(viewChild)
        val view = binding.root
        setContentView(view)
        Log.d(TAG, "onCreate")

        mDataManager = DataManager.getInstance()
        mUserInfoInit()
        setupToolbar()
        setupDrawable()
        loadUserInfoValue()
        onClickFloatButton()
        onClickPlaceholder()
        onClickCall()

        // val test:MutableList<String> = mDataManager.preferencesManagers.loadUserProfileData()

        if (savedInstanceState == null) {
            showSnackBar("активити запускаеться впервые")
            //активити запускаеться впервые
        } else {
            mCurrentEditMode = savedInstanceState.getInt(ConstantManager.EDIT_MODE_KEY, 0)
            changeEditMode(mCurrentEditMode)
            showSnackBar("активность уже создавалась")
            //активность уже создавалась
        }
    }

    private fun onClickPlaceholder() {
        binding.rlProfilePlaceholder.setOnClickListener {
            onCreateDialog(LOAD_PROFILE_PHOTO)
        }
    }

    override fun onCreateDialog(id: Int): Dialog? {
        return when (id) {
            ConstantManager.LOAD_PROFILE_CAMERA -> {
                val selectItem = arrayOf(
                    getString(R.string.user_profile_dialog_gallery),
                    getString(R.string.user_profile_dialog_camera),
                    getString(R.string.user_profile_dialog_cancel)
                )
                val builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.user_profile_title))
                builder.setItems(selectItem) { dialog, choiceItem ->
                    when (choiceItem) {
                        0 -> {
                            //TODO: Загрузити фото з галереї
                            loadPhotoFromGallery()
                            showSnackBar("Загрузити фото з галереї")
                        }
                        1 -> {
                            //TODO: Загрузити фото з камери
                            loadPhotoFromCamera()
                            showSnackBar("Загрузити фото з камери")
                        }
                        2 -> {
                            dialog.cancel()
                            //TODO: Відміна
                            showSnackBar("Відміна")
                        }
                    }
                }
                builder.create()
            }
            else -> null
        }
    }

    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd HHmmss")
            .format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = Environment
            .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(imageFileName, ".jpg", storageDir)
    }

    private fun onClickFloatButton() {
        binding.fabMain.setOnClickListener {
            showSnackBar("click")
            onClickEditor()
        }
    }

    private fun onClickEditor() {
        Log.e(TAG, "onClickEditor")
        mCurrentEditMode = if (mCurrentEditMode == 0) {
            changeEditMode(1)
            1
        } else {
            changeEditMode(0)
            0
        }
    }

    private fun mUserInfoInit() {
        mUserInfoViews = ArrayList()
        mUserInfoViews.add(bindingChild.etPhone)
        mUserInfoViews.add(bindingChild.etEmail)
        mUserInfoViews.add(bindingChild.etFacebook)
        mUserInfoViews.add(bindingChild.etGithub)
        mUserInfoViews.add(bindingChild.etAbout)
        Log.e(TAG, "mUserInfoInit")
    }

    private fun onClickCall() {
        bindingChild.ivCall.setOnClickListener {
//            showProgress()
//            runWithDelay()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
        saveUserInfoValue()
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    fun runWithDelay() {
//        val handler = android.os.Handler()
//        handler.postDelayed({ //TODO: Выполнить с задержкой
//            hideProgress()
//        }, 5000)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(ConstantManager.EDIT_MODE_KEY, mCurrentEditMode)
    }

    private fun showSnackBar(message: String) {
        Log.e(TAG, "showSnackBar")
        Snackbar.make(binding.coordinatorLayoutMain, message, LENGTH_LONG)
            .show()
    }

    private fun setupToolbar() {
        Log.e(TAG, "setupToolbar")
        setSupportActionBar(binding.tbMain)
        val actionBar: ActionBar? = supportActionBar

        mAppBarParam = binding.ctlMain.layoutParams as LayoutParams?
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            binding.dlMain.openDrawer(GravityCompat.START)
        return super.onOptionsItemSelected(item)
    }

    private fun setupDrawable() {
        binding.nvMain.setNavigationItemSelectedListener(
            NavigationView.OnNavigationItemSelectedListener() {
                showSnackBar(it.title.toString())
                it.isChecked = true
                binding.dlMain.closeDrawer(GravityCompat.START)
                return@OnNavigationItemSelectedListener true
            })
    }

    /**
     * Отримання результату з іншої Activity (фото з камери або галереї)
     * @param requestCode
     * @param resultCode
     * @param data
     */

    /**
     * Переключаємо режим редагування (switch mode edition)
     * @param mode якщо 1 режим редагування, якщо 0 режим перегляду ()
     */
    private fun changeEditMode(mode: Int) {
        Log.e(TAG, "changeEditMode")
        if (mode == 1) {
            binding.fabMain.setImageResource(R.drawable.ic_baseline_done_24)
            showSnackBar("click")
            Log.e(TAG, "changeEditMode_1")
            for (userValue: EditText in mUserInfoViews) {
                userValue.isEnabled = true
                userValue.focusable = FOCUSABLE
                userValue.isFocusableInTouchMode = true
                showProfilePlaceholder()
                lockToolbar()
                binding.ctlMain.setExpandedTitleColor(Color.TRANSPARENT)
            }
        } else {
            binding.fabMain.setImageResource(R.drawable.ic_baseline_create_24)
            showSnackBar("click")
            Log.e(TAG, "changeEditMode_0")
            for (userValue: EditText in mUserInfoViews) {
                userValue.isEnabled = false
                userValue.focusable = NOT_FOCUSABLE
                userValue.isFocusableInTouchMode = false
                hideProfilePlaceholder()
                unlockToolbar()
                binding.ctlMain.setExpandedTitleColor(resources.getColor(R.color.white))
                saveUserInfoValue()
            }
        }
    }

    private fun loadUserInfoValue() {
        val userData: MutableList<String> = mDataManager.preferencesManagers
            .loadUserProfileData()
        for (i in userData.indices) {
            mUserInfoViews[i].setText(userData[i])
        }
    }

    private fun saveUserInfoValue() {
        val userData: MutableList<String> = ArrayList()
        for (userFieldView in mUserInfoViews) {
            userData.add(userFieldView.text.toString())
        }
        mDataManager.preferencesManagers.saveUserProfileData(userData)
    }

    private fun loadPhotoFromGallery() {

    }

    private fun loadPhotoFromCamera() {
        var photoFile: File? = null
        val takeCaptureInt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            photoFile = createImageFile()
        } catch (e: IOException) {
            e.printStackTrace()
            //TODO: Обробити помилку
        }
        if (photoFile != null){
            //TODO: Передавати фотофайл в інтент
            takeCaptureInt.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(photoFile))
        }
    }

    private fun showProfilePlaceholder() {
        binding.rlProfilePlaceholder.visibility = View.VISIBLE
    }

    private fun hideProfilePlaceholder() {
        binding.rlProfilePlaceholder.visibility = View.GONE
    }

    private fun lockToolbar() {
        mAppBarLayout?.setExpanded(true, true)
        mAppBarParam?.scrollFlags = 0
        binding.ctlMain.layoutParams = mAppBarParam
    }

    private fun unlockToolbar() {
        mAppBarParam?.scrollFlags = SCROLL_FLAG_SCROLL.or(SCROLL_FLAG_EXIT_UNTIL_COLLAPSED)
        binding.ctlMain.layoutParams = mAppBarParam
    }
}


