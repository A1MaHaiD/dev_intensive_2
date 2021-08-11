package com.softdesign.devintensive2.ui.activities

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.Manifest
import android.content.ContentValues
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.ActionBar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
import com.squareup.picasso.Picasso
import java.io.File
import java.io.IOException
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
    private var mPhotoFile: File? = null
    private var mSelectedImage: Uri? = null

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
//        @Picasso don't work && crashed app
//        Picasso.get()
//            .load(mDataManager.preferencesManagers.loadUserPhoto())
//            .placeholder(R.drawable.user_photo) // TODO: Зробити placeholder та transform + crop
//            .into(binding.ivUserPhoto)

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
            showSnackBar("onClickPlaceholder")
            onCreateDialog(ConstantManager.LOAD_PROFILE_PHOTO)
        }
    }

    override fun onCreateDialog(id: Int): AlertDialog? {
        return when (id) {
            ConstantManager.LOAD_PROFILE_PHOTO -> {
                val selectItem = arrayOf(
                    getString(R.string.`user.profile_dialog_gallery`),
                    getString(R.string.`user.profile_dialog_camera`),
                    getString(R.string.`user.profile_dialog_cancel`)
                )
                val builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.`user.profile_title`))
                builder.setItems(selectItem) { dialog, choiceItem ->
                    when (choiceItem) {
                        0 -> {
                            //TODO: Загрузити фото з галереї
                            loadPhotoFromGallery()
//                            showSnackBar("Загрузити фото з галереї")
                        }
                        1 -> {
                            //TODO: Загрузити фото з камери
                            loadPhotoFromCamera()
//                            showSnackBar("Загрузити фото з камери")
                        }
                        2 -> {
                            dialog.cancel()
                            //TODO: Відміна
//                            showSnackBar("Відміна")
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
        val image = File.createTempFile(imageFileName, ".jpg", storageDir)
        val values = ContentValues()
        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis())
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        values.put(MediaStore.MediaColumns.DATA, image.absolutePath)
        this.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        return image
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            ConstantManager.REQUEST_GALLERY_PICTURE ->
                if (resultCode == RESULT_OK && data != null) {
                    mSelectedImage = data.data
                    insertProfileImage(mSelectedImage)
                }
            ConstantManager.REQUEST_CAMERA_PICTURE ->
                if (resultCode == RESULT_OK && mPhotoFile != null) {
                    mSelectedImage = Uri.fromFile(mPhotoFile)
                    insertProfileImage(mSelectedImage)
                }
        }
    }

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
                userValue.isFocusable = true
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
                userValue.isFocusable = false
                userValue.isFocusableInTouchMode = false
                hideProfilePlaceholder()
                unlockToolbar()
                binding.ctlMain.setExpandedTitleColor(resources.getColor(R.color.white))
                saveUserInfoValue()
            }
        }
    }

    private fun loadUserInfoValue() {
        val userData: List<String?> = mDataManager.preferencesManagers
            .loadUserProfileData()
        for (i in userData.indices) {
            mUserInfoViews[i].setText(userData[i])
        }
    }

    private fun saveUserInfoValue() {
        val userData: MutableList<String?> = ArrayList()
        for (userFieldView in mUserInfoViews) {
            userData.add(userFieldView.text.toString())
        }
        mDataManager.preferencesManagers.saveUserProfileData(userData)
    }

    private fun loadPhotoFromGallery() {
        val takeGalleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        takeGalleryIntent.type = "image/*"
        startActivityForResult(
            Intent
                .createChooser(takeGalleryIntent, getString(R.string.`user.profile_chose_photo`)),
            ConstantManager.REQUEST_GALLERY_PICTURE
        )
    }

    private fun loadPhotoFromCamera() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {


            val takeCaptureInt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                mPhotoFile = createImageFile()
            } catch (e: IOException) {
                e.printStackTrace()
                //TODO: Обробити помилку
            }
            if (mPhotoFile != null) {
                //TODO: Передавати фотофайл в інтент
                takeCaptureInt.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mPhotoFile))
                startActivityForResult(takeCaptureInt, ConstantManager.REQUEST_CAMERA_PICTURE)
            }
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), ConstantManager.CAMERA_REQUEST_PERMISSION_CODE
            )
            Snackbar.make(
                binding.coordinatorLayoutMain,
                getString(R.string.`message.snack_bar`),
                Snackbar.LENGTH_LONG
            )
                .setAction(getString(R.string.`message.snack_bar.accept`)) {
                    openApplicationSettings()
                }.show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == ConstantManager.CAMERA_REQUEST_PERMISSION_CODE && grantResults.size == 2) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //TODO: Тут обробляемо розширення (розширення отримано)
                // наприклад вивести повідомлення або обробити якусь логіку, якщо потрібно
            }
        } else if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            //TODO: Тут обробляемо розширення (розширення отримано)
            // наприклад вивести повідомлення або обробити якусь логіку, якщо потрібно
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

    private fun insertProfileImage(selectedImage: Uri?) {
        Picasso.get()
            .load(selectedImage)
            .into(binding.ivUserPhoto)
        // TODO: Зробити placeholder та transform + crop
        mDataManager.preferencesManagers.saveUserPhoto(selectedImage)
    }

    private fun openApplicationSettings() {
        val appSettingIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.parse("package:$packageName")
        )
        startActivityForResult(appSettingIntent, ConstantManager.PERMISSION_REQUEST_SETTINGS_CODE)
    }
}


