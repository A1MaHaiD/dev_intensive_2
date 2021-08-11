package com.softdesign.devintensive2.data.network;

import com.softdesign.devintensive2.data.network.interceptors.HeaderInterceptor;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder sBuilder =
            new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create());

//    public static <S> S createService (Class<S> serviceClass){
//
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        httpClient.addInterceptor(new HeaderInterceptor());
//        httpClient.addInterceptor(logging);
//    }

//    String run(String url) throws IOException {
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//
//        try (Response response = httpClient.newCall(request).execute()) {
//            return response.body().string();
//        }
//    }


//    RestService service = sBuilder.create(RestService.class);
}
