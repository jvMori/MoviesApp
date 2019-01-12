package com.example.jvmori.moviesapp.repository;

import com.example.jvmori.moviesapp.model.movieDetails.MovieDetails;
import com.example.jvmori.moviesapp.util.Consts;
import com.example.jvmori.moviesapp.util.ApiGetDetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class DetailsMovieRepository
{
    private MutableLiveData<MovieDetails> movieDetails;

    public LiveData<MovieDetails> getDetails(String movieId){
        movieDetails = new MutableLiveData<>();
        ApiGetDetails.getRequest(Consts.movie, movieId, new ApiGetDetails.OnDataDownloaded() {
            @Override
            public void callback(MovieDetails response) {
                movieDetails.postValue(response);
            }
        });
        return movieDetails;
    }
}
