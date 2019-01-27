package com.example.jvmori.moviesapp.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.jvmori.moviesapp.model.favMovies.FavMovie;
import com.example.jvmori.moviesapp.model.favMovies.MovieDao;
import com.example.jvmori.moviesapp.model.favMovies.MovieDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;

public class FavMovieRepository
{
    private MovieDao movieDao;
    private LiveData<List<FavMovie>> allMovies;

    public FavMovieRepository(Application application){
        MovieDatabase movieDatabase = MovieDatabase.getInstance(application);
        movieDao = movieDatabase.movieDao();
        allMovies = movieDao.getAllItems();
    }

    public void insert(FavMovie favMovie){
        new InsertAsyncTask(movieDao).execute(favMovie);
    }

    public void delete(FavMovie favMovie){
        new DeleteAsyncTask(movieDao).execute(favMovie);
    }

    public void deleteAll(){
        new DeleteAllAsyncTask(movieDao).execute();
    }

    public LiveData<List<FavMovie>> getAllMovies(){
        return allMovies;
    }
    public LiveData<List<FavMovie>> getAllItemsOfType(String type){
        return movieDao.getAllItemsOfType(type);
    }
    private static class InsertAsyncTask extends AsyncTask<FavMovie, Void, Void>{
        private MovieDao movieDao;
        InsertAsyncTask(MovieDao movieDao){
            this.movieDao = movieDao;
        }
        @Override
        protected Void doInBackground(FavMovie... favMovies) {
            movieDao.insert(favMovies[0]);
            return null;
        }
    }
    private static class DeleteAsyncTask extends AsyncTask<FavMovie, Void, Void>{
        private MovieDao movieDao;
        DeleteAsyncTask(MovieDao movieDao){
            this.movieDao = movieDao;
        }
        @Override
        protected Void doInBackground(FavMovie... favMovies) {
            movieDao.delete(favMovies[0]);
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
