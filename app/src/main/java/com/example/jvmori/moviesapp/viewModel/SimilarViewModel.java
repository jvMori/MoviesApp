package com.example.jvmori.moviesapp.viewModel;

import android.app.Application;

import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.repository.SimilarRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class SimilarViewModel extends AndroidViewModel {

    private MediatorLiveData<List<MovieItem>> allSimilarMovies;
    private SimilarRepository similarMoviesRepository;

    public SimilarViewModel(@NonNull Application application) {
        super(application);
        similarMoviesRepository = new SimilarRepository();
        allSimilarMovies = new MediatorLiveData<>();
    }

    public LiveData<List<MovieItem>> getAllSimilarMovies(String type, String movieId){
        allSimilarMovies.addSource(similarMoviesRepository.getSimilarMovies(type,movieId), new Observer<List<MovieItem>>() {
            @Override
            public void onChanged(List<MovieItem> movieItems) {
                allSimilarMovies.postValue(movieItems);
            }
        });
        return allSimilarMovies;
    }

}
