package com.example.jvmori.moviesapp.repository;

import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.util.ApiGetSimilar;
import com.example.jvmori.moviesapp.util.Consts;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class SimilarMoviesRepository
{
    private MutableLiveData<List<MovieItem>> similarMovies;

    public LiveData<List<MovieItem>> getSimilarMovies(String movieId){
        similarMovies = new MutableLiveData<>();
        ApiGetSimilar.getRequest(Consts.movie, movieId, new ApiGetSimilar.OnDataDownloaded() {
            @Override
            public void callback(List<MovieItem> response) {
                similarMovies.setValue(response);
            }
        });

        return similarMovies;
    }

}
