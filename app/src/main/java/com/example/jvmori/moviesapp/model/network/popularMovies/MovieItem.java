package com.example.jvmori.moviesapp.model.network.popularMovies;
import com.google.gson.annotations.SerializedName;
import java.util.List;

import androidx.room.Entity;

public class MovieItem
{
    @SerializedName("id")
    private String tmdbId;

    @SerializedName("media_type")
    private String mediaType;

    @SerializedName(value="title", alternate = "name")
    private String title;

    @SerializedName(("poster_path"))
    private String poster;

    @SerializedName(value = "release_date", alternate = "first_air_date")
    private String year;

    @SerializedName(("vote_average"))
    private String rating;

    @SerializedName(("genre_ids"))
    private List<String> categories;

    @SerializedName(("vote_count"))
    private String reviews;

    public String getTmdbId() {
        return tmdbId;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getYear() {
        return year;
    }

    public String getRating() {
        return rating;
    }

    public List<String> getCategories() {
        return categories;
    }

    public String getReviews() {
        return reviews;
    }

    public void setTmdbId(String tmdbId) {
        this.tmdbId = tmdbId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
