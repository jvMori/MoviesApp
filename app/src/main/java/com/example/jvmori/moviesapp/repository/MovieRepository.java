package com.example.jvmori.moviesapp.repository;

import android.app.Application;

import com.example.jvmori.moviesapp.model.db.MovieDao;
import com.example.jvmori.moviesapp.model.db.MovieDatabase;

public class MovieRepository
{
    private static final Object LOCK = new Object();
    private static MovieRepository instance;
    private MovieDao movieDao;

    private MovieRepository(Application application){
        movieDao = MovieDatabase.getInstance(application).movieDao();
    }

    public synchronized static MovieRepository getInstance(Application context) {
        if (instance == null) {
            synchronized (LOCK) {
                instance = new MovieRepository(context);
            }
        }
        return instance;
    }


}
