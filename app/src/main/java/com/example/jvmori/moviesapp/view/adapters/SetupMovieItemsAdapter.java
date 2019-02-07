package com.example.jvmori.moviesapp.view.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.library.collection.favMovie.FavMovie;
import com.example.jvmori.moviesapp.model.genre.Genre;
import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.util.Consts;
import com.example.jvmori.moviesapp.view.activities.DetailsActivity;
import com.example.jvmori.moviesapp.view.fragments.HomeFragmentDirections;
import com.example.jvmori.moviesapp.viewModel.FavMovieViewModel;
import com.example.jvmori.moviesapp.viewModel.GenreViewModel;
import java.util.List;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SetupMovieItemsAdapter
{
    private MovieItemAdapter movieItemAdapter;
    private Activity activity;
    private Fragment fragment;
    private LifecycleOwner owner;
    private FavMovieViewModel favMovieViewModel;

    public SetupMovieItemsAdapter(Activity activity, Fragment fragment, LifecycleOwner owner) {
        this.activity = activity;
        this.fragment = fragment;
        this.owner = owner;
        favMovieViewModel = ViewModelProviders.of(fragment).get(FavMovieViewModel.class);
    }

    public void setMovieItemAdapter(RecyclerView recyclerView, String mediaType, final SetViewCallback setViewCallback){
        setPopularMovieAdapter(recyclerView, mediaType);
        setGenreViewModel(mediaType, setViewCallback);
    }

    private void setPopularMovieAdapter(RecyclerView recyclerView, final String mediaType){
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setHasFixedSize(true);
        movieItemAdapter = new MovieItemAdapter();
        recyclerView.setAdapter(movieItemAdapter);
        favMovieViewModel.getAllMovies().observe(owner, new Observer<List<FavMovie>>() {
            @Override
            public void onChanged(List<FavMovie> favMovies) {
                movieItemAdapter.setFavMovies(favMovies);
            }
        });

        movieItemAdapter.setOnItemClickedListener(new MovieItemAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(MovieItem movieItem) {
                Intent intent = new Intent(activity, DetailsActivity.class);
                intent.putExtra(Consts.id_parameter, movieItem.getTmdbId());
                String type = movieItem.getMediaType() != null ? movieItem.getMediaType() : mediaType;
                intent.putExtra(Consts.type_parameter, type);
                activity.startActivity(intent);
            }
        });

        movieItemAdapter.setOnLikeClickedListener(new MovieItemAdapter.OnLikeClickedListener() {
            @Override
            public void addCallback(MovieItem movieItem) {
                addFavMovie(movieItem, mediaType);
            }

            @Override
            public void removeCallback(MovieItem movieItem) {
                favMovieViewModel.deleteById(movieItem.getTmdbId());
            }
        });

        movieItemAdapter.setOnAddClickedListener(new MovieItemAdapter.OnAddClickedListener() {
            @Override
            public void callback(String movieId) {
                HomeFragmentDirections.AddToLibraryAction action = HomeFragmentDirections.addToLibraryAction();
                action.setMovieId(movieId);
                Navigation.findNavController(activity, R.id.my_nav_host_fragment).navigate(action);

            }
        });
    }

    private void setGenreViewModel(final String mediaType, final SetViewCallback setViewCallback){
        GenreViewModel genreViewModel = ViewModelProviders.of(fragment).get(GenreViewModel.class);
        genreViewModel.getData(mediaType).observe(owner, new Observer<List<Genre>>() {
            @Override
            public void onChanged(List<Genre> genres) {
                if (genres == null)
                    return;
                movieItemAdapter.setGenres(genres);
                if (setViewCallback != null)
                    setViewCallback.callback();
            }
        });
    }

    public interface SetViewCallback{
        void callback();
    }

    private void addFavMovie(MovieItem movieItem, String mediaType) {
        if (mediaType != null)
            movieItem.setMediaType(mediaType);
        favMovieViewModel.insert(new FavMovie(mediaType, movieItem.getTmdbId(), movieItem, "Favorite"));
    }


    public MovieItemAdapter getMovieItemAdapter() {
        return movieItemAdapter;
    }

}
