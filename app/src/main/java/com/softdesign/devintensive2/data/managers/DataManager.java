package com.softdesign.devintensive2.data.managers;

import android.content.Context;

import com.softdesign.devintensive2.data.network.RestService;
import com.softdesign.devintensive2.data.network.ServiceGenerator;
import com.softdesign.devintensive2.data.network.req.UserLoginReq;
import com.softdesign.devintensive2.data.network.res.UserModelRes;
import com.softdesign.devintensive2.utils.DevIntensive2Application;

import retrofit2.Call;

public class DataManager {
    private static DataManager INSTANCE = null;

    private Context mContext;
    private PreferencesManager mPreferencesManager;
    private RestService mRestService;


    public DataManager() {
        this.mPreferencesManager = new PreferencesManager();
        this.mContext = DevIntensive2Application.getContext();
        this.mRestService = ServiceGenerator.createService(RestService.class);
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

    //Region ===============  Network  ==========================

    public Call<UserModelRes> loginUser(
//            String lastModified,
            UserLoginReq userLoginReq
    ) {
        return mRestService.loginUser(
//                lastModified,
                userLoginReq);
    }

    //Endregion  ================================================

    //Region ===============  Data  ==========================

//    public Call<UserModelRes> login(UserLoginReq userLoginReq){
//        return mRestService.loginUser(userLoginReq);
//    }

    //Endregion  ================================================
}
