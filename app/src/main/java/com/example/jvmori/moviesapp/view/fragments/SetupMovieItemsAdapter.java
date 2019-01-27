package com.example.jvmori.moviesapp.view.fragments;

import android.app.Activity;


import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.genre.Genre;
import com.example.jvmori.moviesapp.view.adapters.MovieItemAdapter;
import com.example.jvmori.moviesapp.viewModel.GenreViewModel;

import java.security.acl.Owner;
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

    public void setMovieItemAdapter(RecyclerView recyclerView, Activity activity, Fragment fragment, LifecycleOwner owner){
        setPopularMovieAdapter(recyclerView, activity);
        setGenreViewModel(fragment, owner);
    }


    private void setPopularMovieAdapter(RecyclerView recyclerView, Activity activity){
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setHasFixedSize(true);
        movieItemAdapter = new MovieItemAdapter();
        recyclerView.setAdapter(movieItemAdapter);
    }

    private void setGenreViewModel(Fragment fragment, LifecycleOwner owner){
        GenreViewModel genreViewModel = ViewModelProviders.of(fragment).get(GenreViewModel.class);
        genreViewModel.getData().observe(owner, new Observer<List<Genre>>() {
            @Override
            public void onChanged(List<Genre> genres) {
                if (genres == null)
                    return;
                movieItemAdapter.setGenres(genres);
            }
        });
    }

    public MovieItemAdapter getMovieItemAdapter() {
        return movieItemAdapter;
    }

}
