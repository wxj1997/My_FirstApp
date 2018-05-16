package com.example.wxj.my_firstapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wxj.my_firstapp.R;


import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;


public class VideoActivity extends AppCompatActivity {
    private JZVideoPlayerStandard jzVideoPlayerStandard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initView();
        initData();


    }

    private void initData() {
        Intent intent = getIntent();
        String videoName=intent.getStringExtra("Video_Name");
        String videoUrl=intent.getStringExtra("Video_Url");
        jzVideoPlayerStandard.setUp
                (videoUrl
                        , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, videoName);

    }

    private void initView() {
        jzVideoPlayerStandard = (JZVideoPlayerStandard) findViewById(R.id.videoplayer);
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
}

