package com.example.jvmori.moviesapp.viewModel;

import android.app.Application;

import com.example.jvmori.moviesapp.model.db.entities.LibraryItem;
import com.example.jvmori.moviesapp.repository.MovieRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class LibraryViewModel extends AndroidViewModel {

    private MovieRepository repository;

    public LibraryViewModel(@NonNull Application application) {
        super(application);
        repository = MovieRepository.getInstance(application);
    }

    public LiveData<List<LibraryItem>> getAllItems(){
       return repository.getAllColections();
    }
}
