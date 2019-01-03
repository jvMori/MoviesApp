package com.example.jvmori.moviesapp.view;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PopularItemsAdapter extends FragmentPagerAdapter {
    private static int COUNT = 2;

    public PopularItemsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0: fragment = new PopularMovieFragment();
            break;
            case 1: fragment = new PopularMovieFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
