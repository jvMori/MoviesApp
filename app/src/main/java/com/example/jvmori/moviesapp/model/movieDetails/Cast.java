package com.example.jvmori.moviesapp.model.movieDetails;

import com.google.gson.annotations.SerializedName;

public class Cast
{
    private String name;

    @SerializedName("profile_path")
    private String posterUrl;
}
