package com.example.jvmori.moviesapp.model.db;

import com.example.jvmori.moviesapp.model.network.response.MovieItem;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Observable;

@Dao
public interface MovieDao
{
    @Insert
    void insert(MovieItem movieItem);

    @Delete
    void delete(MovieItem movieItem);

    @Query("DELETE FROM movie_table WHERE tmdbId = :tmdbId")
    void deleteById(String tmdbId);

    @Query("DELETE FROM movie_table")
    void deleteAll();

    @Query("SELECT * FROM movie_table")
    Observable<List<MovieItem>> getAllItems();

    @Query("SELECT * FROM movie_table WHERE nameOfCollection LIKE :nameOfColl")
    LiveData<List<MovieItem>> getAllFromCollection(String nameOfColl);

    @Query("SELECT * FROM movie_table WHERE mediaType LIKE :nameType")
    LiveData<List<MovieItem>> getAllItemsOfType(String nameType);

}
