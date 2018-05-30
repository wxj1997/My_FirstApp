package com.example.wxj.my_firstapp.net;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class RetrofitManger {
    private static RetrofitManger mInstance = null;
    private Retrofit mRetrofit;

    private RetrofitManger() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.2.105")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

    }

    public static RetrofitManger getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitManger.class) {
                mInstance = new RetrofitManger();
            }
        }
        return mInstance;
    }

    public <T> T getService(Class<T> tClass) {
        return mRetrofit.create(tClass);
    }
}
