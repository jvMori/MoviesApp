package com.example.jvmori.moviesapp.view.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.network.response.MovieItem;
import com.example.jvmori.moviesapp.view.adapters.PopularItemsAdapter;
import com.example.jvmori.moviesapp.view.adapters.MovieItemAdapter;
import com.example.jvmori.moviesapp.view.adapters.SetupMovieItemsAdapter;
import com.example.jvmori.moviesapp.viewModel.SearchResultsViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private View view;
    private PopularItemsAdapter popularItemsAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private LinearLayout popularItemsLayout;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = view.findViewById(R.id.popularItemViewPager);
        tabLayout = view.findViewById(R.id.tabLayout);
        popularItemsLayout = view.findViewById(R.id.popularLayout);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        popularItemsAdapter = new PopularItemsAdapter(this.getChildFragmentManager());
        viewPager.setAdapter(popularItemsAdapter);
        tabLayout.setupWithViewPager(viewPager);
        popularItemsLayout.setVisibility(View.VISIBLE);

    }
}
