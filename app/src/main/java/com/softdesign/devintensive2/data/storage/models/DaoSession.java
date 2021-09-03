package com.softdesign.devintensive2.data.storage.models;

import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;

public class DaoSession extends AbstractDaoSession {
    public DaoSession(Database db) {
        super(db);
    }
}
