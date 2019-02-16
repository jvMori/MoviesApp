package com.example.jvmori.moviesapp.model.network.response;

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
