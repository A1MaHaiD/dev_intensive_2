package com.softdesign.devintensive2.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.softdesign.devintensive2.R
import com.softdesign.devintensive2.ui.adapters.ViewHolders.UserVH

class UserAdapter : RecyclerView.Adapter<UserVH>() {

    lateinit var mUser: ArrayList<Object>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserVH {
        val convertView: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user_list, parent, false);
        return UserVH(convertView);
    }

    override fun onBindViewHolder(holder: UserVH, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}