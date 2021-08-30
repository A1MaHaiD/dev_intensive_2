package com.softdesign.devintensive2.data.network.res

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserListRes(

    @SerializedName("success")
    @Expose
    val success: Boolean,
    @SerializedName("data")
    @Expose
    val data: List<UserData> = ArrayList()
)


