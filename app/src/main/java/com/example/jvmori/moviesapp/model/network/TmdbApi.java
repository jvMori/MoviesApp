package com.example.jvmori.moviesapp.model.network;
import com.example.jvmori.moviesapp.model.network.response.GenreJsonObj;
import com.example.jvmori.moviesapp.model.network.response.CreditsJsonObj;
import com.example.jvmori.moviesapp.model.network.response.MovieDetails;
import com.example.jvmori.moviesapp.model.network.response.MovieJsonObj;
import com.example.jvmori.moviesapp.model.network.response.VideoJsonObj;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface TmdbApi
{
    @GET("discover/{type}")
    Observable<MovieJsonObj> getPopular(@Path ("type") String type, @QueryMap Map<String, String> parameters);

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
