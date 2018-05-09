package com.example.wxj.my_firstapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.wxj.my_firstapp.ui.fragement.CircleFragment;
import com.example.wxj.my_firstapp.ui.fragement.FindFragment;
import com.example.wxj.my_firstapp.ui.fragement.MeFragment;
import com.example.wxj.my_firstapp.ui.fragement.MessageFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar
        .OnTabSelectedListener {
    private BottomNavigationBar mBottomNavigationBar;
    private List<Fragment> mFragements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.nav_circle_selector, "动态"))
                .addItem(new BottomNavigationItem(R.drawable.nav_find_selector, "发现"))
                .addItem(new BottomNavigationItem(R.drawable.nav_message_selector, "消息"))
                .addItem(new BottomNavigationItem(R.drawable.nav_me_selector, "我的"))
                .setMode(BottomNavigationBar.MODE_FIXED)//mode_fixed:每个item对应名称，不选中也会显示
                .initialise();

        mBottomNavigationBar.setTabSelectedListener(this);

        mFragements = new ArrayList<>();
        mFragements.add(new CircleFragment());
        mFragements.add(new FindFragment());
        mFragements.add(new MessageFragment());
        mFragements.add(new MeFragment());

        setFragement(0);


    }

    private void setFragement(int posittion) {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.main_container,mFragements.get(posittion));
        ft.commit();
    }

    private void initView() {
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.btm_nav_bar);
    }

    @Override
    public void onTabSelected(int position) {
       setFragement(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
