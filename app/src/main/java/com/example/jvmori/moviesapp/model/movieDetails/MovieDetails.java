package com.example.jvmori.moviesapp.model.movieDetails;

import com.example.jvmori.moviesapp.model.genre.Genre;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetails
{
    @SerializedName("id")
    private String tmdbId;

    @SerializedName(value="title", alternate = "name")
    private String title;

    @SerializedName("runtime")
    private String runtime;

    @SerializedName(("poster_path"))
    private String poster;

    @SerializedName(value = "release_date", alternate = "first_air_date")
    private String year;

    @SerializedName(("vote_average"))
    private String rating;

    @SerializedName(("vote_count"))
    private String reviews;

    @SerializedName("backdrop_path")
    private String backdropUrl;

    private List<Genre> genres;

    private String overview;

    @SerializedName(value = "tagline", alternate = "homepage")
    private String tagline;

    public String getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(String tmdbId) {
        this.tmdbId = tmdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getBackdropUrl() {
        return backdropUrl;
    }

    public void setBackdropUrl(String backdropUrl) {
        this.backdropUrl = backdropUrl;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }
}
