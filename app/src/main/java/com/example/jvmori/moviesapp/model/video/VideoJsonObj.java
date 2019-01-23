package com.example.jvmori.moviesapp.model.video;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoJsonObj
{
    @SerializedName("results")
    private List<Video> results;

    public List<Video> getResults() {
        return results;
    }

    public void setResults(List<Video> results) {
        this.results = results;
    }
}
