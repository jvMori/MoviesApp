package com.example.jvmori.moviesapp.view.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.network.response.MovieItem;
import com.example.jvmori.moviesapp.view.adapters.MovieItemAdapter;
import com.example.jvmori.moviesapp.view.adapters.SetupMovieItemsAdapter;
import com.example.jvmori.moviesapp.viewModel.SearchResultsViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment {

    private View view;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private MovieItemAdapter movieItemAdapter;

    public DiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_discover, container, false);
        searchView = view.findViewById(R.id.searchView);
        recyclerView = view.findViewById(R.id.searchRecyclerView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SetupMovieItemsAdapter setupMovieItemsAdapter = new SetupMovieItemsAdapter(this.getActivity(), this, this);
        setupMovieItemsAdapter.setMovieItemAdapter(recyclerView, "movie", null);
        movieItemAdapter = setupMovieItemsAdapter.getMovieItemAdapter();
        searchView.setQueryHint("Search...");
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
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
                //recyclerView.setVisibility(View.GONE);
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
