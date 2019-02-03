package com.example.jvmori.moviesapp.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.jvmori.moviesapp.model.library.collection.favMovie.FavMovie;
import com.example.jvmori.moviesapp.model.library.collection.favMovie.MovieDao;
import com.example.jvmori.moviesapp.model.library.collection.favMovie.MovieDatabase;

import java.security.acl.Owner;
import java.util.List;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

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

    public void deleteById(String id){new DeleteByIdAsyncTask(movieDao).execute(id);}

    public void deleteAll(){
        new DeleteAllAsyncTask(movieDao).execute();
    }

    public LiveData<List<FavMovie>> getAllMovies(){
        return allMovies;
    }

    public LiveData<List<FavMovie>> getAllItemsOfType(String type){
        return movieDao.getAllItemsOfType(type);
    }

    public LiveData<List<FavMovie>> getAllItemsOfCollection(String name){
       return movieDao.getAllFromCollection(name);
    }

    public static class GetItemsAsyncTask extends AsyncTask<String, Void, LiveData<List<FavMovie>>>{
        private MovieDao movieDao;
        private LiveData<List<FavMovie>> result;
        GetItemsAsyncTask(MovieDao movieDao, LiveData<List<FavMovie>> result){
            this.movieDao = movieDao;
            this.result =result;
        }
        @Override
        protected LiveData<List<FavMovie>> doInBackground(String... strings) {
            movieDao.getAllFromCollection(strings[0]);
            return null;
        }

        @Override
        protected void onPostExecute(LiveData<List<FavMovie>> listLiveData) {
            super.onPostExecute(listLiveData);
            result = listLiveData;
        }
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

    private static class DeleteByIdAsyncTask extends AsyncTask<String, Void, Void>{
        private MovieDao movieDao;
        DeleteByIdAsyncTask(MovieDao movieDao){
            this.movieDao = movieDao;
        }
        @Override
        protected Void doInBackground(String... strings) {
            movieDao.deleteById(strings[0]);
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
