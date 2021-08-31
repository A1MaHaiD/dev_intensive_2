package com.softdesign.devintensive2.data.storage.models;

import com.softdesign.devintensive2.data.network.res.Repo;
import com.softdesign.devintensive2.data.network.res.UserData;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private String mPhoto;
    private String mFullName;
    private String mRating;
    private String mCodeLines;
    private String mProjects;
    private String mAbout;
    private List<String> mRepositories;


    private UserDTO(UserData userData){
        List<String> repoLink = new ArrayList<>();

        mPhoto = userData.getPublicInfo().getPhoto();
        mFullName = userData.getFullName();
        mRating = String.valueOf(userData.getProfileValues().getRait());
        mCodeLines = String.valueOf(userData.getProfileValues().getLinesCode());
        mProjects = String.valueOf(userData.getProfileValues().getProjects());
        mAbout = userData.getPublicInfo().getBio();
        for (Repo gitLink: userData.getRepositories().getRepo()){
            repoLink.add(gitLink.getGit());
        }
        mRepositories = repoLink;
    }
}
