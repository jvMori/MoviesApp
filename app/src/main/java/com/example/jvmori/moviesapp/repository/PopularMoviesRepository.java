package com.example.jvmori.moviesapp.repository;

import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.util.ApiGetPopular;
import com.example.jvmori.moviesapp.util.Consts;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class PopularMoviesRepository
{
    private MutableLiveData<List<MovieItem>> allPopularMovies;

    public LiveData<List<MovieItem>> getData(){
        allPopularMovies = new MutableLiveData<>();

        ApiGetPopular.getRequest(Consts.tvShow, new ApiGetPopular.OnDataDownloaded() {
            @Override
            public void callback(List<MovieItem> response) {
                allPopularMovies.setValue(response);
            }
        });

        return allPopularMovies;
    }
}
