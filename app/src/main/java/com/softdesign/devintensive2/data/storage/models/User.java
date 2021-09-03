package com.softdesign.devintensive2.data.storage.models;

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

    private int codeLines;

    private int projects;

    private String  bio;

    @ToMany(joinProperties = {
            @JoinProperty(name = "remoteId",
            referencedName = "userRemotedId")
    })
    private List<Repository> repositories;


}
