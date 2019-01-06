package com.example.jvmori.moviesapp.viewModel;

import android.app.Application;

import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.repository.SimilarShowsRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class SimilarShowsViewModel extends AndroidViewModel {

    private MediatorLiveData<List<MovieItem>> similarShows;
    private SimilarShowsRepository similarShowsRepository;

    public SimilarShowsViewModel(@NonNull Application application) {
        super(application);
        similarShowsRepository = new SimilarShowsRepository();
        similarShows = new MediatorLiveData<>();
    }

    public LiveData<List<MovieItem>> getSimilarShows(String showId){
        similarShows.addSource(similarShowsRepository.getSimilarShows(showId), new Observer<List<MovieItem>>() {
            @Override
            public void onChanged(List<MovieItem> movieItems) {
                similarShows.postValue(movieItems);
            }
        });

        return similarShows;
    }
}
