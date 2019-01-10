package com.example.jvmori.moviesapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.jvmori.moviesapp.viewModel.CastViewModel;
import com.example.jvmori.moviesapp.viewModel.MovieDetailsViewModel;
import com.example.jvmori.moviesapp.viewModel.SimilarMoviesViewModel;
import com.example.jvmori.moviesapp.viewModel.SimilarShowsViewModel;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity {
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        movie = new Movie();
        Intent intent = getIntent();
        if (intent.hasExtra(Consts.item_clicked_id)) {
            String id = intent.getStringExtra(Consts.item_clicked_id);
            setMovieDetailsViewModel(id);
            setCastViewModel(id);
            setSimilarMoviesViewModel(id);
            if (movie.getSimilarMovies() == null)
                setSimilarShowsViewModel(id);
        }

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

    private static void loadImage(ImageView view, String imageUrl) {
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.rounded_bg)
                .into(view);
    }
    private void setDetailViewUI(){
        ImageView backdrop = findViewById(R.id.backdropImg);
        ImageView posterImg = findViewById(R.id.posterImg);
        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView category = findViewById(R.id.category);
        TextView tagline = findViewById(R.id.tagline);
        TextView overview = findViewById(R.id.overview);

        loadImage(backdrop, Consts.base_backdrop_url + movie.getMovieDetails().getBackdropUrl());
        loadImage(posterImg, Consts.base_poster_url + movie.getMovieDetails().getPoster());
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

    }

}
