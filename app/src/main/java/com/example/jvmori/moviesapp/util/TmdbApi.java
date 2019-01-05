package com.example.jvmori.moviesapp.util;
import com.example.jvmori.moviesapp.model.genre.GenreJsonObj;
import com.example.jvmori.moviesapp.model.popularMovies.MovieJsonObj;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface TmdbApi
{
    @GET("discover/movie")
    Call<MovieJsonObj> getPopularMovies(@QueryMap Map<String, String> parameters);

    @GET("genre/movie/list")
    Call<GenreJsonObj> getGenres(@Query("api_key") String api_key);

    @GET("discover/tv")
    Call<MovieJsonObj> getPopularShows(@QueryMap Map<String, String> parameters);
}
