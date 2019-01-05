package com.example.jvmori.moviesapp.view.adapters;

import com.example.jvmori.moviesapp.view.fragments.PopularMovieFragment;
import com.example.jvmori.moviesapp.view.fragments.PopularShowFragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PopularItemsAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[] { "Movies", "TV Shows"};
    public PopularItemsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0: fragment = new PopularMovieFragment();
            break;
            case 1: fragment = new PopularShowFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
