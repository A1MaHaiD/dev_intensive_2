package com.softdesign.devintensive2.data.storage.models;

import com.softdesign.devintensive2.utils.DevIntensive2Application;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;

public class DaoMaster extends AbstractDaoMaster {
    public DaoMaster(Database db, int schemaVersion) {
        super(db, schemaVersion);
    }

    @Override
    public AbstractDaoSession newSession() {
        return null;
    }

    @Override
    public AbstractDaoSession newSession(IdentityScopeType type) {
        return null;
    }

    public static class DevOpenHelper{

        public DevOpenHelper(DevIntensive2Application devIntensive2Application, String s) {

        }
    }
}
