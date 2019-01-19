package com.example.jvmori.moviesapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.jvmori.moviesapp.view.adapters.PopularMovieAdapter;
import com.example.jvmori.moviesapp.view.adapters.SimilarAdapter;
import com.example.jvmori.moviesapp.viewModel.CastViewModel;
import com.example.jvmori.moviesapp.viewModel.DetailsViewModel;
import com.example.jvmori.moviesapp.viewModel.SimilarViewModel;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    private Movie movie;
    private CastAdapter castAdapter;
    private SimilarAdapter similarAdapter;
    private ScrollView detailsScrollView;
    private LinearLayout starsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_movie_details);
        detailsScrollView = findViewById(R.id.detailsScrollView);
        starsLayout = findViewById(R.id.starsLayout);

        String id = getIntent().getStringExtra(Consts.id_parameter);
        String type = getIntent().getStringExtra(Consts.type_parameter);
        createView(type, id);
    }


    private void createView(final String type, String id){
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

    private void setMovieDetailsViewModel(String type, String movieId){
        DetailsViewModel movieDetailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        movieDetailsViewModel.getMovieDetails(type, movieId).observe(this, new Observer<MovieDetails>() {
            @Override
            public void onChanged(MovieDetails movieDetails) {
                movie.setMovieDetails(movieDetails);
                setDetailViewUI(movie);
                //TODO: loading screen disable
            }
        });
    }

    private void setCastViewModel(String type, String movieId){
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

    private void setSimilarMoviesViewModel(String type, String movieId){
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

    private void setCastAdapter(){
        RecyclerView recyclerView = findViewById(R.id.creditsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        castAdapter = new CastAdapter();
        recyclerView.setAdapter(castAdapter);
    }

    private void setSimilarAdapter(){
        RecyclerView recyclerView = findViewById(R.id.similarRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        similarAdapter = new SimilarAdapter();
        recyclerView.setAdapter(similarAdapter);
    }


    private void setDetailViewUI(Movie movie){
        ImageView backdrop = findViewById(R.id.backdropImg);
        ImageView posterImg = findViewById(R.id.posterImg);
        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView category = findViewById(R.id.category);
        TextView overview = findViewById(R.id.overview);

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
        overview.setText(movie.getMovieDetails().getOverview());
        float rating = Float.parseFloat(movie.getMovieDetails().getRating()) * 10;
        PopularMovieAdapter.setStars(rating, starsLayout, this, Consts.number_of_stars);
    }
}