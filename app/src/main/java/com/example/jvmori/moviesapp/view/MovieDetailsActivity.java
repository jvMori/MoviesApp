package com.example.jvmori.moviesapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.movie.Movie;
import com.example.jvmori.moviesapp.model.movieDetails.Cast;
import com.example.jvmori.moviesapp.model.movieDetails.MovieDetails;
import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.viewModel.CastViewModel;
import com.example.jvmori.moviesapp.viewModel.MovieDetailsViewModel;
import com.example.jvmori.moviesapp.viewModel.SimilarMoviesViewModel;

import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity {
    private Movie movie;
    private MovieItem movieItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        movie = new Movie();
        setMovieDetailsViewModel(movieItem.getTmdbId());
        setCastViewModel(movieItem.getTmdbId());
        setSimilarMoviesViewModel(movieItem.getTmdbId());
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
}
