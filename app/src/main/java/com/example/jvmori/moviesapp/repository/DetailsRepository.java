package com.example.jvmori.moviesapp.repository;

import android.util.Log;

import com.example.jvmori.moviesapp.model.network.movieDetails.MovieDetails;
import com.example.jvmori.moviesapp.model.network.TmdbApi;
import com.example.jvmori.moviesapp.model.network.TmdbApiServiceCall;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        TmdbApi tmdbApi = TmdbApiServiceCall.init();
        tmdbApi.getDetails(type, id).enqueue(new Callback<MovieDetails>() {
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
