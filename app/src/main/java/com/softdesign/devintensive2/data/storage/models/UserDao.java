package com.softdesign.devintensive2.data.storage.models;

import com.softdesign.devintensive2.data.managers.DataManager;
import com.softdesign.devintensive2.data.network.res.UserData;

import org.greenrobot.greendao.Property;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UserDao extends User {
    public static DataManager.Properties Properties;

    public UserDao(UserData userRes) {
        super(userRes);
    }

    public void insertOrReplace(List<User> allUsers) {

    }

    public void insertOrReplaceInTx(Iterable<Repository> repositoryIterable){

    }

    public void insertOrReplaceInTx(@NotNull List<User> allUsers) {

    }

}
