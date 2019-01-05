package com.example.jvmori.moviesapp.repository;

import android.util.Log;

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

public class SimilarMoviesRepository
{
    private MutableLiveData<List<MovieItem>> similarMovies;

    public LiveData<List<MovieItem>> getSimilarMovies(String movieId){
        similarMovies = new MutableLiveData<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TmdbApi tmdbApi = retrofit.create(TmdbApi.class);
        tmdbApi.getSimilarMovies(movieId, Consts.api_key).enqueue(new Callback<MovieJsonObj>() {
            @Override
            public void onResponse(Call<MovieJsonObj> call, Response<MovieJsonObj> response) {
                if (!response.isSuccessful())
                    return;
                if(response.body() != null)
                    similarMovies.postValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieJsonObj> call, Throwable t) {
                Log.i("Fail", "Failed to download similar movies");
            }
        });

        return similarMovies;
    }

}
