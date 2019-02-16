package com.example.jvmori.moviesapp.model.network;

import android.util.Log;

import com.example.jvmori.moviesapp.model.network.movieDetails.MovieDetails;
import com.example.jvmori.moviesapp.model.network.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.model.network.popularMovies.MovieJsonObj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieNetworkDataSource
{
    private TmdbApi tmdbApi;
    private MutableLiveData<MovieDetails> movieDetails;

    public MovieNetworkDataSource(){
        tmdbApi = TmdbApiServiceCall.init();
        movieDetails = new MutableLiveData<>();
    }

    public LiveData<List<MovieItem>> getAllPopular(String itemType){
        final MutableLiveData<List<MovieItem>> popularItems = new MutableLiveData<>();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("sort_by", "popularity.desc");

        tmdbApi.getPopular(itemType, parameters).enqueue(new Callback<MovieJsonObj>() {
            @Override
            public void onResponse(Call<MovieJsonObj> call, Response<MovieJsonObj> response) {
                if (!response.isSuccessful()){
                    return;
                }
                assert response.body() != null;
                popularItems.postValue(response.body().getResults());
            }
            @Override
            public void onFailure(Call<MovieJsonObj> call, Throwable t) {
                Log.i("ITEM", "Fail");
            }
        });
        return popularItems;
    }

    public LiveData<MovieDetails> getItemDetails (String type, String id){
        tmdbApi.getDetails(type, id).enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                if(!response.isSuccessful())
                    return;
                if(response.body() != null)
                    movieDetails.postValue(response.body());
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                Log.i("ITEM", "Fail");
            }
        });
        return movieDetails;
    }
}
