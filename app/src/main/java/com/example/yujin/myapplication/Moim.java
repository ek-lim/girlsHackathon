package com.example.yujin.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by SSU on 2016-12-17.
 */
public class Moim extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moim);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        MoimAdapter adapter = new MoimAdapter(getSupportFragmentManager());

        adapter.addFragment(new MoimInfo(), "Information"); // fragment만 바꾸면 돼요
        adapter.addFragment(new MoimMaps(), "Maps");
        adapter.addFragment(new MoimAttendance(), "Attender");
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setTabTextColors(Color.BLACK,Color.BLACK);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }
}
