package com.example.jvmori.moviesapp.view;

import android.app.Application;
import android.os.Bundle;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.favMovies.Movie;
import com.example.jvmori.moviesapp.repository.PopularMoviesRepository;
import com.example.jvmori.moviesapp.viewModel.MovieViewModel;
import com.google.gson.Gson;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.movieRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final MovieAdapter movieAdapter = new MovieAdapter();
        recyclerView.setAdapter(movieAdapter);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                movieAdapter.setMovies(movies);
            }
        });


        PopularMoviesRepository.getInstance().getData();
    }
}
