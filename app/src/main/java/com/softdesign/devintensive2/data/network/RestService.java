package com.softdesign.devintensive2.data.network;

import com.softdesign.devintensive2.data.network.req.UserLoginReq;
import com.softdesign.devintensive2.data.network.res.UploadPhotoRes;
import com.softdesign.devintensive2.data.network.res.UserListRes;
import com.softdesign.devintensive2.data.network.res.UserModelRes;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface RestService {

//    @Headers({
//            "Custom-Header : my header Value"
//    })

    @POST("login")
    Call<UserModelRes> loginUser (
//            @Header("Last-Modified") String lastMod,
                                  @Body UserLoginReq req);

    @Multipart
    @POST("user/{userId}/publicValues/profilePhoto")
    Call<UploadPhotoRes> uploadPhoto(@Path("userId") String userId,
                                     @Part MultipartBody.Part file);

    @GET("user/list?orderBy=rating")
    Call<UserListRes> getUserList();

//    @GET("users/{user}/repos")
//    Call<List<Repo>> listRepos(@Path("user") String user);
}
