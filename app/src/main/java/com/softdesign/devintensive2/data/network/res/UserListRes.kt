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

data class UserData(

    @SerializedName("_id")
    @Expose
    val id: String,
    @SerializedName("first_name")
    @Expose
    val firstName: String,
    @SerializedName("second_name")
    @Expose
    val secondName: String,
    @SerializedName("__v")
    @Expose
    val v: Int,
    @SerializedName("repositories")
    @Expose
    val repositories: Repositories,
    @SerializedName("profileValues")
    @Expose
    val profileValues: ProfileValues,
    @SerializedName("publicInfo")
    @Expose
    val publicInfo: PublicInfo,
    @SerializedName("specialization")
    @Expose
    val specialization: String,
    @SerializedName("updated")
    @Expose
    val updated: String
)
