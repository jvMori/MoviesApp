package com.example.jvmori.moviesapp.model;

import android.app.Application;

import com.example.jvmori.moviesapp.util.DatabaseCallback;

import java.util.List;

import androidx.lifecycle.LiveData;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class MovieRepository
{
    private MovieDao movieDao;
    private LiveData<List<Movie>> allMovies;

    public MovieRepository(Application application){
        MovieDatabase movieDatabase = MovieDatabase.getInstance(application);
        movieDao = movieDatabase.movieDao();
        allMovies = movieDao.getAllMovies();
    }

    public void Insert(final DatabaseCallback databaseCallback, final Movie movie){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                movieDao.insert(movie);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                databaseCallback.onMovieAdded();
            }

            @Override
            public void onError(Throwable e) {
                databaseCallback.onDataNotAvailable();
            }
        });
    }

    public void Delete(final DatabaseCallback databaseCallback, final Movie movie){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                movieDao.delete(movie);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        databaseCallback.onMovieDeleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        databaseCallback.onDataNotAvailable();
                    }
                });
    }

    public void DeleteAll(final DatabaseCallback databaseCallback){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                movieDao.deleteAll();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        databaseCallback.onAllMoviesDeleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        databaseCallback.onDataNotAvailable();
                    }
                });
    }

    public LiveData<List<Movie>> getAllMovies(){
        return allMovies;
    }

}
