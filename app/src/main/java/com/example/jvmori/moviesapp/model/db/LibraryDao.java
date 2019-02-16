package com.example.jvmori.moviesapp.model.db;

import com.example.jvmori.moviesapp.model.db.entities.LibraryItem;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface LibraryDao
{
    @Insert
    void insert(LibraryItem item);

    @Delete
    void delete(LibraryItem item);

    @Query("DELETE FROM libraryItems")
    void deleteAll();

    @Update()
    void update(LibraryItem item);

    @Query("SELECT * FROM libraryItems")
    LiveData<List<LibraryItem>> getAllItems();

}
