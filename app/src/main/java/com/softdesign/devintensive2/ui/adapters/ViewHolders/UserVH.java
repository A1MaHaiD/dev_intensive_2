package com.softdesign.devintensive2.ui.adapters.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.softdesign.devintensive2.R;
import com.softdesign.devintensive2.ui.views.AspectRatioImageView;

public class UserVH extends RecyclerView.ViewHolder {

    public AspectRatioImageView userPhoto;
    public TextView mFullName, mRating, mCodeLine, mProjects, mAbout;

    public UserVH(View itemView) {
        super(itemView);
        userPhoto = itemView.findViewById(R.id.ariv_user_photo);
        mFullName = itemView.findViewById(R.id.tv_user_full_name);
        mRating = itemView.findViewById(R.id.tv_rating);
        mCodeLine = itemView.findViewById(R.id.tv_code_line);
        mProjects = itemView.findViewById(R.id.tv_project);
        mAbout = itemView.findViewById(R.id.tv_about);

    }
}
