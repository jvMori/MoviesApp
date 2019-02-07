package com.example.jvmori.moviesapp.model.network;
import com.example.jvmori.moviesapp.model.network.genre.GenreJsonObj;
import com.example.jvmori.moviesapp.model.network.movieDetails.CreditsJsonObj;
import com.example.jvmori.moviesapp.model.network.movieDetails.MovieDetails;
import com.example.jvmori.moviesapp.model.network.popularMovies.MovieJsonObj;
import com.example.jvmori.moviesapp.model.network.video.VideoJsonObj;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface TmdbApi
{
    @GET("discover/{type}")
    Call<MovieJsonObj> getPopular(@Path ("type") String type, @QueryMap Map<String, String> parameters);

    @GET("genre/{type}/list")
    Call<GenreJsonObj> getGenres(@Path ("type") String type);

    @GET("{type}/{id}")
    Call<MovieDetails> getDetails(@Path ("type") String type, @Path ("id") String movieId);

    @GET("{type}/{id}/credits")
    Call<CreditsJsonObj> getCredits (@Path ("type") String type, @Path ("id") String movieId);

    @GET("{type}/{id}/similar")
    Call<MovieJsonObj> getSimilar(@Path ("type") String type,@Path ("id") String movieId);

    @GET("{type}/{id}/videos")
    Call<VideoJsonObj> getVideos (@Path ("type") String type,@Path ("id") String movieId);

    @GET("search/multi")
    Call<MovieJsonObj> getSearchedItem(@QueryMap Map<String, String> parameters);

}
