package com.example.jvmori.moviesapp.view.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.library.collection.item.LibraryItem;
import com.example.jvmori.moviesapp.view.adapters.SavedItemsAdapter;
import com.example.jvmori.moviesapp.view.fragments.saved.SavedItemsFragment;
import com.example.jvmori.moviesapp.viewModel.LibraryViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    List<SavedItemsFragment> savedItems;

    private SavedItemsAdapter savedItemsAdapter;
    private View view;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Fragment fragment;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        savedItems = new ArrayList<>();
        fragment = this;
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getSavedItems();
    }

    private void getSavedItems(){
        LibraryViewModel libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);
        libraryViewModel.getAllItems().observe(this, new Observer<List<LibraryItem>>() {
            @Override
            public void onChanged(List<LibraryItem> libraryItems) {
                setupView(libraryItems);
                savedItemsAdapter = new SavedItemsAdapter(fragment.getChildFragmentManager(), savedItems);
                viewPager.setAdapter(savedItemsAdapter);
                tabLayout.setupWithViewPager(viewPager);
            }
        });
    }

    private void setupView(List<LibraryItem> libraryItems){
        for (LibraryItem item:libraryItems) {
            SavedItemsFragment favFrag = new SavedItemsFragment();
            favFrag.setCollectionName(item.getNameOfCollection());
            savedItems.add(favFrag);
        }
    }
}
