package com.example.jvmori.moviesapp.model.db;

import android.content.Context;
import android.os.AsyncTask;

import com.example.jvmori.moviesapp.model.db.entities.LibraryItem;
import com.example.jvmori.moviesapp.util.Consts;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = LibraryItem.class, version = 1, exportSchema = false)
public abstract class LibraryDatabase extends RoomDatabase
{
    public static LibraryDatabase instance;
    public abstract LibraryDao libraryDao();

    public static synchronized LibraryDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), LibraryDatabase.class, "library_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDatabaseAsyncTask(instance).execute();
        }
    };

    public static class PopulateDatabaseAsyncTask extends AsyncTask<Void, Void, Void>{
        private LibraryDao libraryDao;

        PopulateDatabaseAsyncTask (LibraryDatabase libraryDatabase){
            this.libraryDao = libraryDatabase.libraryDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            for (String item:Consts.libraryItems) {
                libraryDao.insert(new LibraryItem(item));
            }
            return null;
        }
    }
}
