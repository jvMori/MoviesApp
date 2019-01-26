package com.example.jvmori.moviesapp.view.fragments.saved;

import android.os.Bundle;
import android.view.View;

import com.example.jvmori.moviesapp.model.favMovies.FavMovie;
import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.viewModel.FavMovieViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class FavItemsFragment extends SavedItemsFragment {

    private List<MovieItem> movieItems;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setFavMovieViewModel();
    }

    private void setFavMovieViewModel(){
        FavMovieViewModel favMovieViewModel = ViewModelProviders.of(this).get(FavMovieViewModel.class);
        favMovieViewModel.getAllMovies().observe(this, new Observer<List<FavMovie>>() {
            @Override
            public void onChanged(List<FavMovie> favMovies) {
                setMovieItems(favMovies);
                movieItemAdapter.setMovieItems(movieItems);
            }
        });
    }

    private void setMovieItems(List<FavMovie> favMovies){
        movieItems = new ArrayList<>();
        for (FavMovie fav: favMovies) {
            movieItems.add(fav.getMovie());
        }
    }

}
