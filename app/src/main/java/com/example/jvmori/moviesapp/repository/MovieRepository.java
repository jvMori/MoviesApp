package com.example.jvmori.moviesapp.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.jvmori.moviesapp.model.Movie;
import com.example.jvmori.moviesapp.model.MovieDao;
import com.example.jvmori.moviesapp.model.MovieDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;

public class MovieRepository
{
    private MovieDao movieDao;
    private LiveData<List<Movie>> allMovies;

    public MovieRepository(Application application){
        MovieDatabase movieDatabase = MovieDatabase.getInstance(application);
        movieDao = movieDatabase.movieDao();
        allMovies = movieDao.getAllMovies();
    }

    public void insert(Movie movie){
        new InsertAsyncTask(movieDao).execute(movie);
    }

    public void delete(Movie movie){
        new DeleteAsyncTask(movieDao).execute(movie);
    }

    public void deleteAll(){
        new DeleteAllAsyncTask(movieDao).execute();
    }

    public LiveData<List<Movie>> getAllMovies(){
        return allMovies;
    }

    private static class InsertAsyncTask extends AsyncTask<Movie, Void, Void>{
        private MovieDao movieDao;
        InsertAsyncTask(MovieDao movieDao){
            this.movieDao = movieDao;
        }
        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.insert(movies[0]);
            return null;
        }
    }
    private static class DeleteAsyncTask extends AsyncTask<Movie, Void, Void>{
        private MovieDao movieDao;
        DeleteAsyncTask(MovieDao movieDao){
            this.movieDao = movieDao;
        }
        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.delete(movies[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void>{
        private MovieDao movieDao;
        DeleteAllAsyncTask(MovieDao movieDao){
            this.movieDao = movieDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            movieDao.deleteAll();
            return null;
        }
    }


}
