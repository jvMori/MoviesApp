package com.example.jvmori.moviesapp.viewModel;
import android.app.Application;
import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.repository.PopularMoviesRepository;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;


public class PopularMoviesViewModel extends AndroidViewModel {

    private MediatorLiveData<List<MovieItem>> allPopularMovies;
    private PopularMoviesRepository popularMoviesRepository;

    public PopularMoviesViewModel(@NonNull Application application) {
        super(application);
        allPopularMovies = new MediatorLiveData<>();
        popularMoviesRepository = new PopularMoviesRepository();
    }

    public LiveData<List<MovieItem>> getAllPopularMovies() {
       allPopularMovies.addSource(popularMoviesRepository.getData(), new Observer<List<MovieItem>>() {
           @Override
           public void onChanged(List<MovieItem> movieItems) {
               allPopularMovies.postValue(movieItems);
           }
       });
       return allPopularMovies;
    }

}
