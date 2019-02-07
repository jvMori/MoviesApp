package com.example.jvmori.moviesapp.viewModel;

import android.app.Application;

import com.example.jvmori.moviesapp.model.db.entities.LibraryItem;
import com.example.jvmori.moviesapp.repository.LibraryRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class LibraryViewModel extends AndroidViewModel {

    private MediatorLiveData<List<LibraryItem>> allItems;
    private LibraryRepository libraryRepository;

    public LibraryViewModel(@NonNull Application application) {
        super(application);
        libraryRepository = new LibraryRepository(application);
        allItems = new MediatorLiveData<>();
    }

    public LiveData<List<LibraryItem>> getAllItems(){
        allItems.addSource(libraryRepository.getAllItems(), new Observer<List<LibraryItem>>() {
            @Override
            public void onChanged(List<LibraryItem> libraryItems) {
                allItems.postValue(libraryItems);
            }
        });
        return allItems;
    }
}
