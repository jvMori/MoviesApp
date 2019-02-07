package com.example.jvmori.moviesapp.repository;
import android.util.Log;

import com.example.jvmori.moviesapp.model.network.genre.Genre;
import com.example.jvmori.moviesapp.model.network.genre.GenreJsonObj;
import com.example.jvmori.moviesapp.model.network.TmdbApi;
import com.example.jvmori.moviesapp.model.network.TmdbApiServiceCall;

import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenresRepository
{
    private MutableLiveData<List<Genre>> allGenres;

    public LiveData<List<Genre>> getGenres(final String type){
        allGenres = new MutableLiveData<>();
        TmdbApi tmdbApi = TmdbApiServiceCall.init();
        Call<GenreJsonObj> callGenres = tmdbApi.getGenres(type);
        callGenres.enqueue(new Callback<GenreJsonObj>() {
            @Override
            public void onResponse(Call<GenreJsonObj> call, Response<GenreJsonObj> response) {
                if (!response.isSuccessful())
                    return;
                assert response.body() != null;
                allGenres.setValue(response.body().getGenres());
            }
            @Override
            public void onFailure(Call<GenreJsonObj> call, Throwable t) {
                Log.i("Error", t.toString());
            }
        });
        return allGenres;
    }
}
