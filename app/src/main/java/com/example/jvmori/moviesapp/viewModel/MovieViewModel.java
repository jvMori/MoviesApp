package com.example.jvmori.moviesapp.viewModel;

import android.app.Application;

import com.example.jvmori.moviesapp.model.Movie;
import com.example.jvmori.moviesapp.repository.MovieRepository;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MovieViewModel extends AndroidViewModel {
    private MovieRepository repository;
    private LiveData<List<Movie>> allMovies;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieRepository(application);
        allMovies = repository.getAllMovies();
    }

    public void insert(Movie movie){
        repository.insert(movie);
    }

    public void delete(Movie movie){
        repository.delete(movie);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public LiveData<List<Movie>> getAllMovies() {
        return allMovies;
    }
}