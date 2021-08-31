package com.softdesign.devintensive2.ui.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class RepositoriesAdapter:BaseAdapter() {

    var mRepositoriesList:List<String>? = null

    override fun getCount(): Int {
        return mRepositoriesList!!.size
    }

    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(position: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("Not yet implemented")
    }
}