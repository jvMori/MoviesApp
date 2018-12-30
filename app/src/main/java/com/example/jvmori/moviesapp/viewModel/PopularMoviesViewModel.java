package com.example.jvmori.moviesapp.viewModel;
import android.app.Application;
import com.example.jvmori.moviesapp.model.popularMovies.PopularItem;
import com.example.jvmori.moviesapp.repository.PopularMoviesRepository;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;


public class PopularMoviesViewModel extends AndroidViewModel {

    private MediatorLiveData<List<PopularItem>> allPopularMovies;
    private PopularMoviesRepository popularMoviesRepository;

    public PopularMoviesViewModel(@NonNull Application application) {
        super(application);
        allPopularMovies = new MediatorLiveData<>();
        popularMoviesRepository = new PopularMoviesRepository();
    }

    public LiveData<List<PopularItem>> getAllPopularMovies() {
       allPopularMovies.addSource(popularMoviesRepository.getData(), new Observer<List<PopularItem>>() {
           @Override
           public void onChanged(List<PopularItem> popularItems) {
               allPopularMovies.postValue(popularItems);
           }
       });
       return allPopularMovies;
    }

}
