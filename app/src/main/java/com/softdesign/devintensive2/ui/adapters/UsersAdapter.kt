package com.softdesign.devintensive2.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softdesign.devintensive2.R
import com.softdesign.devintensive2.data.managers.DataManager
import com.softdesign.devintensive2.data.network.res.UserData
import com.softdesign.devintensive2.ui.adapters.ViewHolders.UserVH
import com.softdesign.devintensive2.utils.ConstantManager
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.lang.Exception

class UsersAdapter(
    var mUser: List<UserData>?,
    var mCustomClickListener: UserVH.CustomClickListener
) : RecyclerView.Adapter<UserVH>() {

    private val TAG: String = ConstantManager.TAG_PREFIX + " UsersAdapter"



    lateinit var mContext: Context

    var mDummy: Drawable? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserVH {
        mContext = parent.context
        val convertView: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user_list, parent, false);
        return UserVH(convertView, mCustomClickListener);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: UserVH, position: Int) {
        val user: UserData = mUser!![position]
        val userPhoto: String
        if (user.publicInfo.photo.isEmpty()) {
            userPhoto = "null"
            Log.e(TAG,"onBindViewHolder: user with name" +
                    " ${user.getFullName()} has empty name")
        } else{
            userPhoto = user.publicInfo.photo
        }

        DataManager.getInstance().picasso
            .load(userPhoto)
            .error(holder.mDummy)
            .placeholder(holder.mDummy)
            .fit()
            .centerCrop()
            .networkPolicy(NetworkPolicy.OFFLINE)
            .into(holder.userPhoto , object : Callback{
                override fun onSuccess() {
                    Log.d(TAG," load from cache")
                }

                override fun onError(e: Exception?) {
                    DataManager.getInstance().picasso
                        .load(userPhoto)
                        .error(holder.mDummy)
                        .placeholder(holder.mDummy)
                        .fit()
                        .centerCrop()
                        .into(holder.userPhoto, object:Callback{
                            override fun onSuccess() {
                                TODO("Not yet implemented")
                            }

                            override fun onError(e: Exception?) {
                                Log.d(TAG, "Could not fetch image")
                            }

                        })
                }

            })

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