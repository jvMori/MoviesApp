package com.example.jvmori.moviesapp.util;

public interface DatabaseCallback {
    void onMovieDeleted();

    void onMovieAdded();

    void onDataNotAvailable();

    void onAllMoviesDeleted();
}
