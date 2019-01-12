package com.example.jvmori.moviesapp.repository;

import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.model.popularMovies.MovieJsonObj;
import com.example.jvmori.moviesapp.util.ApiGetSimilar;
import com.example.jvmori.moviesapp.util.Consts;
import com.example.jvmori.moviesapp.util.TmdbApi;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SimilarShowsRepository
{
    private MutableLiveData<List<MovieItem>> similarShows;

    public LiveData<List<MovieItem>> getSimilarShows(String showId){
        similarShows = new MutableLiveData<>();
        ApiGetSimilar.getRequest(Consts.movie, showId, new ApiGetSimilar.OnDataDownloaded() {
            @Override
            public void callback(List<MovieItem> response) {
                similarShows.setValue(response);
            }
        });

        return similarShows;
    }
}
