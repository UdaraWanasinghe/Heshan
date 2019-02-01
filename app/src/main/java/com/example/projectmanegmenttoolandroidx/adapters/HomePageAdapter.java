package com.example.projectmanegmenttoolandroidx.adapters;

import com.example.projectmanegmenttoolandroidx.mainThreeFragments.ProjectFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class HomePageAdapter extends FragmentPagerAdapter {
    public HomePageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ProjectFragment();
//            case 1:
//                return new OnGoingFragment();
//            case 2:
//                return new NotificationFragment();
        }
        //todo:testing--remove this after making all fragments correctly
        return new ProjectFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }
}
