package com.example.jvmori.moviesapp.viewModel;
import android.app.Application;
import com.example.jvmori.moviesapp.model.network.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.repository.PopularItemsRepository;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;


public class PopularItemsViewModel extends AndroidViewModel {

    private MediatorLiveData<List<MovieItem>> allPopularMovies;
    private PopularItemsRepository popularMoviesRepository;

    public PopularItemsViewModel(@NonNull Application application) {
        super(application);
        allPopularMovies = new MediatorLiveData<>();
        popularMoviesRepository = new PopularItemsRepository();
    }

    public LiveData<List<MovieItem>> getAllPopularItems(String type) {
       allPopularMovies.addSource(popularMoviesRepository.getData(type), new Observer<List<MovieItem>>() {
           @Override
           public void onChanged(List<MovieItem> movieItems) {
               allPopularMovies.postValue(movieItems);
           }
       });
       return allPopularMovies;
    }

}
