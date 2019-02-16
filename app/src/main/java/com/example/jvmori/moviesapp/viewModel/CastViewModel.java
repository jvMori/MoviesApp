package com.example.jvmori.moviesapp.viewModel;

import android.app.Application;

import com.example.jvmori.moviesapp.model.network.movieDetails.Cast;
import com.example.jvmori.moviesapp.repository.MovieRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CastViewModel extends AndroidViewModel {
    private MovieRepository repository;

    public CastViewModel(@NonNull Application application) {
        super(application);
        repository = MovieRepository.getInstance(application);
    }

    public LiveData<List<Cast>> getAllCast(String type, String movieId){
       return repository.getAllCast(type, movieId);
    }
}
