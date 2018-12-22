package com.example.jvmori.moviesapp.model;

import android.graphics.Bitmap;
import android.media.Image;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_table")
public class Movie
{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    //private Bitmap poster;
    private String year;
    private String genre;
    private String director;
    private String actors;
    private String description;
    private String ratingRT;
    private  String ratingImdb;

    public Movie(String title, String year, String genre, String director, String actors, String description, String ratingRT, String ratingImdb) {
        this.title = title;
        //this.poster = poster;
        this.year = year;
        this.genre = genre;
        this.director = director;
        this.actors = actors;
        this.description = description;
        this.ratingRT = ratingRT;
        this.ratingImdb = ratingImdb;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

//    public Bitmap getPoster() {
//        return poster;
//    }

    public String getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getActors() {
        return actors;
    }

    public String getDescription() {
        return description;
    }

    public String getRatingRT() {
        return ratingRT;
    }

    public String getRatingImdb() {
        return ratingImdb;
    }
}
