package com.example.jvmori.moviesapp.view;
import android.os.Bundle;
import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.genre.Genre;
import com.example.jvmori.moviesapp.model.popularMovies.PopularItem;
import com.example.jvmori.moviesapp.viewModel.GenreViewModel;
import com.example.jvmori.moviesapp.viewModel.PopularMoviesViewModel;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private PopularMovieAdapter popularMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setPopularMovieAdapter();
        setGenreViewModel();
        setPopularMovieViewModel();
    }

    private void setPopularMovieAdapter(){
        final RecyclerView recyclerView = findViewById(R.id.movieRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        movieViewModel.getAllPopularMovies().observe(this, new Observer<List<PopularItem>>() {
            @Override
            public void onChanged(List<PopularItem> movies) {
                if (movies == null)
                    return;
                popularMovieAdapter.setPopularMovies(movies);
            }
        });
    }
}
