package com.example.wxj.my_firstapp.adapter;


import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wxj.my_firstapp.R;
import com.example.wxj.my_firstapp.bean.Video;

import java.util.List;

import wseemann.media.FFmpegMediaMetadataRetriever;

public class VideoAdapter extends BaseQuickAdapter<Video, BaseViewHolder> {
    public VideoAdapter(@LayoutRes int layoutResId, @Nullable List<Video> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Video item) {
        helper.setText(R.id.tv_video_name,item.getName());
        helper.setImageBitmap(R.id.image_thumb, getBitmap(item.getVideoUrl()));
    }

    public Bitmap getBitmap(String imageUrl) {
        FFmpegMediaMetadataRetriever mmr = new FFmpegMediaMetadataRetriever();
        mmr.setDataSource(imageUrl);
        mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM);
        mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ARTIST);
        Bitmap b = mmr.getFrameAtTime(2000000, FFmpegMediaMetadataRetriever.OPTION_CLOSEST); //
        // frame at 2 seconds
        byte[] artwork = mmr.getEmbeddedPicture();

        mmr.release();
        return b;
    }
}
