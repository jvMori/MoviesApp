package com.example.jvmori.moviesapp.viewModel;
import android.app.Application;
import com.example.jvmori.moviesapp.model.db.entities.MovieItem;
import com.example.jvmori.moviesapp.repository.MovieRepository;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


public class PopularItemsViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;

    public PopularItemsViewModel(@NonNull Application application) {
        super(application);
        movieRepository = MovieRepository.getInstance(application);
    }

    public LiveData<List<MovieItem>> getAllPopularMovies(String type) {
        return movieRepository.getAllPopular(type);
    }

}
