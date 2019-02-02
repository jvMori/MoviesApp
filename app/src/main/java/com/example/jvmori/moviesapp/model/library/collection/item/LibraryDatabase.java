package com.example.jvmori.moviesapp.model.library.collection.item;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = LibraryItem.class, version = 1, exportSchema = false)
public abstract class LibraryDatabase extends RoomDatabase
{
    public static LibraryDatabase instance;
    public abstract LibraryDao libraryDao();

    public static synchronized LibraryDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), LibraryDatabase.class, "library_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
