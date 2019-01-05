package com.example.jvmori.moviesapp.model.movieDetails;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreditsJsonObj
{
    @SerializedName("cast")
    private List<Cast> cast;
}
