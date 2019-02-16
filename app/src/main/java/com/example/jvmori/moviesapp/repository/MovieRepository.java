package com.example.jvmori.moviesapp.repository;

import android.app.Application;

import com.example.jvmori.moviesapp.model.db.MovieDao;
import com.example.jvmori.moviesapp.model.db.MovieDatabase;
import com.example.jvmori.moviesapp.model.network.MovieNetworkDataSource;
import com.example.jvmori.moviesapp.model.network.movieDetails.MovieDetails;
import com.example.jvmori.moviesapp.model.network.popularMovies.MovieItem;

import java.util.List;

import androidx.lifecycle.LiveData;

public class MovieRepository
{
    private static final Object LOCK = new Object();
    private static MovieRepository instance;
    private MovieNetworkDataSource movieNetworkDataSource;
    private MovieDao movieDao;

    private MovieRepository(Application application){
        movieDao = MovieDatabase.getInstance(application).movieDao();
        movieNetworkDataSource = new MovieNetworkDataSource();
    }

    public synchronized static MovieRepository getInstance(Application context) {
        if (instance == null) {
            synchronized (LOCK) {
                instance = new MovieRepository(context);
            }
        }
        return instance;
    }

    public LiveData<List<MovieItem>> getAllPopular(String type){
        return movieNetworkDataSource.getAllPopular(type);
    }

    public LiveData<MovieDetails> getItemDetails(String type, String id){
        return movieNetworkDataSource.getItemDetails(type, id);
    }
}
