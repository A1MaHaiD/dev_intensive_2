package com.softdesign.devintensive2.data.managers;

import android.content.Context;

import com.softdesign.devintensive2.data.network.PicassoCache;
import com.softdesign.devintensive2.data.network.RestService;
import com.softdesign.devintensive2.data.network.ServiceGenerator;
import com.softdesign.devintensive2.data.network.req.UserLoginReq;
import com.softdesign.devintensive2.data.network.res.UploadPhotoRes;
import com.softdesign.devintensive2.data.network.res.UserListRes;
import com.softdesign.devintensive2.data.network.res.UserModelRes;
import com.softdesign.devintensive2.data.storage.models.DaoSession;
import com.softdesign.devintensive2.data.storage.models.User;
import com.softdesign.devintensive2.data.storage.models.UserDao;
import com.softdesign.devintensive2.utils.DevIntensive2Application;
import com.squareup.picasso.Picasso;

import org.greenrobot.greendao.Property;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class DataManager {
    private static DataManager INSTANCE = null;
    private Picasso mPicasso;

    private Context mContext;
    private PreferencesManager mPreferencesManager;
    private RestService mRestService;

    private DaoSession mDaoSession;


    public DataManager() {
        this.mPreferencesManager = new PreferencesManager();
        this.mContext = DevIntensive2Application.getContext();
        this.mRestService = ServiceGenerator.createService(RestService.class);
        this.mPicasso = new PicassoCache(mContext).getPicassoInstance();
        this.mDaoSession = DevIntensive2Application.getsDaoSession();
    }

    public static DataManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataManager();
        }
        return INSTANCE;
    }

    public PreferencesManager getPreferencesManagers() {
        return mPreferencesManager;
    }

    public Context getContext() {
        return mContext;
    }

    public Picasso getPicasso() {
        return mPicasso;
    }


    //Region ===============  Network  ==========================

    public Call<UserModelRes> loginUser(
//            String lastModified,
            UserLoginReq userLoginReq
    ) {
        return mRestService.loginUser(
//                lastModified,
                userLoginReq);
    }

    public Call<UploadPhotoRes> uploadPhoto(String userId, MultipartBody.Part photoFile) {
        return mRestService.uploadPhoto(userId, photoFile);
    }

    public Call<UserListRes> getUserListFromNetwork() {
        return mRestService.getUserList();

    }

    //Endregion  ================================================

    //Region ===============  Database  ==========================


    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public List<User> getUserListFromDb() {
        List<User> userList = new ArrayList<>();

        try {
            userList = mDaoSession.queryBuilder(User.class)
                    .where(Properties.CodeLines.gt(0))
                    .orderDesc(Properties.CodeLines)
                    .build()
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    public static class Properties {
        public static Property CodeLines;
        public static Property Rating;
        public static Property SearchName;
    }

    public List<User> getUserListByName(String query) {

        List<User> userList = new ArrayList<>();
        try {
            userList = mDaoSession.queryBuilder(User.class)
                    .where(Properties.Rating.gt(0), UserDao.Properties.SearchName.like("%" + query.toUpperCase() + "%"))
                    .orderDesc(UserDao.Properties.CodeLines)
                    .build()
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }


    //Endregion  ================================================
}
