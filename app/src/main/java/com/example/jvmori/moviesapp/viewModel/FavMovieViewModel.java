package com.example.jvmori.moviesapp.viewModel;

import android.app.Application;

import com.example.jvmori.moviesapp.model.db.entities.LibraryItem;
import com.example.jvmori.moviesapp.model.db.entities.MovieItem;
import com.example.jvmori.moviesapp.repository.MovieRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class FavMovieViewModel extends AndroidViewModel {
    private MovieRepository repository;
    private MutableLiveData<List<MovieItem>> allMovies = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();

    public FavMovieViewModel(@NonNull Application application) {
        super(application);
        repository = MovieRepository.getInstance(application);
    }

    public void setCollectionToMovieItem(LibraryItem libraryItem, MovieItem movieItem) {
        movieItem.setLibraryItem(libraryItem);
    }

    public Observable<List<MovieItem>> checkIfIsInFavs (MovieItem movieItem) {
        return repository.getAllMovies()
                .subscribeOn(Schedulers.io())
                .filter(
                        result -> isFav(movieItem, result)
                );
    }

    private boolean isFav(MovieItem movieItem, List<MovieItem> results){
        for (MovieItem item: results) {
            if (movieItem.getTmdbId().equals(item.getTmdbId()))
                return true;
        }
        return false;
    }

    public void insert(MovieItem favMovie) {
        repository.insert(favMovie);
    }

    public void delete(MovieItem favMovie) {
        repository.delete(favMovie);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public LiveData<List<MovieItem>> getAllMovies() {
        disposable.add(
                repository.getAllMovies()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                result -> allMovies.setValue(result)
                        )
        );

        return allMovies;
    }

    public LiveData<List<MovieItem>> getAllItemsOfType(String nameType) {
        return repository.getAllItemsOfType(nameType);
    }

    public LiveData<List<MovieItem>> getMovieFromCollection(String collection) {
        return repository.getAllItemsOfCollection(collection);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
