package com.example.wxj.my_firstapp.net;

import com.example.wxj.my_firstapp.bean.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by wxj on 2018/5/7.
 */

public interface ApiService {
@GET("news.php")
Call<HttpResult<List<News>>> getNews();
}
