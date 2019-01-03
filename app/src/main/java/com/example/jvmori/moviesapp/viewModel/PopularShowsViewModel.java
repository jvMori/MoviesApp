package com.example.jvmori.moviesapp.viewModel;
import android.app.Application;
import com.example.jvmori.moviesapp.model.popularMovies.PopularItem;
import com.example.jvmori.moviesapp.repository.PopularShowsRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class PopularShowsViewModel extends AndroidViewModel {

    private MediatorLiveData<List<PopularItem>> allPopularShows;
    private PopularShowsRepository popularShowsRepository;

    public PopularShowsViewModel(@NonNull Application application) {
        super(application);
        allPopularShows = new MediatorLiveData<>();
        popularShowsRepository = new PopularShowsRepository();
    }

    public LiveData<List<PopularItem>> getAllPopularShows() {
        allPopularShows.addSource(popularShowsRepository.getData(), new Observer<List<PopularItem>>() {
            @Override
            public void onChanged(List<PopularItem> popularItems) {
                allPopularShows.postValue(popularItems);
            }
        });
        return allPopularShows;
    }
}
