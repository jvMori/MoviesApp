package com.example.jvmori.moviesapp.viewModel;
import android.app.Application;
import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.repository.PopularShowsRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class PopularShowsViewModel extends AndroidViewModel {

    private MediatorLiveData<List<MovieItem>> allPopularShows;
    private PopularShowsRepository popularShowsRepository;

    public PopularShowsViewModel(@NonNull Application application) {
        super(application);
        allPopularShows = new MediatorLiveData<>();
        popularShowsRepository = new PopularShowsRepository();
    }

    public LiveData<List<MovieItem>> getAllPopularShows() {
        allPopularShows.addSource(popularShowsRepository.getData(), new Observer<List<MovieItem>>() {
            @Override
            public void onChanged(List<MovieItem> movieItems) {
                allPopularShows.postValue(movieItems);
            }
        });
        return allPopularShows;
    }
}
