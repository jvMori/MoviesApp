package com.example.jvmori.moviesapp.view.fragments.popular;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.genre.Genre;
import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.util.Consts;
import com.example.jvmori.moviesapp.view.activities.DetailsActivity;
import com.example.jvmori.moviesapp.view.adapters.PopularMovieAdapter;
import com.example.jvmori.moviesapp.view.fragments.HomeFragmentDirections;
import com.example.jvmori.moviesapp.viewModel.GenreViewModel;
import com.example.jvmori.moviesapp.viewModel.PopularItemsViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularMovieFragment extends PopularFragment {


    public PopularMovieFragment() {
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
        setPopularMovieViewModel(Consts.movie);

        popularMovieAdapter.setOnItemClickedListener(new PopularMovieAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(MovieItem movieItem) {
//                HomeFragmentDirections.HomeToMovieDetails action = HomeFragmentDirections.homeToMovieDetails();
//                action.setMovieId(movieItem.getTmdbId());
//                Navigation.findNavController(view).navigate(action);
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra(Consts.id_parameter, movieItem.getTmdbId());
                intent.putExtra(Consts.type_parameter, Consts.movie);
                startActivity(intent);

            }
        });
    }


}
