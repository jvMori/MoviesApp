package com.example.jvmori.moviesapp.repository;

import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.model.popularMovies.MovieJsonObj;
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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TmdbApi tmdbApi = retrofit.create(TmdbApi.class);
        tmdbApi.getSimilarTvShows(showId, Consts.api_key).enqueue(new Callback<MovieJsonObj>() {
            @Override
            public void onResponse(Call<MovieJsonObj> call, Response<MovieJsonObj> response) {
                if (!response.isSuccessful())
                    return;
                if(response.body() != null)
                    similarShows.postValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieJsonObj> call, Throwable t) {

            }
        });

        return similarShows;
    }
}
