package com.du.iit.zayed.vlrp_android.adapter;

import com.du.iit.zayed.vlrp_android.Api.DirectionApi;
import com.du.iit.zayed.vlrp_android.Api.VlrpApi;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by BS-86 on 1/28/2016.
 */
public class DirectionApiAdapter {
    public DirectionApi directionApi;
    Retrofit retrofit;
    public static String BASE_URL="https://maps.googleapis.com/maps/";

    public DirectionApiAdapter()
    {
        OkHttpClient okHttpClient=new OkHttpClient();
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.interceptors().add(httpLoggingInterceptor);
        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
        directionApi=retrofit.create(DirectionApi.class);
    }
}
