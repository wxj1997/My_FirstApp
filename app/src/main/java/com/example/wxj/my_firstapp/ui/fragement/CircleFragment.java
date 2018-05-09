package com.example.wxj.my_firstapp.ui.fragement;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wxj.my_firstapp.R;
import com.example.wxj.my_firstapp.ui.fragement.circleFragementChild.ImageFragment;
import com.example.wxj.my_firstapp.ui.fragement.circleFragementChild.NewsFragment;
import com.example.wxj.my_firstapp.ui.fragement.circleFragementChild.VideoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CircleFragment extends Fragment {
    private View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> mFragements;
    private String[] tittles = {"新闻", "视频", "图片"};

    public CircleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_circle, container, false);
        initView();
        initData();

        return view;

    }

    private void initData() {
        mFragements = new ArrayList<>();
        mFragements.add(new NewsFragment());
        mFragements.add(new VideoFragment());
        mFragements.add(new ImageFragment());

        MyAdapter adapter = new MyAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        /*tabLayout.getTabAt(0).setText("新闻");
        tabLayout.getTabAt(1).setText("视频");
        tabLayout.getTabAt(2).setText("图片");*/


    }

    private void initView() {
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);

    }

    private class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragements.get(position);
        }

        @Override
        public int getCount() {
            return mFragements.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tittles[position];
        }
    }

}
