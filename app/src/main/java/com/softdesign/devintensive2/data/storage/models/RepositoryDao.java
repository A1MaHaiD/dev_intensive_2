package com.softdesign.devintensive2.data.storage.models;

import com.softdesign.devintensive2.data.network.res.Repo;

import java.util.List;

public class RepositoryDao extends Repository {
    public RepositoryDao(Repo repositoryRes, String userId) {
        super(repositoryRes, userId);
    }

    public void insertOrReplace(List<Repository> allRepositories) {

    }

    public  void insertOrReplaceInTx(Iterable<Repository> repositoryIterable){

    }
}
