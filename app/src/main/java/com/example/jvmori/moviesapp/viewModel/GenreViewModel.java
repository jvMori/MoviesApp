package com.example.jvmori.moviesapp.viewModel;

import android.app.Application;

import com.example.jvmori.moviesapp.model.genre.Genre;
import com.example.jvmori.moviesapp.repository.GenresRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class GenreViewModel extends AndroidViewModel {

    private MediatorLiveData<List<Genre>> allGenres;
    private GenresRepository genresRepository;

    public GenreViewModel(@NonNull Application application) {
        super(application);
        allGenres = new MediatorLiveData<>();
        genresRepository = new GenresRepository();
    }

    public LiveData<List<Genre>> getData(String type){
        allGenres.addSource(genresRepository.getGenres(type), new Observer<List<Genre>>() {
            @Override
            public void onChanged(List<Genre> genres) {
                allGenres.postValue(genres);
            }
        });
        return allGenres;
    }
}
