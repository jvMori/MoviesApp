package com.example.jvmori.moviesapp.model.favMovies;

import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.util.MovieTypeConverter;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "movie_table")
public class FavMovie
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "media_type")
    private String mediaType;

    @TypeConverters(MovieTypeConverter.class)
    private MovieItem movie;

    public FavMovie(MovieItem movie, String mediaType) {
        this.movie = movie;
    }

    public MovieItem getMovie() {
        return movie;
    }

    public void setMovie(MovieItem movie) {
        this.movie = movie;
    }

    public int getId() {
        return id;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setId(int id) {
        this.id = id;
    }

}
