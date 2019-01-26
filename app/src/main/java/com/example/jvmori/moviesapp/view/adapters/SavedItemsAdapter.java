package com.example.jvmori.moviesapp.view.adapters;

import com.example.jvmori.moviesapp.view.fragments.saved.SavedItemsFragment;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SavedItemsAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[] { "Favorites", "Watched", "To Watch"};
    private List<SavedItemsFragment> savedFragments;

    public SavedItemsAdapter(FragmentManager fm, List<SavedItemsFragment> savedFragments) {
        super(fm);
        this.savedFragments = savedFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return savedFragments.get(position);
    }

    @Override
    public int getCount() {
        return savedFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
