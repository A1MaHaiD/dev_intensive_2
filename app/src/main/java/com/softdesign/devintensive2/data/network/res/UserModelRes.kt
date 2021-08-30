package com.softdesign.devintensive2.data.network.res

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class UserModelRes(
    @SerializedName("success")
    @Expose
    val success: Boolean,
    @SerializedName("data")
    @Expose
    val data: Data
)

data class Data(
    @SerializedName("user")
    @Expose
    val user: User,
    @SerializedName("token")
    @Expose
    val token: String
)

data class Contacts(
    @SerializedName("vk")
    @Expose
    val vk: String,
    @SerializedName("phone")
    @Expose
    val phone: String,
    @SerializedName("email")
    @Expose
    val email: String,
    @SerializedName("updated")
    @Expose
    val updated: String
)

data class ProfileValues(
    @SerializedName("homeTask")
    @Expose
    val homeTask: Int,
    @SerializedName("projects")
    @Expose
    val projects: Int,
    @SerializedName("linesCode")
    @Expose
    val linesCode: Int,
    @SerializedName("rait")
    @Expose
    val rait: Int,
    @SerializedName("updated")
    @Expose
    val updated: String
)

data class PublicInfo(
    @SerializedName("bio")
    @Expose
    val bio: String,
    @SerializedName("avatar")
    @Expose
    val avatar: String,
    @SerializedName("photo")
    @Expose
    val photo: String,
    @SerializedName("updated")
    @Expose
    val updated: String
)

data class Repo(
    @SerializedName("_id")
    @Expose
    val id: String,
    @SerializedName("git")
    @Expose
    val git: String,
    @SerializedName("title")
    @Expose
    val title: String
)

data class Repositories(
    @SerializedName("repo")
    @Expose
    val repo: List<Repo> = ArrayList<Repo>(),
    @SerializedName("updated")
    @Expose
    val updated: String
)

data class User(
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
    @SerializedName("contacts")
    @Expose
    val contacts: Contacts,
    @SerializedName("profileValues")
    @Expose
    val profileValues: ProfileValues,
    @SerializedName("publicInfo")
    @Expose
    val publicInfo: PublicInfo,
    @SerializedName("specialization")
    @Expose
    val specialization: String,
    @SerializedName("role")
    @Expose
    val role: String,
    @SerializedName("updated")
    @Expose
    val updated: String
)
