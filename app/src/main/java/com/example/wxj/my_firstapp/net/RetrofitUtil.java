package com.example.wxj.my_firstapp.net;

import com.example.wxj.my_firstapp.bean.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class RetrofitUtil {
    public static void getNewsByRetrofit(Callback<HttpResult<List<News>>> callback) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2").addConverterFactory
                (GsonConverterFactory.create()).build();
        ApiService apiservice=retrofit.create(ApiService.class);
        Call<HttpResult<List<News>>> call=apiservice.getNews();
        call.enqueue(callback);
    }
}
