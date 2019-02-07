package com.example.jvmori.moviesapp.repository;

import android.util.Log;

import com.example.jvmori.moviesapp.model.network.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.model.network.popularMovies.MovieJsonObj;
import com.example.jvmori.moviesapp.model.network.TmdbApi;
import com.example.jvmori.moviesapp.model.network.TmdbApiServiceCall;

import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SimilarRepository
{
    private MutableLiveData<List<MovieItem>> similarMovies;

    public LiveData<List<MovieItem>> getSimilarMovies(String type, String movieId){
        similarMovies = new MutableLiveData<>();
        getRequest(type, movieId, new OnDataDownloaded() {
            @Override
            public void callback(List<MovieItem> response) {
                similarMovies.setValue(response);
            }
        });

        return similarMovies;
    }

    private static void getRequest(String type, String id, final OnDataDownloaded onDataDownloaded){
        TmdbApi tmdbApi = TmdbApiServiceCall.init();
        tmdbApi.getSimilar(type, id).enqueue(new Callback<MovieJsonObj>() {
            @Override
            public void onResponse(Call<MovieJsonObj> call, Response<MovieJsonObj> response) {
                if (!response.isSuccessful())
                    return;
                if(response.body() != null)
                    onDataDownloaded.callback(response.body().getResults());
            }
            @Override
            public void onFailure(Call<MovieJsonObj> call, Throwable t) {
                Log.i("Fail", "Failed to download similar movies");
            }
        });
    }
    public interface OnDataDownloaded{
        void callback(List<MovieItem> response);
    }
}
