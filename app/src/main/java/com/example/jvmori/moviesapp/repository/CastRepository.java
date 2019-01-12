package com.example.jvmori.moviesapp.repository;

import android.util.Log;

import com.example.jvmori.moviesapp.model.movieDetails.Cast;
import com.example.jvmori.moviesapp.model.movieDetails.CreditsJsonObj;
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

public class CastRepository
{
    private MutableLiveData<List<Cast>> allCasts;

    public LiveData<List<Cast>> getAllCasts(String type, String movieId){
        allCasts = new MutableLiveData<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TmdbApi tmdbApi = retrofit.create(TmdbApi.class);
        tmdbApi.getCredits(type , movieId, Consts.api_key).enqueue(new Callback<CreditsJsonObj>() {
            @Override
            public void onResponse(Call<CreditsJsonObj> call, Response<CreditsJsonObj> response) {
                if(!response.isSuccessful())
                    return;
                if(response.body() != null)
                    allCasts.postValue(response.body().getCast());
            }

            @Override
            public void onFailure(Call<CreditsJsonObj> call, Throwable t) {
                Log.i("Fail", "Failed to load casts");
            }
        });

        return allCasts;
    }
}
