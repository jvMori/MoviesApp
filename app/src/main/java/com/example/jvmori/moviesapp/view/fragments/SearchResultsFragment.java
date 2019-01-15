package com.example.jvmori.moviesapp.view.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.view.activities.MainActivity;
import com.example.jvmori.moviesapp.viewModel.SearchResultsViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResultsFragment extends Fragment implements MainActivity.OnSearchCallback {


    public SearchResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_results, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

}

    @Override
    public void onSearch(String query) {
        createView(query);
    }

    private void createView(String query){
        SearchResultsViewModel viewModel = ViewModelProviders.of(this).get(SearchResultsViewModel.class);
        viewModel.getResults(query).observe(this, new Observer<List<MovieItem>>() {
            @Override
            public void onChanged(List<MovieItem> movieItems) {
                Log.i("movie items", movieItems.get(0).getTitle());
            }
        });
    }


}
