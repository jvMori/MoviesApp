package com.example.jvmori.moviesapp.model.library.collection.item;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

public interface LibraryDao
{
    @Insert
    void insert(LibraryItem item);

    @Delete
    void delete(LibraryItem item);

    @Query("DELETE FROM libraryItems")
    void deleteAll();

    @Query("SELECT * FROM libraryItems")
    LiveData<List<LibraryItem>> getAllItems();

}
