package com.example.jvmori.moviesapp.util;
import com.example.jvmori.moviesapp.model.genre.GenreJsonObj;
import com.example.jvmori.moviesapp.model.movieDetails.CreditsJsonObj;
import com.example.jvmori.moviesapp.model.movieDetails.MovieDetails;
import com.example.jvmori.moviesapp.model.popularMovies.MovieJsonObj;
import com.example.jvmori.moviesapp.model.video.VideoJsonObj;

import java.io.IOException;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface TmdbApi
{
    @GET("discover/{type}")
    Call<MovieJsonObj> getPopular(@Path ("type") String type, @QueryMap Map<String, String> parameters);

    @GET("genre/movie/list")
    Call<GenreJsonObj> getGenres();

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
