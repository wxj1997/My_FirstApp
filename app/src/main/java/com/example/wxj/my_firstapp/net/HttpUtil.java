package com.example.wxj.my_firstapp.net;


import com.example.wxj.my_firstapp.bean.News;
import com.example.wxj.my_firstapp.bean.Video;
import com.example.wxj.my_firstapp.net.function.HttpResultFunction;

import org.reactivestreams.Subscriber;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HttpUtil {
    private static HttpUtil mHttpUtil = null;
    private ApiService apiService;

    private HttpUtil() {
        apiService = RetrofitManger.getInstance().getService(ApiService.class);
    }

    public static HttpUtil getIntance() {
        if (mHttpUtil == null) {
            synchronized (HttpUtil.class) {
                mHttpUtil = new HttpUtil();
            }
        }
        return mHttpUtil;
    }

    public void getNews(Subscriber<List<News>> subscriber) {
        Flowable<HttpResult<List<News>>> flowable = apiService.getNews();
        flowable.map(new HttpResultFunction<List<News>>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(subscriber);
    }

    public void getVideo(Subscriber<List<Video>> subcriber) {
        Flowable<HttpResult<List<Video>>> flowable = apiService.getVideo();
        flowable.map(new HttpResultFunction<List<Video>>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(subcriber);
    }

}
