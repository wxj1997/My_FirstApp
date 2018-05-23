package com.example.wxj.my_firstapp.net;

import com.example.wxj.my_firstapp.bean.News;
import com.example.wxj.my_firstapp.bean.Video;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;



public interface ApiService {
    @GET("news.php")
    Flowable<HttpResult<List<News>>> getNews();

    @GET("video.php")
    Flowable<HttpResult<List<Video>>> getVideo();
}
