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
        new InsertAsyncTask(libraryDao).execute(libraryItem);
    }

    public void delete(LibraryItem libraryItem){
        new DeleteAsyncTask(libraryDao).execute(libraryItem);
    }

    public LiveData<List<LibraryItem>> getAllItems(){return libraryDao.getAllItems();}

    public static class InsertAsyncTask extends AsyncTask<LibraryItem, Void, Void>{
        private LibraryDao libraryDao;
        InsertAsyncTask(LibraryDao libraryDao){
            this.libraryDao = libraryDao;
        }
        @Override
        protected Void doInBackground(LibraryItem... libraryItems) {
            libraryDao.insert(libraryItems[0]);
            return null;
        }
    }

    public static class DeleteAsyncTask extends AsyncTask<LibraryItem, Void, Void>{
        private LibraryDao libraryDao;
        DeleteAsyncTask(LibraryDao libraryDao){this.libraryDao = libraryDao;}
        @Override
        protected Void doInBackground(LibraryItem... libraryItems) {
            libraryDao.delete(libraryItems[0]);
            return null;
        }
    }
}
