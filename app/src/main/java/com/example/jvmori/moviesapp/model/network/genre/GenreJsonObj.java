package com.example.jvmori.moviesapp.model.network.genre;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenreJsonObj
{
    @SerializedName("genres")
    private List<Genre> genres;

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
