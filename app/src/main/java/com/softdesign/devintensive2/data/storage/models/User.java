package com.softdesign.devintensive2.data.storage.models;

import com.softdesign.devintensive2.data.network.res.UserData;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Unique;
import java.util.List;

@Entity(active = true, nameInDb ="USERS")
public class User {

    @Id
    private Long id;

    @NotNull
    @Unique
    private String removeId;

    private String photo;

    @NotNull
    @Unique
    private String fullName;

    @NotNull
    @Unique
    private String searchName;

    private int rating;

    private int codeLines;

    private int projects;

    private String  bio;

    @ToMany(joinProperties = {
            @JoinProperty(name = "remoteId",
            referencedName = "userRemotedId")
    })
    private List<Repository> repositories;


    public User(UserData userRes) {
        this.removeId = userRes.getId();
        this.photo = userRes.getPublicInfo().getPhoto();
        this.fullName = userRes.getFullName();
        this.searchName = userRes.getFullName().toUpperCase();
        this.rating = userRes.getProfileValues().getRait();
        this.codeLines = userRes.getProfileValues().getLinesCode();
        this.projects = userRes.getProfileValues().getProjects();
        this.bio  = userRes.getPublicInfo().getBio();
    }

    public Long getId() {
        return id;
    }

    public String getRemoveId() {
        return removeId;
    }

    public String getPhoto() {
        return photo;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSearchName() {
        return searchName;
    }

    public int getRating() {
        return rating;
    }

    public int getCodeLines() {
        return codeLines;
    }

    public int getProjects() {
        return projects;
    }

    public String getBio() {
        return bio;
    }

    public List<Repository> getRepositories() {
        return repositories;
    }
}
