package com.example.jvmori.moviesapp.viewModel;

import android.app.Application;

import com.example.jvmori.moviesapp.model.favMovies.FavMovie;
import com.example.jvmori.moviesapp.repository.FavMovieRepository;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class FavMovieViewModel extends AndroidViewModel {
    private FavMovieRepository repository;
    private LiveData<List<FavMovie>> allMovies;

    public FavMovieViewModel(@NonNull Application application) {
        super(application);
        repository = new FavMovieRepository(application);
        allMovies = repository.getAllMovies();
    }

    public void insert(FavMovie favMovie){
        repository.insert(favMovie);
    }

    public void delete(FavMovie favMovie){
        repository.delete(favMovie);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public LiveData<List<FavMovie>> getAllMovies() {
        return allMovies;
    }

    public LiveData<List<FavMovie>> getAllItemsOfType(String type){
        return repository.getAllItemsOfType(type);
    }

}
