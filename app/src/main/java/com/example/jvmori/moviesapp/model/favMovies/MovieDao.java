package com.example.jvmori.moviesapp.model.favMovies;

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

    @Query("DELETE FROM movie_table")
    void deleteAll();

    @Query("SELECT * FROM movie_table")
    LiveData<List<FavMovie>> getAllMovies();
}
