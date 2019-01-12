package com.example.jvmori.moviesapp.view.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.genre.Genre;
import com.example.jvmori.moviesapp.model.movieDetails.MovieDetails;
import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.util.Consts;
import com.example.jvmori.moviesapp.view.activities.MovieDetailsActivity;
import com.example.jvmori.moviesapp.view.adapters.PopularMovieAdapter;
import com.example.jvmori.moviesapp.viewModel.GenreViewModel;
import com.example.jvmori.moviesapp.viewModel.PopularMoviesViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularMovieFragment extends Fragment {

    private View view;
    private PopularMovieAdapter popularMovieAdapter;
    private RelativeLayout loadingScreen;
    private RecyclerView recyclerView;

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
        setPopularMovieViewModel();

        popularMovieAdapter.setOnItemClickedListener(new PopularMovieAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(MovieItem movieItem) {
//                Intent intent = new Intent(getContext(), MovieDetailsActivity.class);
//                intent.putExtra(Consts.item_clicked_id, movieItem.getTmdbId());
//                startActivity(intent);
                //Navigation.findNavController(view).navigate(R.id.action_destination_home_to_movieDetailsFragment);
                HomeFragmentDirections.HomeToMovieDetails action = HomeFragmentDirections.homeToMovieDetails();
                action.setMovieId(movieItem.getTmdbId());
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    private void setPopularMovieAdapter(){
        recyclerView = view.findViewById(R.id.movieRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        popularMovieAdapter = new PopularMovieAdapter();
        recyclerView.setAdapter(popularMovieAdapter);
    }

    private void setGenreViewModel(){
        GenreViewModel genreViewModel = ViewModelProviders.of(this).get(GenreViewModel.class);
        genreViewModel.getData().observe(this, new Observer<List<Genre>>() {
            @Override
            public void onChanged(List<Genre> genres) {
                if (genres == null)
                    return;
                popularMovieAdapter.setGenres(genres);
            }
        });
    }

    private void setPopularMovieViewModel(){
        PopularMoviesViewModel movieViewModel = ViewModelProviders.of(this).get(PopularMoviesViewModel.class);
        movieViewModel.getAllPopularMovies().observe(this, new Observer<List<MovieItem>>() {
            @Override
            public void onChanged(List<MovieItem> movies) {
                if (movies == null)
                    return;
                popularMovieAdapter.setMovieItems(movies);
                loadingScreen.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }

}
