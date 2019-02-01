package com.example.projectmanegmenttoolandroidx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.projectmanegmenttoolandroidx.adapters.HomePageAdapter;
import com.google.android.material.tabs.TabLayout;

public class Home extends AppCompatActivity {
    private ViewPager mViewPager;
    TabLayout tabs;
    private HomePageAdapter homePageAdapter;
    private int[] tabIcons = {
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mViewPager = findViewById(R.id.pager);
        tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(mViewPager);

        homePageAdapter = new HomePageAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.pager);
        mViewPager.setAdapter(homePageAdapter);
        setupTabIcons();


        tabs.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tab_position = tab.getPosition();
    //todo:set name to action bar according to tab
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setupTabIcons() {
        tabs.getTabAt(0).setIcon(tabIcons[0]);
        tabs.getTabAt(1).setIcon(tabIcons[1]);
        tabs.getTabAt(2).setIcon(tabIcons[2]);
    }
}