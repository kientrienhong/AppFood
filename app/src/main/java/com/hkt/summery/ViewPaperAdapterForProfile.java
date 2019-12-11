package com.hkt.summery;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPaperAdapterForProfile extends FragmentPagerAdapter {

    public ViewPaperAdapterForProfile(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                Fragment fragment = new Fragment_post();
                return fragment;
            case 1:
                Fragment fragment2 = new Fragment_save();
                return fragment2;
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }


}
