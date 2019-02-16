package com.example.jvmori.moviesapp.viewModel;

import android.app.Application;

import com.example.jvmori.moviesapp.model.network.movieDetails.MovieDetails;
import com.example.jvmori.moviesapp.repository.MovieRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class DetailsViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;

    public DetailsViewModel(@NonNull Application application) {
        super(application);
        movieRepository = MovieRepository.getInstance(application);
    }

    public LiveData<MovieDetails> getItemDetails(String type, String movieId) {
       return  movieRepository.getItemDetails(type, movieId);
    }
}
