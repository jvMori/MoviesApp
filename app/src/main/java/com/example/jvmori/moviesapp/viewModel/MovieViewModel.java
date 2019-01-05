package com.example.jvmori.moviesapp.viewModel;

import android.app.Application;

import com.example.jvmori.moviesapp.model.favMovies.FavMovie;
import com.example.jvmori.moviesapp.repository.MovieRepository;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MovieViewModel extends AndroidViewModel {
    private MovieRepository repository;
    private LiveData<List<FavMovie>> allMovies;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieRepository(application);
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
}
