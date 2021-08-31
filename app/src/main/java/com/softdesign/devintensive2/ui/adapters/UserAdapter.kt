package com.softdesign.devintensive2.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softdesign.devintensive2.R
import com.softdesign.devintensive2.data.network.res.UserData
import com.softdesign.devintensive2.ui.adapters.ViewHolders.UserVH
import com.squareup.picasso.Picasso

class UserAdapter(
    var mUser: List<UserData>?,
    var mCustomClickListener: UserVH.CustomClickListener
) : RecyclerView.Adapter<UserVH>() {

    lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserVH {
        mContext = parent.context
        val convertView: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user_list, parent, false);
        return UserVH(convertView, mCustomClickListener);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: UserVH, position: Int) {
        val user: UserData = mUser!![position];
        Picasso.get()
            .load(user.publicInfo.photo)
            .placeholder(mContext.resources.getDrawable(R.drawable.user_placeholder_24))
            .error(mContext.resources.getDrawable(R.drawable.user_placeholder_24))
            .into(holder.userPhoto)

        holder.mFullName.text = user.getFullName()
        holder.mRating.text = user.profileValues.rait.toString()
        holder.mCodeLine.text = user.profileValues.linesCode.toString()
        holder.mProjects.text = user.profileValues.projects.toString()

        if (user.publicInfo.bio.isEmpty()) {
            holder.mAbout.visibility = View.GONE
        } else {
            holder.mAbout.visibility = View.VISIBLE
            holder.mAbout.text = user.publicInfo.bio
        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}