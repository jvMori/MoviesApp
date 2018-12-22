package com.example.jvmori.moviesapp;

import java.util.List;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

public interface MovieDao
{
    @Insert
    void insert(Movie movie);

    @Delete
    void delete(Movie mOvie);

    @Query("DELETE FROM movie_table")
    void deleteAll();

    @Query("SELECT * FROM movie_table")
    List<Movie> getAllMovies();
}
