package com.example.jvmori.moviesapp.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.jvmori.moviesapp.model.db.LibraryDao;
import com.example.jvmori.moviesapp.model.db.MovieDao;
import com.example.jvmori.moviesapp.model.db.MovieDatabase;
import com.example.jvmori.moviesapp.model.db.entities.FavMovie;
import com.example.jvmori.moviesapp.model.db.entities.LibraryItem;
import com.example.jvmori.moviesapp.model.network.MovieNetworkDataSource;
import com.example.jvmori.moviesapp.model.network.response.Genre;
import com.example.jvmori.moviesapp.model.network.response.Cast;
import com.example.jvmori.moviesapp.model.network.response.MovieDetails;
import com.example.jvmori.moviesapp.model.network.response.MovieItem;
import com.example.jvmori.moviesapp.model.network.response.Video;

import java.util.List;

import androidx.lifecycle.LiveData;

public class MovieRepository
{
    private static final Object LOCK = new Object();
    private static MovieRepository instance;
    private MovieNetworkDataSource movieNetworkDataSource;
    private MovieDao movieDao;
    private LibraryDao libraryDao;

    private MovieRepository(Application application){
        movieDao = MovieDatabase.getInstance(application).movieDao();
        libraryDao = MovieDatabase.getInstance(application).libraryDao();
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

    public LiveData<List<Cast>> getAllCast(String type, String movieId){
        return movieNetworkDataSource.getAllCasts(type, movieId);
    }

    public LiveData<List<Genre>> getAllGenres(String type){
        return movieNetworkDataSource.getGenres(type);
    }

    public LiveData<List<MovieItem>> getSearchedResults(String query){
        return movieNetworkDataSource.getSearchResults(query);
    }

    public LiveData<List<MovieItem>> getSimilar(String type, String id){
       return movieNetworkDataSource.getSimilar(type, id);
    }

    public LiveData<List<Video>> getAllVideos(String type, String id){
        return movieNetworkDataSource.getVideos(type, id);
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
        return movieDao.getAllItems();
    }

    public LiveData<List<FavMovie>> getAllItemsOfType(String nameType){
        return movieDao.getAllItemsOfType(nameType);
    }

    public LiveData<List<FavMovie>> getAllItemsOfCollection(String name){
        return movieDao.getAllFromCollection(name);
    }
    public void insert(LibraryItem libraryItem){
        new CustomAsyncTask(new OnAsyncTask() {
            @Override
            public void onAsyncTask(LibraryItem libraryItem) {
                libraryDao.insert(libraryItem);
            }
        }).execute(libraryItem);
    }

    public void delete(LibraryItem libraryItem){
        new CustomAsyncTask(new OnAsyncTask() {
            @Override
            public void onAsyncTask(LibraryItem libraryItem) {
                libraryDao.delete(libraryItem);
            }
        }).execute(libraryItem);
    }

    public void update(LibraryItem libraryItem){
        new CustomAsyncTask(new OnAsyncTask() {
            @Override
            public void onAsyncTask(LibraryItem libraryItem) {
                libraryDao.update(libraryItem);
            }
        }).execute(libraryItem);
    }

    public LiveData<List<LibraryItem>> getAllColections(){return libraryDao.getAllItems();}

    public static class CustomAsyncTask extends AsyncTask<LibraryItem, Void, Void> {
        OnAsyncTask callback;
        CustomAsyncTask(OnAsyncTask callback){
            this.callback = callback;
        }
        @Override
        protected Void doInBackground(LibraryItem... libraryItems) {
            callback.onAsyncTask(libraryItems[0]);
            return null;
        }
    }

    public interface OnAsyncTask {
        void onAsyncTask(LibraryItem libraryItem);
    }
    private static class InsertAsyncTask extends AsyncTask<FavMovie, Void, Void> {
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
