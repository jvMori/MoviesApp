package com.example.jvmori.moviesapp.util;

import com.example.jvmori.moviesapp.model.network.response.MovieItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import androidx.room.TypeConverter;

public class MovieTypeConverter
{
    static Gson gson = new Gson();

    @TypeConverter
    public static MovieItem stringToMovie(String data) {
        if (data == null) {
            new MovieItem();
        }

        Type listType = new TypeToken<MovieItem>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String movieToString(MovieItem movie) {
        return gson.toJson(movie);
    }
}
