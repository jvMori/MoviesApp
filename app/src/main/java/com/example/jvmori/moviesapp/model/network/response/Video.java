package com.example.jvmori.moviesapp.model.network.response;

import com.google.gson.annotations.SerializedName;

public class Video
{
    @SerializedName("key")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
