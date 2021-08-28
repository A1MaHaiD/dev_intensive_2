package com.softdesign.devintensive2.data.network;

import com.softdesign.devintensive2.data.network.req.UserLoginReq;
import com.softdesign.devintensive2.data.network.res.UserModelRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RestService {

    @Headers({
            "Custom-Header : my header Value"
    })

    @POST("login")
    Call<UserModelRes> loginUser (
//            @Header("Last-Modified") String lastMod,
                                  @Body UserLoginReq req);

//    @GET("users/{user}/repos")
//    Call<List<Repo>> listRepos(@Path("user") String user);
}
