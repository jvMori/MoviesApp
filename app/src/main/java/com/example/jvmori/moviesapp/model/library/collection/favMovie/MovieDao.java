package com.example.jvmori.moviesapp.model.library.collection.favMovie;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface MovieDao
{
    @Insert
    void insert(FavMovie favMovie);

    @Delete
    void delete(FavMovie favMovie);

    @Query("DELETE FROM movie_table WHERE tmdbId = :tmdbId")
    void deleteById(String tmdbId);

    @Query("DELETE FROM movie_table")
    void deleteAll();

    @Query("SELECT * FROM movie_table")
    LiveData<List<FavMovie>> getAllItems();

    @Query("SELECT * FROM movie_table WHERE collection LIKE :nameOfColl")
    LiveData<List<FavMovie>> getAllFromCollection(String nameOfColl);

    @Query("SELECT * FROM movie_table WHERE media_type LIKE :type")
    LiveData<List<FavMovie>> getAllItemsOfType(String type);

}
