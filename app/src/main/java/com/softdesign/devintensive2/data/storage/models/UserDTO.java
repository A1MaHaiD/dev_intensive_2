package com.softdesign.devintensive2.data.storage.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.softdesign.devintensive2.data.network.res.Repo;
import com.softdesign.devintensive2.data.network.res.UserData;

import java.util.ArrayList;
import java.util.List;

public class UserDTO implements Parcelable {

    private String mPhoto;
    private String mFullName;
    private String mRating;
    private String mCodeLines;
    private String mProjects;
    private String mAbout;
    private List<String> mRepositories;


    public UserDTO(UserData userData) {
        List<String> repoLink = new ArrayList<>();

        mPhoto = userData.getPublicInfo().getPhoto();
        mFullName = userData.getFullName();
        mRating = String.valueOf(userData.getProfileValues().getRait());
        mCodeLines = String.valueOf(userData.getProfileValues().getLinesCode());
        mProjects = String.valueOf(userData.getProfileValues().getProjects());
        mAbout = userData.getPublicInfo().getBio();
        for (Repo gitLink : userData.getRepositories().getRepo()) {
            repoLink.add(gitLink.getGit());
        }
        mRepositories = repoLink;
    }

    protected UserDTO(Parcel in) {
        mPhoto = in.readString();
        mFullName = in.readString();
        mRating = in.readString();
        mCodeLines = in.readString();
        mProjects = in.readString();
        mAbout = in.readString();
        if (in.readByte() == 0x01) {
            mRepositories = new ArrayList<String>();
            in.readList(mRepositories,String.class.getClassLoader());
        } else {
            mRepositories = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mPhoto);
        dest.writeString(mFullName);
        dest.writeString(mRating);
        dest.writeString(mCodeLines);
        dest.writeString(mProjects);
        dest.writeString(mAbout);
        if (mRepositories == null){
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(mRepositories);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<UserDTO> CREATOR = new Parcelable.Creator<UserDTO>(){
        @Override
        public UserDTO createFromParcel(Parcel in){
            return new UserDTO(in);
        }

        @Override
        public UserDTO[] newArray(int size){
            return new UserDTO[size];
        }
    };

    public String getPhoto() {
        return mPhoto;
    }

    public String getFullName() {
        return mFullName;
    }

    public String getRating() {
        return mRating;
    }

    public String getCodeLines() {
        return mCodeLines;
    }

    public String getProjects() {
        return mProjects;
    }

    public String getAbout() {
        return mAbout;
    }

    public List<String> getRepositories() {
        return mRepositories;
    }
}
