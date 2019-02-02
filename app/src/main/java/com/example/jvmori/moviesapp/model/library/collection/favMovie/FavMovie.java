package com.example.jvmori.moviesapp.model.library.collection.favMovie;

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

    @ColumnInfo(name="collection")
    private String collection;

    @ColumnInfo(name = "tmdbId")
    private String tmdbId;

    @TypeConverters(MovieTypeConverter.class)
    private MovieItem movie;

    public FavMovie(String mediaType, String tmdbId, MovieItem movie, String collection) {
        this.mediaType = mediaType;
        this.tmdbId = tmdbId;
        this.movie = movie;
        this.collection = collection;
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

    public String getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(String tmdbId) {
        this.tmdbId = tmdbId;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }
}
