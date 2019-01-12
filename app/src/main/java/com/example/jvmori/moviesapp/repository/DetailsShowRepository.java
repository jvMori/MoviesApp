package com.example.jvmori.moviesapp.repository;

import com.example.jvmori.moviesapp.model.movieDetails.MovieDetails;
import com.example.jvmori.moviesapp.util.ApiGetDetails;
import com.example.jvmori.moviesapp.util.Consts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class DetailsShowRepository {

    private MutableLiveData<MovieDetails> showDetails;

    public LiveData<MovieDetails> getDetails(String tvId){
        showDetails = new MutableLiveData<>();
        ApiGetDetails.getRequest(Consts.tvShow, tvId, new ApiGetDetails.OnDataDownloaded() {
            @Override
            public void callback(MovieDetails response) {
                showDetails.postValue(response);
            }
        });
        return showDetails;
    }
}
