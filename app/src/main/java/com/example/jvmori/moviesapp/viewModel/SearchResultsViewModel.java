package com.example.jvmori.moviesapp.viewModel;

import android.app.Application;
import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.repository.SearchResultsRepository;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;


public class SearchResultsViewModel extends AndroidViewModel {

    private MediatorLiveData<List<MovieItem>> allResults;
    private SearchResultsRepository searchResultsRepository;

    public SearchResultsViewModel(@NonNull Application application) {
        super(application);
        searchResultsRepository = new SearchResultsRepository();
        allResults = new MediatorLiveData<>();
    }

    public LiveData<List<MovieItem>> getResults(String query){
        allResults.addSource(searchResultsRepository.getData(query), new Observer<List<MovieItem>>() {
            @Override
            public void onChanged(List<MovieItem> movieItems) {
                allResults.postValue(movieItems);
            }
        });
        return allResults;
    }
}
