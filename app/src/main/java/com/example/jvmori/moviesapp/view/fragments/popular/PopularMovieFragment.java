package com.example.jvmori.moviesapp.view.fragments.popular;


import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.favMovies.FavMovie;
import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.util.Consts;
import com.example.jvmori.moviesapp.view.activities.DetailsActivity;
import com.example.jvmori.moviesapp.view.adapters.MovieItemAdapter;
import com.example.jvmori.moviesapp.view.fragments.SetupMovieItemsAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopularMovieFragment extends PopularFragment {

    private String mediaType = Consts.movie;

    public PopularMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_popular_item, container, false);
        loadingScreen = view.findViewById(R.id.loadingPanel);
        recyclerView = view.findViewById(R.id.movieRecyclerView);
        //setPopularMovieAdapter();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setGenreViewModel();
        SetupMovieItemsAdapter setupMovieItemsAdapter = new SetupMovieItemsAdapter();
        setupMovieItemsAdapter.setMovieItemAdapter(recyclerView, this.getActivity(), this, this);
        setPopularMovieViewModel(Consts.movie);
        movieItemAdapter = setupMovieItemsAdapter.getMovieItemAdapter();

        movieItemAdapter.setOnItemClickedListener(new MovieItemAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(MovieItem movieItem) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra(Consts.id_parameter, movieItem.getTmdbId());
                intent.putExtra(Consts.type_parameter, Consts.movie);
                startActivity(intent);
            }
        });
        movieItemAdapter.setOnLikeClickedListener(new MovieItemAdapter.OnLikeClickedListener() {
            @Override
            public void onLikeClicked(MovieItem movieItem) {
                addFavMovie(movieItem, mediaType);
            }
        });

    }

}
