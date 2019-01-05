package com.example.jvmori.moviesapp.viewModel;

import android.app.Application;

import com.example.jvmori.moviesapp.model.movieDetails.Cast;
import com.example.jvmori.moviesapp.repository.CastRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class CastViewModel extends AndroidViewModel {
    private MediatorLiveData<List<Cast>> allCast;
    private CastRepository castRepository;

    public CastViewModel(@NonNull Application application) {
        super(application);
        allCast = new MediatorLiveData<>();
        castRepository = new CastRepository();
    }

    public LiveData<List<Cast>> getAllCast(String movieId){
        allCast.addSource(castRepository.getAllCasts(movieId), new Observer<List<Cast>>() {
            @Override
            public void onChanged(List<Cast> casts) {
                allCast.postValue(casts);
            }
        });
        return allCast;
    }
}
