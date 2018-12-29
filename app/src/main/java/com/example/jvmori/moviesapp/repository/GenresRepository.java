package com.example.jvmori.moviesapp.repository;
import android.util.Log;

import com.example.jvmori.moviesapp.model.genre.Genre;
import com.example.jvmori.moviesapp.model.genre.GenreJsonObj;
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

public class GenresRepository
{
    private MutableLiveData<List<Genre>> allGenres;

    public LiveData<List<Genre>> getGenres(){
        allGenres = new MutableLiveData<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TmdbApi tmdbApi = retrofit.create(TmdbApi.class);

        Call<GenreJsonObj> callGenres = tmdbApi.getGenres(Consts.api_key);
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
