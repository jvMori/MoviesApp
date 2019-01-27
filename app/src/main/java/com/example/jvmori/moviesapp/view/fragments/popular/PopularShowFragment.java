package com.example.jvmori.moviesapp.view.fragments.popular;

import android.os.Bundle;
import android.view.View;
import com.example.jvmori.moviesapp.util.Consts;
import com.example.jvmori.moviesapp.view.adapters.SetupMovieItemsAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PopularShowFragment extends PopularFragment {

    private String mediaType = Consts.tvShow;

    public PopularShowFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SetupMovieItemsAdapter setupMovieItemsAdapter = new SetupMovieItemsAdapter(this.getActivity(), this, this);
        setupMovieItemsAdapter.setMovieItemAdapter(recyclerView, mediaType,null, new SetupMovieItemsAdapter.SetViewCallback() {
            @Override
            public void callback() {
                setPopularMovieViewModel(mediaType);
            }
        });
        movieItemAdapter = setupMovieItemsAdapter.getMovieItemAdapter();
    }
}
