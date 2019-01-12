package com.example.jvmori.moviesapp.repository;

import android.util.Log;

import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.model.popularMovies.MovieJsonObj;
import com.example.jvmori.moviesapp.util.ApiGetPopular;
import com.example.jvmori.moviesapp.util.Consts;
import com.example.jvmori.moviesapp.util.TmdbApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PopularShowsRepository {
    private MutableLiveData<List<MovieItem>> allPopularShows;

    public LiveData<List<MovieItem>> getData(){
        allPopularShows = new MutableLiveData<>();

        ApiGetPopular.getRequest(Consts.tvShow, new ApiGetPopular.OnDataDownloaded() {
            @Override
            public void callback(List<MovieItem> response) {
                allPopularShows.setValue(response);
            }
        });

        return allPopularShows;
    }
}
