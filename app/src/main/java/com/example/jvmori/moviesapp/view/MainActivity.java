package com.example.jvmori.moviesapp.view;

import android.app.Application;
import android.os.Bundle;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.favMovies.Movie;
import com.example.jvmori.moviesapp.model.popularMovies.PopularItem;
import com.example.jvmori.moviesapp.repository.PopularMoviesRepository;
import com.example.jvmori.moviesapp.viewModel.MovieViewModel;
import com.example.jvmori.moviesapp.viewModel.PopularMoviesViewModel;
import com.google.gson.Gson;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private PopularMoviesViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.movieRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //final MovieAdapter movieAdapter = new MovieAdapter();
        final PopularMovieAdapter popularMovieAdapter = new PopularMovieAdapter();
        recyclerView.setAdapter(popularMovieAdapter);

        movieViewModel = ViewModelProviders.of(this).get(PopularMoviesViewModel.class);
        movieViewModel.getAllPopularMovies().observe(this, new Observer<List<PopularItem>>() {
            @Override
            public void onChanged(List<PopularItem> movies) {
                if (movies == null)
                    return;
                popularMovieAdapter.setPopularMovies(movies);
            }
        });


        //PopularMoviesRepository.getInstance().getData();
    }
}
