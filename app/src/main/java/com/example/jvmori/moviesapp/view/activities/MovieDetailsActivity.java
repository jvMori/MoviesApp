package com.example.jvmori.moviesapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.movie.Movie;
import com.example.jvmori.moviesapp.model.movieDetails.Cast;
import com.example.jvmori.moviesapp.model.movieDetails.MovieDetails;
import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.util.Consts;
import com.example.jvmori.moviesapp.viewModel.CastViewModel;
import com.example.jvmori.moviesapp.viewModel.MovieDetailsViewModel;
import com.example.jvmori.moviesapp.viewModel.SimilarMoviesViewModel;
import com.example.jvmori.moviesapp.viewModel.SimilarShowsViewModel;

import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity {
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        movie = new Movie();
        Intent intent = getIntent();
        if (intent.hasExtra(Consts.item_clicked_id)) {
            String id = intent.getStringExtra(Consts.item_clicked_id);
            setMovieDetailsViewModel(id);
            setCastViewModel(id);
            setSimilarMoviesViewModel(id);
            if (movie.getSimilarMovies() == null)
                setSimilarShowsViewModel(id);
        }
    }

    private void setMovieDetailsViewModel(String movieId){
        MovieDetailsViewModel movieDetailsViewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);
        movieDetailsViewModel.getMovieDetails(movieId).observe(this, new Observer<MovieDetails>() {
            @Override
            public void onChanged(MovieDetails movieDetails) {
                movie.setMovieDetails(movieDetails);
                //TODO: display data
                //TODO: loading screen disable
            }
        });
    }

    private void setCastViewModel(String movieId){
        CastViewModel castViewModel = ViewModelProviders.of(this).get(CastViewModel.class);
        castViewModel.getAllCast(movieId).observe(this, new Observer<List<Cast>>() {
            @Override
            public void onChanged(List<Cast> casts) {
                movie.setCast(casts);
                //TODO: recycler view adapter
                //TODO: loading screen disable
            }
        });
    }

    private void setSimilarMoviesViewModel(String movieId){
        SimilarMoviesViewModel similarMoviesViewModel = ViewModelProviders.of(this).get(SimilarMoviesViewModel.class);
        similarMoviesViewModel.getAllSimilarMovies(movieId).observe(this, new Observer<List<MovieItem>>() {
            @Override
            public void onChanged(List<MovieItem> movieItems) {
                movie.setSimilarMovies(movieItems);
                //TODO: recycler view adapter
                //TODO: loading screen disable
            }
        });
    }

    private void setSimilarShowsViewModel(String showId){
        SimilarShowsViewModel similarShowsViewModel = ViewModelProviders.of(this).get(SimilarShowsViewModel.class);
        similarShowsViewModel.getSimilarShows(showId).observe(this, new Observer<List<MovieItem>>() {
            @Override
            public void onChanged(List<MovieItem> movieItems) {
                movie.setSimilarMovies(movieItems);
                //TODO: recycler view adapter
                //TODO: loading screen disable
            }
        });
    }
}
