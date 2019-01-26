package com.example.jvmori.moviesapp.model.favMovies;

import com.example.jvmori.moviesapp.model.movie.Movie;
import com.example.jvmori.moviesapp.util.MovieTypeConverter;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "movie_table")
public class FavMovie
{

    @PrimaryKey(autoGenerate = true)
    private int id;
    @TypeConverters(MovieTypeConverter.class)
    private Movie movie;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
