package com.example.jvmori.moviesapp.view;

import android.os.Bundle;
import android.widget.Toast;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.Movie;
import com.example.jvmori.moviesapp.viewModel.MovieViewModel;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {
    private MovieViewModel movieViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                //update recycler view
                Toast.makeText(MainActivity.this, "changed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
