package com.example.jvmori.moviesapp.view.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.genre.Genre;
import com.example.jvmori.moviesapp.model.movie.Movie;
import com.example.jvmori.moviesapp.model.movieDetails.Cast;
import com.example.jvmori.moviesapp.model.movieDetails.MovieDetails;
import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.util.Consts;
import com.example.jvmori.moviesapp.util.LoadImage;
import com.example.jvmori.moviesapp.view.adapters.CastAdapter;
import com.example.jvmori.moviesapp.viewModel.CastViewModel;
import com.example.jvmori.moviesapp.viewModel.MovieDetailsViewModel;
import com.example.jvmori.moviesapp.viewModel.SimilarMoviesViewModel;
import com.example.jvmori.moviesapp.viewModel.SimilarShowsViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailsFragment extends Fragment {

    private Movie movie;
    private CastAdapter castAdapter;
    private View view;

    private ImageView backdrop;
    private ImageView posterImg;
    private TextView titleTextView;
    private TextView category;
    private TextView tagline;
    private TextView overview;
    private RecyclerView recyclerView;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        backdrop = view.findViewById(R.id.backdropImg);
        posterImg = view.findViewById(R.id.posterImg);
        titleTextView = view.findViewById(R.id.titleTextView);
        category = view.findViewById(R.id.category);
        tagline = view.findViewById(R.id.tagline);
        overview = view.findViewById(R.id.overview);
        recyclerView = view.findViewById(R.id.creditsRecyclerView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movie = new Movie();
        setCastAdapter();
        String id = MovieDetailsFragmentArgs.fromBundle(getArguments()).getMovieId();
        setMovieDetailsViewModel(id);
        setCastViewModel(id);
        setSimilarMoviesViewModel(id);
    }


    private void setMovieDetailsViewModel(String movieId){
        MovieDetailsViewModel movieDetailsViewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);
        movieDetailsViewModel.getMovieDetails(movieId).observe(this, new Observer<MovieDetails>() {
            @Override
            public void onChanged(MovieDetails movieDetails) {
                movie.setMovieDetails(movieDetails);
                setDetailViewUI();
                //TODO: loading screen disable
            }
        });
    }

    private void setCastViewModel(String movieId){
        CastViewModel castViewModel = ViewModelProviders.of(this).get(CastViewModel.class);
        castViewModel.getAllCast(movieId).observe(this, new Observer<List<Cast>>() {
            @Override
            public void onChanged(List<Cast> casts) {
                movie.setCast(casts);
                castAdapter.setCastItems(casts);
                //TODO: recycler view adapter
                //TODO: loading screen disable
            }
        });
    }

    private void setSimilarMoviesViewModel(String movieId){
        SimilarMoviesViewModel similarMoviesViewModel = ViewModelProviders.of(this).get(SimilarMoviesViewModel.class);
        similarMoviesViewModel.getAllSimilarMovies(movieId).observe(this, new Observer<List<MovieItem>>() {
            @Override
            public void onChanged(List<MovieItem> movieItems) {
                movie.setSimilarMovies(movieItems);
                //TODO: recycler view adapter
                //TODO: loading screen disable
            }
        });
    }

    private void setSimilarShowsViewModel(String showId){
        SimilarShowsViewModel similarShowsViewModel = ViewModelProviders.of(this).get(SimilarShowsViewModel.class);
        similarShowsViewModel.getSimilarShows(showId).observe(this, new Observer<List<MovieItem>>() {
            @Override
            public void onChanged(List<MovieItem> movieItems) {
                movie.setSimilarMovies(movieItems);
                //TODO: recycler view adapter
                //TODO: loading screen disable
            }
        });
    }

    private void setDetailViewUI(){
        LoadImage.loadImage(backdrop, Consts.base_backdrop_url + movie.getMovieDetails().getBackdropUrl());
        LoadImage.loadImage(posterImg, Consts.base_poster_url + movie.getMovieDetails().getPoster());
        titleTextView.setText(movie.getMovieDetails().getTitle());

        StringBuilder categoryTxt= new StringBuilder();
        for (Genre genre : movie.getMovieDetails().getGenres()) {
            String txtGenre = genre.getName();
            List<Genre> txtGenres = movie.getMovieDetails().getGenres();
            categoryTxt.append(txtGenre);
            if (!txtGenre.equals(txtGenres.get(txtGenres.size()-1).getName()))
                categoryTxt.append(" | ");
        }
        category.setText(categoryTxt);
        tagline.setText(movie.getMovieDetails().getTagline()); //TODO: do something when tagline is empty
        overview.setText(movie.getMovieDetails().getOverview());

    }

    private void setCastAdapter(){
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        castAdapter = new CastAdapter();
        recyclerView.setAdapter(castAdapter);
    }
}
