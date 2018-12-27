package com.example.jvmori.moviesapp.util;

import com.example.jvmori.moviesapp.model.popularMovies.PopularItem;
import com.example.jvmori.moviesapp.model.popularMovies.PopularMoviesJsonObj;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface TmdbApi
{
    @GET("discover/movie")
    Call<PopularMoviesJsonObj> getPopularMovies(@QueryMap Map<String, String> parameters);
}
