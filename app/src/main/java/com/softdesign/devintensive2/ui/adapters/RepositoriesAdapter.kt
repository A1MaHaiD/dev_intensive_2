package com.softdesign.devintensive2.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.softdesign.devintensive2.R

class RepositoriesAdapter(
    private var mContext: Context?,
    private var mRepoList: List<String>?,
    private var mInflater: LayoutInflater = mContext?.getSystemService(
        Context.LAYOUT_INFLATER_SERVICE
    ) as LayoutInflater
) : BaseAdapter() {

    override fun getCount(): Int {
        return mRepoList!!.size
    }

    override fun getItem(position: Int): Any {
        return mRepoList!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemView: View? = convertView

        if (itemView == null) {
            itemView = mInflater.inflate(R.layout.item_repositories_list, parent, false)
        }

        var repoName: TextView = itemView?.findViewById(R.id.et_github) as TextView
        repoName.text = mRepoList!![position]
        return itemView
    }
}