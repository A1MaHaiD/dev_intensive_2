package com.softdesign.devintensive2.data.network;

import com.softdesign.devintensive2.data.network.req.UserLoginReq;
import com.softdesign.devintensive2.data.network.res.UserModelRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RestService {
    @POST("login")
    Call<UserModelRes> loginUser (@Body UserLoginReq req);

//    @GET("users/{user}/repos")
//    Call<List<Repo>> listRepos(@Path("user") String user);
}
