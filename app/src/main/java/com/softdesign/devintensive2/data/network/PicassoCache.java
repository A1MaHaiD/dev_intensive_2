package com.softdesign.devintensive2.data.network;

import android.content.Context;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class PicassoCache {
    private Context mContext;
    private Picasso mPicassoInstance;

    public PicassoCache (Context context){
        this.mContext = context;
        OkHttp3Downloader okHttp3Downloader = new OkHttp3Downloader(context,Integer.MAX_VALUE);
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(okHttp3Downloader);
    }

    public Picasso getPicassoInstance(){

        if (mPicassoInstance == null){
            new PicassoCache(mContext);
            return mPicassoInstance;
        }
        return mPicassoInstance;
    }

}
