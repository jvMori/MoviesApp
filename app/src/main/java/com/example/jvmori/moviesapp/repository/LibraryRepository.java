package com.example.jvmori.moviesapp.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.jvmori.moviesapp.model.library.collection.item.LibraryDao;
import com.example.jvmori.moviesapp.model.library.collection.item.LibraryDatabase;
import com.example.jvmori.moviesapp.model.library.collection.item.LibraryItem;

import java.util.List;

import androidx.lifecycle.LiveData;

public class LibraryRepository
{
    private LibraryDao libraryDao;
    private LiveData<List<LibraryItem>> allLibraryItems;

    public LibraryRepository(Application application){
        LibraryDatabase libraryDatabase = LibraryDatabase.getInstance(application);
        libraryDao = libraryDatabase.libraryDao();
        allLibraryItems = libraryDao.getAllItems();
    }

    public void insert(LibraryItem libraryItem){
        new CustomAsyncTask(new OnAsyncTask() {
            @Override
            public void onAsyncTask(LibraryItem libraryItem) {
                libraryDao.insert(libraryItem);
            }
        }).execute(libraryItem);
    }

    public void delete(LibraryItem libraryItem){
        new CustomAsyncTask(new OnAsyncTask() {
            @Override
            public void onAsyncTask(LibraryItem libraryItem) {
                libraryDao.delete(libraryItem);
            }
        }).execute(libraryItem);
    }

    public void update(LibraryItem libraryItem){
        new CustomAsyncTask(new OnAsyncTask() {
            @Override
            public void onAsyncTask(LibraryItem libraryItem) {
                libraryDao.update(libraryItem);
            }
        }).execute(libraryItem);
    }

    public LiveData<List<LibraryItem>> getAllItems(){return libraryDao.getAllItems();}

    public static class CustomAsyncTask extends AsyncTask<LibraryItem, Void, Void> {
        OnAsyncTask callback;
        CustomAsyncTask(OnAsyncTask callback){
            this.callback = callback;
        }
        @Override
        protected Void doInBackground(LibraryItem... libraryItems) {
            callback.onAsyncTask(libraryItems[0]);
            return null;
        }
    }

    public interface OnAsyncTask {
        void onAsyncTask(LibraryItem libraryItem);
    }
}
