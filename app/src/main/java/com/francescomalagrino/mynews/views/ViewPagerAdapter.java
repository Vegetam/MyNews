package com.francescomalagrino.mynews.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.francescomalagrino.mynews.Controllers.Fragments.MainFragment;
import com.francescomalagrino.mynews.Utils.Constant;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return Constant.NEWS_SECTIONS[position];
    }

    @NonNull
    @Override
    public MainFragment getItem(int position) {
        return MainFragment.newInstance(Constant.NEWS_SECTIONS[position]);
    }

    @Override
    public int getCount() {
        return Constant.NEWS_SECTIONS.length;
    }
}
