package com.example.jvmori.moviesapp.repository;

import android.util.Log;

import com.example.jvmori.moviesapp.model.movieDetails.MovieDetails;
import com.example.jvmori.moviesapp.util.Consts;
import com.example.jvmori.moviesapp.util.TmdbApi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsRepository
{
    private MutableLiveData<MovieDetails> movieDetails;

    public LiveData<MovieDetails> getDetails(String type, String movieId){
        movieDetails = new MutableLiveData<>();
        getRequest(type, movieId, new OnDataDownloaded() {
            @Override
            public void callback(MovieDetails response) {
                movieDetails.postValue(response);
            }
        });
        return movieDetails;
    }

    private void getRequest(String type, String id, final OnDataDownloaded onDataDownloaded){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TmdbApi tmdbApi = retrofit.create(TmdbApi.class);
        tmdbApi.getDetails(type, id, Consts.api_key).enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                if (!response.isSuccessful())
                    return;
                if (response.body() != null)
                    onDataDownloaded.callback(response.body());
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                Log.i("Fail", "Failed to download details");
            }
        });
    }

    public interface OnDataDownloaded{
        void callback(MovieDetails response);
    }
}