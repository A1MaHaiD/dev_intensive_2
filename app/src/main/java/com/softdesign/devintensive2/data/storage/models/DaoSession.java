package com.softdesign.devintensive2.data.storage.models;

import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;

public class DaoSession extends AbstractDaoSession {

    public final UserDao mUserDao;
    public final RepositoryDao mRepositoryDao;

    public DaoSession(Database db,UserDao userDao, RepositoryDao repositoryDao) {
        super(db);
        this.mUserDao = userDao;
        this.mRepositoryDao = repositoryDao;
    }

    public UserDao getUserDao() {
        return mUserDao;
    }

    public RepositoryDao getRepositoryDao() {
        return mRepositoryDao;
    }

}
