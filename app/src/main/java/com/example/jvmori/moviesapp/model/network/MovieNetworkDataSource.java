package com.example.jvmori.moviesapp.model.network;

import android.util.Log;

import com.example.jvmori.moviesapp.model.network.response.Genre;
import com.example.jvmori.moviesapp.model.network.response.GenreJsonObj;
import com.example.jvmori.moviesapp.model.network.response.Cast;
import com.example.jvmori.moviesapp.model.network.response.CreditsJsonObj;
import com.example.jvmori.moviesapp.model.network.response.MovieDetails;
import com.example.jvmori.moviesapp.model.db.entities.MovieItem;
import com.example.jvmori.moviesapp.model.network.response.MovieJsonObj;
import com.example.jvmori.moviesapp.model.network.response.Video;
import com.example.jvmori.moviesapp.model.network.response.VideoJsonObj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieNetworkDataSource {
    private TmdbApi tmdbApi;
    private MutableLiveData<MovieDetails> movieDetails;
    private MutableLiveData<List<MovieItem>> allSearched;
    private MutableLiveData<List<MovieItem>> similarMovies;
    private MutableLiveData<List<Video>> videos;

    public MovieNetworkDataSource() {
        tmdbApi = TmdbApiServiceCall.init();
        movieDetails = new MutableLiveData<>();
        similarMovies = new MutableLiveData<>();
        videos = new MutableLiveData<>();
    }

    public io.reactivex.Observable<List<MovieItem>> getPopularItems(String itemType){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("sort_by", "popularity.desc");
        return tmdbApi.getPopular(itemType, parameters)
                .subscribeOn(Schedulers.io())
                .flatMap(results ->
                        io.reactivex.Observable.just(results.getResults())
                );
    }

    public LiveData<MovieDetails> getItemDetails(String type, String id) {
        tmdbApi.getDetails(type, id).enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                if (!response.isSuccessful())
                    return;
                if (response.body() != null)
                    movieDetails.postValue(response.body());
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                Log.i("ITEM", "Fail");
            }
        });
        return movieDetails;
    }

    public LiveData<List<Cast>> getAllCasts(String type, String movieId) {
        final MutableLiveData<List<Cast>> allCasts = new MutableLiveData<>();
        tmdbApi.getCredits(type, movieId).enqueue(new Callback<CreditsJsonObj>() {
            @Override
            public void onResponse(Call<CreditsJsonObj> call, Response<CreditsJsonObj> response) {
                if (!response.isSuccessful())
                    return;
                if (response.body() != null)
                    allCasts.postValue(response.body().getCast());
            }

            @Override
            public void onFailure(Call<CreditsJsonObj> call, Throwable t) {
                Log.i("Fail", "Failed to load casts");
            }
        });

        return allCasts;
    }

    public LiveData<List<Genre>> getGenres(final String type) {
       final MutableLiveData<List<Genre>> allGenres = new MutableLiveData<>();
        tmdbApi.getGenres(type).enqueue(new Callback<GenreJsonObj>() {
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

    public LiveData<List<MovieItem>> getSearchResults(String query){
        allSearched = new MutableLiveData<>();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("query", query);
        parameters.put("include_adult", "false");

        tmdbApi.getSearchedItem(parameters).enqueue(new Callback<MovieJsonObj>() {
            @Override
            public void onResponse(Call<MovieJsonObj> call, Response<MovieJsonObj> response) {
                if(!response.isSuccessful())
                    return;
                if(response.body() != null)
                    allSearched.postValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieJsonObj> call, Throwable t) {

            }
        });
        return allSearched;
    }

    public LiveData<List<MovieItem>> getSimilar(String type, String id){
        tmdbApi.getSimilar(type, id).enqueue(new Callback<MovieJsonObj>() {
            @Override
            public void onResponse(Call<MovieJsonObj> call, Response<MovieJsonObj> response) {
                if (!response.isSuccessful())
                    return;
                if(response.body() != null)
                   similarMovies.postValue(response.body().getResults());
            }
            @Override
            public void onFailure(Call<MovieJsonObj> call, Throwable t) {
                Log.i("Fail", "Failed to download similar movies");
            }
        });
        return similarMovies;
    }

    public LiveData<List<Video>> getVideos(String type, String id){
        tmdbApi.getVideos(type, id).enqueue(new Callback<VideoJsonObj>() {
            @Override
            public void onResponse(Call<VideoJsonObj> call, Response<VideoJsonObj> response) {
                if (!response.isSuccessful())
                    return;
                if (response.body() != null)
                    videos.postValue(response.body().getResults());
            }
            @Override
            public void onFailure(Call<VideoJsonObj> call, Throwable t) {

            }
        });
        return videos;
    }
}
