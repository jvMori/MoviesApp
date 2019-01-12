package com.example.jvmori.moviesapp.repository;

import android.util.Log;

import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.model.popularMovies.MovieJsonObj;
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

public class PopularItemsRepository
{
    private MutableLiveData<List<MovieItem>> allPopularMovies;

    public LiveData<List<MovieItem>> getData(String type){
        allPopularMovies = new MutableLiveData<>();
        getRequest(type, new OnDataDownloaded() {
            @Override
            public void callback(List<MovieItem> response) {
                allPopularMovies.setValue(response);
            }
        });

        return allPopularMovies;
    }

    public static void getRequest(String type, final OnDataDownloaded onDataDownloaded){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("api_key", Consts.api_key);
        parameters.put("sort_by", "popularity.desc");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TmdbApi tmdbApi = retrofit.create(TmdbApi.class);

        Call<MovieJsonObj> callApi = tmdbApi.getPopular(type, parameters);
        callApi.enqueue(new Callback<MovieJsonObj>() {
            @Override
            public void onResponse(Call<MovieJsonObj> call, Response<MovieJsonObj> response) {
                if (!response.isSuccessful()){
                    return;
                }
                assert response.body() != null;
                onDataDownloaded.callback(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieJsonObj> call, Throwable t) {
                Log.i("ITEM", "Fail");
            }
        });
    }

    public interface OnDataDownloaded{
        void callback(List<MovieItem> response);
    }
}
