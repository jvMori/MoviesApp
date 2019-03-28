package com.example.jvmori.moviesapp.model.network.response;

import com.example.jvmori.moviesapp.model.db.entities.MovieItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieJsonObj
{
    @SerializedName("results")
    private List<MovieItem> results;

    public List<MovieItem> getResults() {
        return results;
    }

    public void setResults(List<MovieItem> results) {
        this.results = results;
    }
}
