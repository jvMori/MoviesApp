package com.example.jvmori.moviesapp.view.fragments.details;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
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
import com.example.jvmori.moviesapp.view.adapters.SimilarAdapter;
import com.example.jvmori.moviesapp.viewModel.CastViewModel;
import com.example.jvmori.moviesapp.viewModel.DetailsViewModel;
import com.example.jvmori.moviesapp.viewModel.SimilarViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    protected Movie movie;
    protected CastAdapter castAdapter;
    protected SimilarAdapter similarAdapter;
    protected View view;
    protected ScrollView detailsScrollView;

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        return view;
    }

    protected void createView(final String type, String id){
        detailsScrollView.fullScroll(ScrollView.FOCUS_UP);
        movie = new Movie();
        setCastAdapter();
        setSimilarAdapter();
        setMovieDetailsViewModel(type, id);
        setCastViewModel(type, id);
        setSimilarMoviesViewModel(type,id);
        similarAdapter.setOnSimilarItemClicked(new SimilarAdapter.OnSimilarItemClicked() {
            @Override
            public void onClick(MovieItem item) {
                createView(type, item.getTmdbId());
            }
        });
    }

    protected void setMovieDetailsViewModel(String type, String movieId){
        DetailsViewModel movieDetailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        movieDetailsViewModel.getMovieDetails(type, movieId).observe(this, new Observer<MovieDetails>() {
            @Override
            public void onChanged(MovieDetails movieDetails) {
                movie.setMovieDetails(movieDetails);
                setDetailViewUI(view, movie);
                //TODO: loading screen disable
            }
        });
    }

    protected void setCastViewModel(String type, String movieId){
        CastViewModel castViewModel = ViewModelProviders.of(this).get(CastViewModel.class);
        castViewModel.getAllCast(type, movieId).observe(this, new Observer<List<Cast>>() {
            @Override
            public void onChanged(List<Cast> casts) {
                movie.setCast(casts);
                castAdapter.setCastItems(casts);
                //TODO: loading screen disable
            }
        });
    }

    protected void setSimilarMoviesViewModel(String type, String movieId){
        SimilarViewModel similarMoviesViewModel = ViewModelProviders.of(this).get(SimilarViewModel.class);
        similarMoviesViewModel.getAllSimilarMovies(type,movieId).observe(this, new Observer<List<MovieItem>>() {
            @Override
            public void onChanged(List<MovieItem> movieItems) {
                movie.setSimilarMovies(movieItems);
                similarAdapter.setSimilarItems(movieItems);
                //TODO: loading screen disable
            }
        });
    }

    protected void setCastAdapter(){
        RecyclerView recyclerView = view.findViewById(R.id.creditsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        castAdapter = new CastAdapter();
        recyclerView.setAdapter(castAdapter);
    }

    protected void setSimilarAdapter(){
        RecyclerView recyclerView = view.findViewById(R.id.similarRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        similarAdapter = new SimilarAdapter();
        recyclerView.setAdapter(similarAdapter);
    }


    private void setDetailViewUI(View view, Movie movie){
        ImageView backdrop = view.findViewById(R.id.backdropImg);
        ImageView posterImg = view.findViewById(R.id.posterImg);
        TextView titleTextView = view.findViewById(R.id.titleTextView);
        TextView category = view.findViewById(R.id.category);
        TextView tagline = view.findViewById(R.id.tagline);
        TextView overview = view.findViewById(R.id.overview);

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

}
