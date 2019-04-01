package com.example.jvmori.moviesapp.viewModel;
import android.app.Application;
import android.util.Log;

import com.example.jvmori.moviesapp.model.db.entities.LibraryItem;
import com.example.jvmori.moviesapp.model.db.entities.MovieItem;
import com.example.jvmori.moviesapp.repository.MovieRepository;
import com.example.jvmori.moviesapp.util.Consts;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class PopularItemsViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;
    private CompositeDisposable disposable = new CompositeDisposable();

    public PopularItemsViewModel(@NonNull Application application) {
        super(application);
        movieRepository = MovieRepository.getInstance(application);
    }

    public LiveData<List<MovieItem>> getAllPopularMovies(String type) {
        MutableLiveData<List<MovieItem>> allPopular = new MutableLiveData<>();
        disposable.add(
                movieRepository.getAllPopular(type)
                        .subscribe(
                                allPopular::postValue,
                                error -> Log.i("Error", "error")
                        )
        );
        return allPopular;
    }
    private Single<MovieItem> getSaved(MovieItem item){return movieRepository.getSaved(item);}

    private boolean checkIfEquals(MovieItem newItem, MovieItem item){
        return newItem.getTmdbId().equals(item.getTmdbId()) &&
                item.getLibraryItem().getNameOfCollection().equals(Consts.libraryItems.get(0));
    }

    private void addMediaType(MovieItem movieItem, String type){
        movieItem.setMediaType(type);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
