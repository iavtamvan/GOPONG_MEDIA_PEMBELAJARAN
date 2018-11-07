package com.iav.go_pong.rest;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private static String BASE_URL = "https://script.google.com/macros/s/AKfycbwS74AT0Z3rKsggbN6viqWsfwyI2tF2lu3Xr_684l4bPo7Ey5ZA/";
    private static Retrofit getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }

    public static ApiService getInstanceRetrofit() {
        return getClient().create(ApiService.class);
    }

}
