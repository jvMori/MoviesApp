package com.example.jvmori.moviesapp.view.fragments.popular;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.util.Consts;
import com.example.jvmori.moviesapp.view.activities.DetailsActivity;
import com.example.jvmori.moviesapp.view.adapters.MovieItemAdapter;
import com.example.jvmori.moviesapp.view.fragments.SetupMovieItemsAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PopularShowFragment extends PopularFragment {

    private String mediaType = Consts.tvShow;

    public PopularShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_popular_item, container, false);
        loadingScreen = view.findViewById(R.id.loadingPanel);
        recyclerView = view.findViewById(R.id.movieRecyclerView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SetupMovieItemsAdapter setupMovieItemsAdapter = new SetupMovieItemsAdapter(this.getActivity(), this, this);
        setupMovieItemsAdapter.setMovieItemAdapter(recyclerView, mediaType, new SetupMovieItemsAdapter.SetViewCallback() {
            @Override
            public void callback() {
                setPopularMovieViewModel(mediaType);
            }
        });
        movieItemAdapter = setupMovieItemsAdapter.getMovieItemAdapter();

    }

}
