package com.example.wxj.my_firstapp.ui.fragement.circleFragementChild;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.ImageView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wxj.my_firstapp.R;

import com.example.wxj.my_firstapp.activity.VideoActivity;
import com.example.wxj.my_firstapp.adapter.VideoAdapter;
import com.example.wxj.my_firstapp.bean.Video;
import com.example.wxj.my_firstapp.net.HttpResult;
import com.example.wxj.my_firstapp.net.HttpUtil;
/*import com.example.wxj.my_firstapp.net.RetrofitUtil;*/

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment implements BaseQuickAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private View view;
    private List<Video> videos;
    private VideoAdapter videoAdapter;


    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_video, container, false);
        initView();
        initData();
        return view;
    }

    private void initData() {

        videos = new ArrayList<>();
        /*RetrofitUtil.getVideoByRetrofit(new Callback<HttpResult<List<Video>>>() {
            @Override
            public void onResponse(Call<HttpResult<List<Video>>> call, Response<HttpResult<List
                    <Video>>> response) {
                videoAdapter.addData(response.body().getData());
            }

            @Override
            public void onFailure(Call<HttpResult<List<Video>>> call, Throwable t) {

            }
        });*/
        HttpUtil.getIntance().getVideo(new Subscriber<List<Video>>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(List<Video> videos) {
                videoAdapter.addData(videos);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        videoAdapter = new VideoAdapter(R.layout.item_view_video, videos);
        recyclerView.setAdapter(videoAdapter);
        videoAdapter.setOnItemClickListener(this);


    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Video video = (Video) adapter.getItem(position);
        Intent intent = new Intent(getActivity(), VideoActivity.class);
        intent.putExtra("Video_Id",video.getId());
        intent.putExtra("Video_Name", video.getName());
        intent.putExtra("Video_Url", video.getVideoUrl());
        intent.putExtra("Video_ImageUrl", video.getVideoImageUrl());
        startActivity(intent);
    }
}



