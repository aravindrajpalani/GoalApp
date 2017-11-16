package com.social.goalapp.data.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.social.goalapp.utils.AppConstants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Aravindraj on 6/8/2017.
 */
public class APIClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
/*
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
*/
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.followRedirects(false);
        OkHttpClient httpClient = builder.build();

        retrofit = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(AppConstants.API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}