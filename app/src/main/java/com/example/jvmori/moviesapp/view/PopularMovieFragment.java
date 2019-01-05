package com.example.jvmori.moviesapp.view;


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
import com.example.jvmori.moviesapp.model.genre.Genre;
import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.viewModel.GenreViewModel;
import com.example.jvmori.moviesapp.viewModel.PopularMoviesViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularMovieFragment extends Fragment {

    private View view;
    private PopularMovieAdapter popularMovieAdapter;
    private RelativeLayout loadingScreen;
    private RecyclerView recyclerView;

    public PopularMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_popular_item, container, false);
        loadingScreen = view.findViewById(R.id.loadingPanel);
        setPopularMovieAdapter();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setGenreViewModel();
        setPopularMovieViewModel();
    }

    private void setPopularMovieAdapter(){
        recyclerView = view.findViewById(R.id.movieRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        popularMovieAdapter = new PopularMovieAdapter();
        recyclerView.setAdapter(popularMovieAdapter);
    }

    private void setGenreViewModel(){
        GenreViewModel genreViewModel = ViewModelProviders.of(this).get(GenreViewModel.class);
        genreViewModel.getData().observe(this, new Observer<List<Genre>>() {
            @Override
            public void onChanged(List<Genre> genres) {
                if (genres == null)
                    return;
                popularMovieAdapter.setGenres(genres);
            }
        });
    }

    private void setPopularMovieViewModel(){
        PopularMoviesViewModel movieViewModel = ViewModelProviders.of(this).get(PopularMoviesViewModel.class);
        movieViewModel.getAllPopularMovies().observe(this, new Observer<List<MovieItem>>() {
            @Override
            public void onChanged(List<MovieItem> movies) {
                if (movies == null)
                    return;
                popularMovieAdapter.setMovieItems(movies);
                loadingScreen.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }

}
