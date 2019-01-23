package com.example.jvmori.moviesapp.repository;

import com.example.jvmori.moviesapp.model.video.Video;
import com.example.jvmori.moviesapp.model.video.VideoJsonObj;
import com.example.jvmori.moviesapp.util.Consts;
import com.example.jvmori.moviesapp.util.TmdbApi;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoRepository
{
    private MutableLiveData<List<Video>> videos;

    public LiveData<List<Video>> getVideos(String type, String id){
        videos = new MutableLiveData<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TmdbApi tmdbApi = retrofit.create(TmdbApi.class);
        tmdbApi.getVideos(type, id, Consts.api_key).enqueue(new Callback<VideoJsonObj>() {
            @Override
            public void onResponse(Call<VideoJsonObj> call, Response<VideoJsonObj> response) {
                if (!response.isSuccessful())
                    return;
                if (response.body() != null)
                    videos.postValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<VideoJsonObj> call, Throwable t) {

            }
        });

        return videos;
    }
}
