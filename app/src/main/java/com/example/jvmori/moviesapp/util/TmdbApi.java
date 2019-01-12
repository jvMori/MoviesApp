package com.example.jvmori.moviesapp.util;
import com.example.jvmori.moviesapp.model.genre.GenreJsonObj;
import com.example.jvmori.moviesapp.model.movieDetails.CreditsJsonObj;
import com.example.jvmori.moviesapp.model.movieDetails.MovieDetails;
import com.example.jvmori.moviesapp.model.popularMovies.MovieJsonObj;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface TmdbApi
{
    @GET("discover/{type}")
    Call<MovieJsonObj> getPopular(@Path ("type") String type, @QueryMap Map<String, String> parameters);

    @GET("genre/movie/list")
    Call<GenreJsonObj> getGenres(@Query("api_key") String api_key);

    @GET("{type}/{id}")
    Call<MovieDetails> getDetails(@Path ("type") String type, @Path ("id") String movieId, @Query("api_key") String api_key);

    @GET("{type}/{id}/credits")
    Call<CreditsJsonObj> getCredits (@Path ("type") String type, @Path ("id") String movieId, @Query("api_key") String api_key);

    @GET("{type}/{id}/similar")
    Call<MovieJsonObj> getSimilar(@Path ("type") String type,@Path ("id") String movieId, @Query("api_key") String api_key);

}
