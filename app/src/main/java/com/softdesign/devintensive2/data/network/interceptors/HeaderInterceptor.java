package com.softdesign.devintensive2.data.network.interceptors;

import com.softdesign.devintensive2.data.managers.DataManager;
import com.softdesign.devintensive2.data.managers.PreferencesManager;

import java.io.IOException;
import java.util.prefs.Preferences;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        PreferencesManager pm = DataManager.getInstance().getPreferencesManagers();

        Request original = chain.request();

        Request.Builder requestBuilder = original.newBuilder()
                .header("X-Access-Token", pm.getAuthToken())
                .header("Request-User-Id", pm.getUserId())
                .header("User-Agent", "DevIntensiveApp2");

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
