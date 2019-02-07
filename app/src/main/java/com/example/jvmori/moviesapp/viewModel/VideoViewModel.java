package com.example.jvmori.moviesapp.viewModel;

import android.app.Application;

import com.example.jvmori.moviesapp.model.network.video.Video;
import com.example.jvmori.moviesapp.repository.VideoRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class VideoViewModel extends AndroidViewModel {
    private MediatorLiveData<List<Video>> allVideos;
    private VideoRepository videoRepository;

    public VideoViewModel(@NonNull Application application) {
        super(application);
        allVideos = new MediatorLiveData<>();
        videoRepository = new VideoRepository();
    }

    public LiveData<List<Video>> getAllVideos(String type, String id){
        allVideos.addSource(videoRepository.getVideos(type, id), new Observer<List<Video>>() {
            @Override
            public void onChanged(List<Video> videos) {
                allVideos.postValue(videos);
            }
        });
        return  allVideos;
    }

}
