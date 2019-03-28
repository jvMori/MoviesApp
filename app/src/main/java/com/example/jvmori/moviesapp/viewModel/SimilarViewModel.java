package com.example.jvmori.moviesapp.viewModel;

import android.app.Application;

import com.example.jvmori.moviesapp.model.db.entities.MovieItem;
import com.example.jvmori.moviesapp.repository.MovieRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SimilarViewModel extends AndroidViewModel {

    private MovieRepository repository;

    public SimilarViewModel(@NonNull Application application) {
        super(application);
        repository = MovieRepository.getInstance(application);
    }

    public LiveData<List<MovieItem>> getAllSimilarMovies(String type, String movieId){
       return repository.getSimilar(type, movieId);
    }

}
