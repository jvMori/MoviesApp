package com.example.jvmori.moviesapp.util;

import com.example.jvmori.moviesapp.model.popularMovies.PopularItem;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TmdbApi
{
    @GET("discover/movie?api_key=6a78422b468a1d74ae224a5747a35666")
    Call<List<PopularItem>> getItems();
}
