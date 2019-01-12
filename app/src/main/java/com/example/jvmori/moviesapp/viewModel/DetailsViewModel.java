package com.example.jvmori.moviesapp.viewModel;

import android.app.Application;
import com.example.jvmori.moviesapp.model.movieDetails.MovieDetails;
import com.example.jvmori.moviesapp.repository.DetailsRepository;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class DetailsViewModel extends AndroidViewModel {

    private MediatorLiveData<MovieDetails> allMovieDetails;
    private DetailsRepository movieDetailsRepository;

    public DetailsViewModel(@NonNull Application application) {
        super(application);
        allMovieDetails = new MediatorLiveData<>();
        movieDetailsRepository = new DetailsRepository();
    }

    public LiveData<MovieDetails> getMovieDetails(String type, String movieId){
        allMovieDetails.addSource(movieDetailsRepository.getDetails(type, movieId), new Observer<MovieDetails>() {
            @Override
            public void onChanged(MovieDetails movieDetails) {
                allMovieDetails.postValue(movieDetails);
            }
        });
        return allMovieDetails;
    }
}
