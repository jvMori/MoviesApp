package com.example.jvmori.moviesapp.model.movie;

import com.example.jvmori.moviesapp.model.network.response.Cast;
import com.example.jvmori.moviesapp.model.network.response.MovieDetails;
import com.example.jvmori.moviesapp.model.network.response.MovieItem;

import java.util.List;

public class Movie
{
    private MovieDetails movieDetails;
    private List<Cast> cast;
    private List<MovieItem> similarMovies;

    public MovieDetails getMovieDetails() {
        return movieDetails;
    }

    public void setMovieDetails(MovieDetails movieDetails) {
        this.movieDetails = movieDetails;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public List<MovieItem> getSimilarMovies() {
        return similarMovies;
    }

    public void setSimilarMovies(List<MovieItem> similarMovies) {
        this.similarMovies = similarMovies;
    }
}
