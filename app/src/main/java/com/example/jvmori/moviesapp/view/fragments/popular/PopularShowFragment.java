package com.example.jvmori.moviesapp.view.fragments.popular;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.util.Consts;
import com.example.jvmori.moviesapp.view.activities.DetailsActivity;
import com.example.jvmori.moviesapp.view.adapters.PopularMovieAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PopularShowFragment extends PopularFragment {

    public PopularShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_popular_item, container, false);
        loadingScreen = view.findViewById(R.id.loadingPanel);
        setPopularMovieAdapter();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setGenreViewModel();
        setPopularMovieViewModel(Consts.tvShow);

        popularMovieAdapter.setOnItemClickedListener(new PopularMovieAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(MovieItem movieItem) {
//                HomeFragmentDirections.ActionHomeToShowDetailsFragment action = HomeFragmentDirections.actionHomeToShowDetailsFragment();
//                action.setShowId(movieItem.getTmdbId());
//                Navigation.findNavController(view).navigate(action);
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra(Consts.id_parameter, movieItem.getTmdbId());
                intent.putExtra(Consts.type_parameter, Consts.tvShow);
                startActivity(intent);
            }
        });


    }

}
