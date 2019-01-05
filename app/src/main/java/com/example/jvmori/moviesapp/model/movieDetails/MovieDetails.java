package com.example.jvmori.moviesapp.model.movieDetails;

import com.example.jvmori.moviesapp.model.genre.Genre;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetails
{
    @SerializedName("backdrop_path")
    private String backdropUrl;

    private List<Genre> genres;

    private String overview;

    private String runtime;

    private String tagline;

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

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }
}
