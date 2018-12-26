package com.example.jvmori.moviesapp.repository;

import android.app.Application;
import android.util.Log;

import com.example.jvmori.moviesapp.model.popularMovies.PopularItem;
import com.example.jvmori.moviesapp.util.Consts;
import com.example.jvmori.moviesapp.util.TmdbApi;
import com.google.gson.Gson;

import java.util.List;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PopularMoviesRepository
{
    private LiveData<List<PopularItem>> allPopularMovies;

    public PopularMoviesRepository(Application application){

    }

    public void getData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TmdbApi tmdbApi = retrofit.create(TmdbApi.class);
        Call<List<PopularItem>> callApi = tmdbApi.getItems();
        callApi.enqueue(new Callback<List<PopularItem>>() {
            @Override
            public void onResponse(Call<List<PopularItem>> call, Response<List<PopularItem>> response) {
                if (!response.isSuccessful()){
                    return;
                }
                assert response.body() != null;
                for (PopularItem item: response.body()) {
                    Log.i("ITEM", "ITEM" + item.getTitle());
                }
            }

            @Override
            public void onFailure(Call<List<PopularItem>> call, Throwable t) {

            }
        });
    }
}
