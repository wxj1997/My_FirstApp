package com.example.wxj.my_firstapp.ui.fragement.circleFragementChild;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wxj.my_firstapp.R;
import com.example.wxj.my_firstapp.adapter.DataListAdapter;
import com.example.wxj.my_firstapp.bean.News;
import com.example.wxj.my_firstapp.net.HttpResult;
import com.example.wxj.my_firstapp.net.RetrofitUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
    private RecyclerView recyclerView;
    private View view;
    private List<News> mNews;
    private DataListAdapter dataListAdapter;

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
        RetrofitUtil.getNewsByRetrofit(new Callback<HttpResult<List<News>>>() {
            @Override
            public void onResponse(Call<HttpResult<List<News>>> call,
                                   Response<HttpResult<List<News>>> response) {


              dataListAdapter.addData(response.body().getData());


            }

            @Override
            public void onFailure(Call<HttpResult<List<News>>> call, Throwable t) {

            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dataListAdapter = new DataListAdapter(R.layout.item_view, mNews);
        recyclerView.setAdapter(dataListAdapter);
    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    }

}
