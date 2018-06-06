package com.example.wxj.my_firstapp.ui.fragement.circleFragementChild;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wxj.my_firstapp.R;
import com.example.wxj.my_firstapp.adapter.NewsAdapter;
import com.example.wxj.my_firstapp.bean.News;
import com.example.wxj.my_firstapp.loder.BannerImageLoder;
import com.example.wxj.my_firstapp.net.exception.ServerException;
import com.example.wxj.my_firstapp.net.HttpUtil;
import com.example.wxj.my_firstapp.util.Const;
import com.youth.banner.Banner;
/*import com.example.wxj.my_firstapp.net.RetrofitUtil;*/

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
    private RecyclerView recyclerView;
    private View view;
    private List<News> mNews;
    private NewsAdapter newsAdapter;
    private Banner mBanner;
    private List<String> bannerImageUrl;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_news, container, false);
        initView();
        initData();
        return view;
    }

    private void initData() {
        mNews = new ArrayList<>();
       /* RetrofitUtil.getNewsByRetrofit(new Callback<HttpResult<List<News>>>() {
            @Override
            public void onResponse(Call<HttpResult<List<News>>> call,
                                   Response<HttpResult<List<News>>> response) {


              newsAdapter.addData(response.body().getData());


            }

            @Override
            public void onFailure(Call<HttpResult<List<News>>> call, Throwable t) {

            }
        });*/
        HttpUtil.getIntance().getNews(new Subscriber<List<News>>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(List<News> newses) {
                newsAdapter.addData(newses);
            }

            @Override
            public void onError(Throwable t) {
                if (t instanceof ServerException) {
                    Toast.makeText(getContext(), ((ServerException) t).getMessage(), Toast
                            .LENGTH_SHORT).show();
                }

            }

            @Override
            public void onComplete() {

            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        newsAdapter = new NewsAdapter(R.layout.item_view_news, mNews);
        recyclerView.setAdapter(newsAdapter);
        //轮播图
        bannerImageUrl=new ArrayList<>();
        for (int i=0;i< Const.BANNER_IMAGE_URL.length;i++){
            bannerImageUrl.add(Const.BANNER_IMAGE_URL[i]);
        }

        mBanner.setImageLoader(new BannerImageLoder())
                .setImages(bannerImageUrl)
                .start();


    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mBanner=(Banner)view.findViewById(R.id.banner);
    }

}
