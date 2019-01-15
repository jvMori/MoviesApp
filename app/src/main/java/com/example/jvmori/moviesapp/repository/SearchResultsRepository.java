package com.example.jvmori.moviesapp.repository;

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

public class SearchResultsRepository
{
    private MutableLiveData<List<MovieItem>> allSearched;

    public LiveData<List<MovieItem>> getData(String query){
        allSearched = new MutableLiveData<>();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("api_key", Consts.api_key);
        parameters.put("query", query);
        parameters.put("include_adult", "false");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TmdbApi tmdbApi = retrofit.create(TmdbApi.class);
        tmdbApi.getSearchedItem(parameters).enqueue(new Callback<MovieJsonObj>() {
            @Override
            public void onResponse(Call<MovieJsonObj> call, Response<MovieJsonObj> response) {
                if(!response.isSuccessful())
                    return;
                if(response.body() != null)
                    allSearched.postValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieJsonObj> call, Throwable t) {

            }
        });

        return allSearched;
    }
}
