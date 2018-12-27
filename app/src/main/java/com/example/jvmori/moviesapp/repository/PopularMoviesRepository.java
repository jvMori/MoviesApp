package com.example.jvmori.moviesapp.repository;

import android.util.Log;
import com.example.jvmori.moviesapp.model.popularMovies.PopularItem;
import com.example.jvmori.moviesapp.model.popularMovies.PopularMoviesJsonObj;
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

public class PopularMoviesRepository
{
    private static PopularMoviesRepository instance;
    private MutableLiveData<List<PopularItem>> allPopularMovies;
    public static synchronized PopularMoviesRepository getInstance(){
        if (instance == null){
            instance = new PopularMoviesRepository();
        }
        return instance;
    }

    public LiveData<List<PopularItem>> getAllPopularMovies() {
        return allPopularMovies;
    }

    public void getData(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("api_key", Consts.api_key);
        parameters.put("sort_by", "popularity.desc");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TmdbApi tmdbApi = retrofit.create(TmdbApi.class);

        Call<PopularMoviesJsonObj> callApi = tmdbApi.getPopularMovies(parameters);
        callApi.enqueue(new Callback<PopularMoviesJsonObj>() {
            @Override
            public void onResponse(Call<PopularMoviesJsonObj> call, Response<PopularMoviesJsonObj> response) {
                if (!response.isSuccessful()){
                    return;
                }
                assert response.body() != null;
                allPopularMovies = new MutableLiveData<>();
                allPopularMovies.setValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<PopularMoviesJsonObj> call, Throwable t) {
                Log.i("ITEM", "Fail");
            }
        });
    }
}
