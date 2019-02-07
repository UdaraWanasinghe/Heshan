package com.example.projectmanegmenttoolandroidx.adapters;

import com.example.projectmanegmenttoolandroidx.mainThreeFragments.NotificationFragment;
import com.example.projectmanegmenttoolandroidx.mainThreeFragments.OnGoingFragment;
import com.example.projectmanegmenttoolandroidx.mainThreeFragments.ProjectFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class HomePageAdapter extends FragmentPagerAdapter {
    static Fragment fragment1 = new ProjectFragment();
    static Fragment fragment2 = new OnGoingFragment();
    static Fragment fragment3 = new NotificationFragment();
    public HomePageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return fragment1;
            case 1:
                return fragment2;
            case 2:
                return fragment3;
        }
        return new ProjectFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }
}
