package com.softdesign.devintensive2.data.storage.models;

import com.softdesign.devintensive2.data.network.res.Repo;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;

@Entity(active = true, nameInDb = "REPOSITORIES")
public class Repository {

    @Id
    private Long id;

    @NotNull
    @Unique
    private String remoteId;

    private String repositoryName;

    private String userRemoteId;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 332345895)
    private transient RepositoryDao myDao;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    public Repository(Repo repositoryRes,
                      String userId) {
        this.repositoryName = repositoryRes.getGit();
        this.userRemoteId = userId;
        this.remoteId = repositoryRes.getId();
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context
     */
    @Generated(hash = 1942399219)
    public void refresh() {
        if (myDao == null) {
            throw new  DaoException("Entity is detached from DAO context");
        }
        myDao.refresh();
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null){
            throw new  DaoException("Entity is detached from DAO context");
        }
        myDao.update();
    }


}
