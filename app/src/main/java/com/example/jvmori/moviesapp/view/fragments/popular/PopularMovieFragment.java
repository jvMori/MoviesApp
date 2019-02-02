package com.example.jvmori.moviesapp.view.fragments.popular;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.View;
import com.example.jvmori.moviesapp.util.Consts;
import com.example.jvmori.moviesapp.view.adapters.SetupMovieItemsAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopularMovieFragment extends PopularFragment {

    private String mediaType = Consts.movie;

    public PopularMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SetupMovieItemsAdapter setupMovieItemsAdapter = new SetupMovieItemsAdapter(this.getActivity(), this, this);
        setupMovieItemsAdapter.setMovieItemAdapter(recyclerView, mediaType, new SetupMovieItemsAdapter.SetViewCallback() {
            @Override
            public void callback() {
                setPopularMovieViewModel(mediaType);
            }
        });
        movieItemAdapter = setupMovieItemsAdapter.getMovieItemAdapter();
    }

}
