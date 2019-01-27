package com.example.jvmori.moviesapp.view.adapters;

import android.app.Activity;
import android.content.Intent;


import com.example.jvmori.moviesapp.model.favMovies.FavMovie;
import com.example.jvmori.moviesapp.model.genre.Genre;
import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.util.Consts;
import com.example.jvmori.moviesapp.view.activities.DetailsActivity;
import com.example.jvmori.moviesapp.view.adapters.MovieItemAdapter;
import com.example.jvmori.moviesapp.viewModel.FavMovieViewModel;
import com.example.jvmori.moviesapp.viewModel.GenreViewModel;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SetupMovieItemsAdapter
{
    private MovieItemAdapter movieItemAdapter;
    private Activity activity;
    private Fragment fragment;
    private LifecycleOwner owner;

    public SetupMovieItemsAdapter(Activity activity, Fragment fragment, LifecycleOwner owner) {
        this.activity = activity;
        this.fragment = fragment;
        this.owner = owner;
    }

    public void setMovieItemAdapter(RecyclerView recyclerView, String mediaType, final SetViewCallback setViewCallback){
        setPopularMovieAdapter(recyclerView, mediaType);
        setGenreViewModel(setViewCallback);
    }

    private void setPopularMovieAdapter(RecyclerView recyclerView, final String mediaType){
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setHasFixedSize(true);
        movieItemAdapter = new MovieItemAdapter();
        recyclerView.setAdapter(movieItemAdapter);

        movieItemAdapter.setOnItemClickedListener(new MovieItemAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(MovieItem movieItem) {
                Intent intent = new Intent(activity, DetailsActivity.class);
                intent.putExtra(Consts.id_parameter, movieItem.getTmdbId());
                intent.putExtra(Consts.type_parameter, mediaType);
                activity.startActivity(intent);
            }
        });

        movieItemAdapter.setOnLikeClickedListener(new MovieItemAdapter.OnLikeClickedListener() {
            @Override
            public void onLikeClicked(MovieItem movieItem) {
                addFavMovie(movieItem, mediaType);
            }
        });
    }

    private void setGenreViewModel(final SetViewCallback setViewCallback){
        GenreViewModel genreViewModel = ViewModelProviders.of(fragment).get(GenreViewModel.class);
        genreViewModel.getData().observe(owner, new Observer<List<Genre>>() {
            @Override
            public void onChanged(List<Genre> genres) {
                if (genres == null)
                    return;
                movieItemAdapter.setGenres(genres);
                if (setViewCallback != null)
                    setViewCallback.callback();
            }
        });
    }

    public interface SetViewCallback{
        void callback();
    }

    private void addFavMovie(MovieItem movieItem, String mediaType) {
        FavMovieViewModel favMovieViewModel = ViewModelProviders.of(fragment).get(FavMovieViewModel.class);
        favMovieViewModel.insert(new FavMovie(movieItem, mediaType));
    }


    public MovieItemAdapter getMovieItemAdapter() {
        return movieItemAdapter;
    }

}
