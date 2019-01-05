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
}
