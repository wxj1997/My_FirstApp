package com.example.wxj.my_firstapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.wxj.my_firstapp.R;
import com.example.wxj.my_firstapp.bean.Video;
import com.example.wxj.my_firstapp.db.DBUtil;


import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;


public class VideoActivity extends AppCompatActivity implements View.OnClickListener {
    private JZVideoPlayerStandard jzVideoPlayerStandard;
    private ImageView mFavoriteImageView;
    private Video mVideo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initView();
        initData();


    }

    private void initData() {
        Intent intent = getIntent();
        int  videoId=intent.getIntExtra("Video_Id",0);
        String videoName=intent.getStringExtra("Video_Name");
        String videoUrl=intent.getStringExtra("Video_Url");
        String videoImageUrl=intent.getStringExtra("Video_ImageUrl");
        jzVideoPlayerStandard.setUp
                (videoUrl
                        , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, videoName);
        mFavoriteImageView.setOnClickListener(this);
        mVideo=new Video(videoId,videoName,videoUrl,videoImageUrl);


    }

    private void initView() {
        jzVideoPlayerStandard = (JZVideoPlayerStandard) findViewById(R.id.videoplayer);
        mFavoriteImageView=(ImageView)findViewById(R.id.iv_favorite);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_favorite:
                DBUtil.getInstance(this).insert(mVideo);
                mFavoriteImageView.setImageResource(R.drawable.ico_favorite_selected);
                break;
        }

    }
}

