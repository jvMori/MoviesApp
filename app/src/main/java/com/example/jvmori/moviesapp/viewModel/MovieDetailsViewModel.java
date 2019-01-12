package com.example.jvmori.moviesapp.viewModel;

import android.app.Application;
import com.example.jvmori.moviesapp.model.movieDetails.MovieDetails;
import com.example.jvmori.moviesapp.repository.DetailsMovieRepository;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class MovieDetailsViewModel extends AndroidViewModel {

    private MediatorLiveData<MovieDetails> allMovieDetails;
    private DetailsMovieRepository movieDetailsRepository;

    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
        allMovieDetails = new MediatorLiveData<>();
        movieDetailsRepository = new DetailsMovieRepository();
    }

    public LiveData<MovieDetails> getMovieDetails(String movieId){
        allMovieDetails.addSource(movieDetailsRepository.getDetails(movieId), new Observer<MovieDetails>() {
            @Override
            public void onChanged(MovieDetails movieDetails) {
                allMovieDetails.postValue(movieDetails);
            }
        });
        return allMovieDetails;
    }
}
