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
import com.example.jvmori.moviesapp.model.network.popularMovies.MovieItem;
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
    private SearchView searchView;
    private MovieItemAdapter movieItemAdapter;
    private RecyclerView recyclerView;
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
        searchView = view.findViewById(R.id.searchView);
        recyclerView = view.findViewById(R.id.searchRecyclerView);
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
        recyclerView.setVisibility(View.GONE);
        SetupMovieItemsAdapter setupMovieItemsAdapter = new SetupMovieItemsAdapter(this.getActivity(), this, this);
        setupMovieItemsAdapter.setMovieItemAdapter(recyclerView, "movie", null);
        movieItemAdapter = setupMovieItemsAdapter.getMovieItemAdapter();

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    popularItemsLayout.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null && query.length() > 1){
                    searchTitles(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText!= null && newText.length() > 1){
                    searchTitles(newText);
                }
                return true;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                popularItemsLayout.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                return false;
            }
        });
    }

    private void searchTitles(String query){
        if (query != null)
            createSearchViewModel(query);
    }

    private void createSearchViewModel(String query){
        SearchResultsViewModel viewModel = ViewModelProviders.of(this).get(SearchResultsViewModel.class);
        viewModel.getResults(query).observe(this, new Observer<List<MovieItem>>() {
            @Override
            public void onChanged(List<MovieItem> movieItems) {
                movieItemAdapter.setMovieItems( movieItems);
            }
        });
    }

}
