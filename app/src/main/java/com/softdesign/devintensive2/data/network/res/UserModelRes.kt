package com.softdesign.devintensive2.data.network.res

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class UserModelRes(
    @SerializedName("success")
    @Expose
    val success:Boolean,
    @SerializedName("data")
    @Expose
    val data: Data
)
data class Data(
    @SerializedName("user")
    @Expose
    private val user: User,
    @SerializedName("token")
    @Expose
    private val token: String
)
data class Contacts(
    @SerializedName("vk")
    @Expose
    private val vk: String,
    @SerializedName("phone")
    @Expose
    private val phone: String,
    @SerializedName("email")
    @Expose
    private val email: String,
    @SerializedName("updated")
    @Expose
    private val updated: String
)
data class ProfileValues(
    @SerializedName("homeTask")
    @Expose
    private val homeTask:Int,
    @SerializedName("projects")
    @Expose
    private val projects:Int,
    @SerializedName("linesCode")
    @Expose
    private val linesCode:Int,
    @SerializedName("rait")
    @Expose
    private val rait:Int,
    @SerializedName("updated")
    @Expose
    private val updated:String
)
data class PublicInfo(
    @SerializedName("bio")
    @Expose
    private val bio:String,
    @SerializedName("avatar")
    @Expose
    private val avatar:String,
    @SerializedName("photo")
    @Expose
    private val photo:String,
    @SerializedName("updated")
    @Expose
    private val updated:String
)
data class Repo(
    @SerializedName("_id")
    @Expose
    private val id:String,
    @SerializedName("git")
    @Expose
    private val git:String,
    @SerializedName("title")
    @Expose
    private val title:String
)
data class Repositories(
    @SerializedName("repo")
    @Expose
    private val repo:List<Repo> = ArrayList<Repo>(),
    @SerializedName("updated")
    @Expose
    private val updated:String
)
data class User(
    @SerializedName("_id")
    @Expose
    private val id:String,
    @SerializedName("first_name")
    @Expose
    private val firstName:String,
    @SerializedName("second_name")
    @Expose
    private val secondName:String,
    @SerializedName("__v")
    @Expose
    private val v:Int,
    @SerializedName("repositories")
    @Expose
    private val repositories: Repositories,
    @SerializedName("contacts")
    @Expose
    private val contacts:Contacts,
    @SerializedName("profileValues")
    @Expose
    private val profileValues: ProfileValues,
    @SerializedName("publicInfo")
    @Expose
    private val publicInfo: PublicInfo,
    @SerializedName("specialization")
    @Expose
    private val specialization:String,
    @SerializedName("role")
    @Expose
    private val role:String,
    @SerializedName("updated")
    @Expose
    private val updated:String
)
