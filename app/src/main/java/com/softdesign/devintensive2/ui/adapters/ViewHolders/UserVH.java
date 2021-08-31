package com.softdesign.devintensive2.ui.adapters.ViewHolders;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.softdesign.devintensive2.R;
import com.softdesign.devintensive2.ui.views.AspectRatioImageView;

import org.jetbrains.annotations.NotNull;

public class UserVH extends RecyclerView.ViewHolder implements View.OnClickListener {

    public AspectRatioImageView userPhoto;
    public TextView mFullName, mRating, mCodeLine, mProjects, mAbout;
    protected Button mShowMore;

    private CustomClickListener mListener;
    private Object CustomClickListener;

    public UserVH(View itemView, CustomClickListener customClickListener) {
        super(itemView);
        this.mListener = customClickListener;

        userPhoto = itemView.findViewById(R.id.ariv_user_photo);
        mFullName = itemView.findViewById(R.id.tv_user_full_name);
        mRating = itemView.findViewById(R.id.tv_rating);
        mCodeLine = itemView.findViewById(R.id.tv_code_line);
        mProjects = itemView.findViewById(R.id.tv_project);
        mAbout = itemView.findViewById(R.id.tv_about);
        mShowMore = itemView.findViewById(R.id.b_more_info);

        mShowMore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (CustomClickListener!=null

        ){

        }
    }

    public interface CustomClickListener{

        void onUserItemClickListener(int position);
    }
}
