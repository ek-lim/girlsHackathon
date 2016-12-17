package com.example.yujin.myapplication;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by SSU on 2016-12-17.
 */
public class Moim extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    MyViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moim);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("Information"));
        tabLayout.addTab(tabLayout.newTab().setText("jido"));
        tabLayout.addTab(tabLayout.newTab().setText("attender"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }
}
