package com.example.wxj.my_firstapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.wxj.my_firstapp.R;
import com.example.wxj.my_firstapp.bean.Video;
import com.example.wxj.my_firstapp.db.DBUtil;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;


public class VideoActivity extends AppCompatActivity implements View.OnClickListener {
    private JZVideoPlayerStandard jzVideoPlayerStandard;
    private ImageView mFavoriteImageView;
    private Video mVideo;
    private ImageView deleteBtn;

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMessageEvent(Video video) {
        mVideo = video;
        jzVideoPlayerStandard.setUp
                (mVideo.getVideoUrl()
                        , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, mVideo.getName());
        //视频播放界面缩略图
        Glide.with(this).load(mVideo.getVideoImageUrl()).into(jzVideoPlayerStandard.thumbImageView);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initView();
        initData();


    }

    private void initData() {
        /*Intent intent = getIntent();
        int videoId = intent.getIntExtra("Video_Id", 0);
        String videoName = intent.getStringExtra("Video_Name");
        String videoUrl = intent.getStringExtra("Video_Url");
        String videoImageUrl = intent.getStringExtra("Video_ImageUrl");*/

        mFavoriteImageView.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);

      /*  mVideo = new Video(videoId, videoName, videoUrl, videoImageUrl);*/


    }

    private void initView() {
        jzVideoPlayerStandard = (JZVideoPlayerStandard) findViewById(R.id.videoplayer);
        mFavoriteImageView = (ImageView) findViewById(R.id.iv_favorite);
        deleteBtn = (ImageView) findViewById(R.id.delete);
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
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_favorite:
                DBUtil.getInstance(this).insert(mVideo);
                mFavoriteImageView.setImageResource(R.drawable.ico_favorite_selected);
                break;
            case R.id.delete:
                DBUtil.getInstance(this).delete(mVideo.getName());
                deleteBtn.setImageResource(R.drawable.delete_select);
                break;

        }

    }
}

