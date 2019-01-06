package com.example.jvmori.moviesapp.viewModel;

import android.app.Application;

import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.repository.SimilarMoviesRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class SimilarMoviesViewModel extends AndroidViewModel {

    private MediatorLiveData<List<MovieItem>> allSimilarMovies;
    private SimilarMoviesRepository similarMoviesRepository;

    public SimilarMoviesViewModel(@NonNull Application application) {
        super(application);
        similarMoviesRepository = new SimilarMoviesRepository();
        allSimilarMovies = new MediatorLiveData<>();
    }

    public LiveData<List<MovieItem>> getAllSimilarMovies(String movieId){
        allSimilarMovies.addSource(similarMoviesRepository.getSimilarMovies(movieId), new Observer<List<MovieItem>>() {
            @Override
            public void onChanged(List<MovieItem> movieItems) {
                allSimilarMovies.postValue(movieItems);
            }
        });
        return allSimilarMovies;
    }

}
