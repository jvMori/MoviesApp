package com.example.jvmori.moviesapp.repository;

import android.util.Log;

import com.example.jvmori.moviesapp.model.network.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.model.network.popularMovies.MovieJsonObj;
import com.example.jvmori.moviesapp.model.network.TmdbApi;
import com.example.jvmori.moviesapp.model.network.TmdbApiServiceCall;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    private static void getRequest(String type, final OnDataDownloaded onDataDownloaded){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("sort_by", "popularity.desc");

        TmdbApi tmdbApi = TmdbApiServiceCall.init();
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
