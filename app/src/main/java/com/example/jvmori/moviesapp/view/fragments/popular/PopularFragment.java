package com.example.jvmori.moviesapp.view.fragments.popular;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.favMovies.FavMovie;
import com.example.jvmori.moviesapp.model.genre.Genre;
import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.view.adapters.MovieItemAdapter;
import com.example.jvmori.moviesapp.viewModel.FavMovieViewModel;
import com.example.jvmori.moviesapp.viewModel.GenreViewModel;
import com.example.jvmori.moviesapp.viewModel.PopularItemsViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment {

    protected View view;
    protected MovieItemAdapter movieItemAdapter;
    protected RelativeLayout loadingScreen;
    protected RecyclerView recyclerView;

    public PopularFragment() {
        // Required empty public constructor
    }

//    protected void addFavMovie(MovieItem movieItem, String mediaType){
//        FavMovieViewModel favMovieViewModel = ViewModelProviders.of(this).get(FavMovieViewModel.class);
//        favMovieViewModel.insert(new FavMovie(movieItem, mediaType));
//    }

//    protected void setPopularMovieAdapter(){
//        recyclerView = view.findViewById(R.id.movieRecyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setHasFixedSize(true);
//        movieItemAdapter = new MovieItemAdapter();
//        recyclerView.setAdapter(movieItemAdapter);
//    }
//
//    protected void setGenreViewModel(){
//        GenreViewModel genreViewModel = ViewModelProviders.of(this).get(GenreViewModel.class);
//        genreViewModel.getData().observe(this, new Observer<List<Genre>>() {
//            @Override
//            public void onChanged(List<Genre> genres) {
//                if (genres == null)
//                    return;
//                movieItemAdapter.setGenres(genres);
//            }
//        });
//    }

    protected void setPopularMovieViewModel(String type){
        PopularItemsViewModel movieViewModel = ViewModelProviders.of(this).get(PopularItemsViewModel.class);
        movieViewModel.getAllPopularItems(type).observe(this, new Observer<List<MovieItem>>() {
            @Override
            public void onChanged(List<MovieItem> movies) {
                if (movies == null)
                    return;
                movieItemAdapter.setMovieItems(movies);
                loadingScreen.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }

}
