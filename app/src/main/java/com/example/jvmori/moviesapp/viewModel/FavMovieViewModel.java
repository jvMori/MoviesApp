package com.example.jvmori.moviesapp.viewModel;

import android.app.Application;

import com.example.jvmori.moviesapp.model.db.entities.FavMovie;
import com.example.jvmori.moviesapp.repository.MovieRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class FavMovieViewModel extends AndroidViewModel {
    private MovieRepository repository;

    public FavMovieViewModel(@NonNull Application application) {
        super(application);
        repository = MovieRepository.getInstance(application);
    }

    public void insert(FavMovie favMovie){
        repository.insert(favMovie);
    }

    public void delete(FavMovie favMovie){
        repository.delete(favMovie);
    }

    public void deleteById(String id){
        repository.deleteById(id);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public LiveData<List<FavMovie>> getAllMovies() {
        return repository.getAllMovies();
    }
    public LiveData<List<FavMovie>> getAllItemsOfType(String nameType){
        return repository.getAllItemsOfType(nameType);
    }
    public LiveData<List<FavMovie>> getMovieFromCollection(String collection){
        return repository.getAllItemsOfCollection(collection);
    }
}
