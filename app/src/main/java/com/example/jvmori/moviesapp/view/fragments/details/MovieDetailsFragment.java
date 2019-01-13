package com.example.jvmori.moviesapp.view.fragments.details;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.util.Consts;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailsFragment extends DetailsFragment {

    public MovieDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        detailsScrollView = view.findViewById(R.id.detailsScrollView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String id = MovieDetailsFragmentArgs.fromBundle(getArguments()).getMovieId();
        createView(Consts.movie, id);
    }

}
