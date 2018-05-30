package com.example.wxj.my_firstapp.ui.fragement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wxj.my_firstapp.R;

import com.example.wxj.my_firstapp.adapter.VideoAdapter;
import com.example.wxj.my_firstapp.bean.Video;
import com.example.wxj.my_firstapp.db.DBUtil;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private List<Video> videos;
    private VideoAdapter videoAdapter;


    public FindFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_find, container, false);
        initView();
        initData();
        return view;
    }

    private void initData() {
        videos = DBUtil.getInstance(getContext()).query();
      /*  DBUtil.getInstance(getContext()).delete("后来的我们");
        DBUtil.getInstance(getContext()).delete("不爱我就拉倒");
        DBUtil.getInstance(getContext()).delete("HandClap");*/
        videoAdapter = new VideoAdapter(R.layout.item_view_video, videos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(videoAdapter);
    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    }

}
