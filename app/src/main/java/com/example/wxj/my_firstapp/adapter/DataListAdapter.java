package com.example.wxj.my_firstapp.adapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wxj.my_firstapp.R;
import com.example.wxj.my_firstapp.bean.News;

import java.util.List;

public class DataListAdapter extends BaseQuickAdapter<News, BaseViewHolder> {


    public DataListAdapter(@LayoutRes int layoutResId, @Nullable List<News> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, News item) {

        helper.setText(R.id.tv_tittle, item.getTittle());
        helper.setText(R.id.tv_content, item.getContent());
        Glide.with(mContext).load(item.getImageUrl()).into((ImageView) helper.getView(R.id
                .image_view));
    }
}


