package com.example.jvmori.moviesapp.model.popularMovies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularMoviesJsonObj
{
    @SerializedName("results")
    private List<PopularItem> results;

    public List<PopularItem> getResults() {
        return results;
    }

    public void setResults(List<PopularItem> results) {
        this.results = results;
    }
}
