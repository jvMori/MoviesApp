package com.example.jvmori.moviesapp.model.movieDetails;

import com.google.gson.annotations.SerializedName;

public class Cast
{
    private String name;

    @SerializedName("profile_path")
    private String posterUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
}
