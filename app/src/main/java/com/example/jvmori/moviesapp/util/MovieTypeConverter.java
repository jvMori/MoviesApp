package com.example.jvmori.moviesapp.util;

import com.example.jvmori.moviesapp.model.movie.Movie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import androidx.room.TypeConverter;

public class MovieTypeConverter
{
    static Gson gson = new Gson();

    @TypeConverter
    public static Movie stringToMovie(String data) {
        if (data == null) {
            new Movie();
        }

        Type listType = new TypeToken<Movie>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String movieToString(Movie movie) {
        return gson.toJson(movie);
    }
}
