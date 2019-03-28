package com.example.jvmori.moviesapp.view.adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.db.entities.LibraryItem;
import com.example.jvmori.moviesapp.model.network.response.Genre;
import com.example.jvmori.moviesapp.model.network.response.MovieItem;
import com.example.jvmori.moviesapp.util.Consts;
import com.example.jvmori.moviesapp.view.activities.DetailsActivity;
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
        setPopularMovieAdapter(recyclerView);
        setGenreViewModel(mediaType, setViewCallback);
    }

    private void setPopularMovieAdapter(RecyclerView recyclerView){
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setHasFixedSize(true);
        movieItemAdapter = new MovieItemAdapter();
        recyclerView.setAdapter(movieItemAdapter);
        favMovieViewModel.getAllMovies().observe(owner,result ->
                movieItemAdapter.setFavMovies(result)
        );

        movieItemAdapter.setOnItemClickedListener(movieItem -> {
            Intent intent = new Intent(activity, DetailsActivity.class);
            intent.putExtra(Consts.id_parameter, movieItem.getTmdbId());
            intent.putExtra(Consts.type_parameter,  movieItem.getMediaType());
            activity.startActivity(intent);
        });

        movieItemAdapter.setOnLikeClickedListener(new MovieItemAdapter.OnLikeClickedListener() {
            @Override
            public void addCallback(MovieItem movieItem) {
                addFavMovie(movieItem);
            }

            @Override
            public void removeCallback(MovieItem movieItem) {
                favMovieViewModel.deleteById(movieItem.getTmdbId());
            }
        });

        movieItemAdapter.setOnAddClickedListener(movieItem -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("movieItem", movieItem);
            Navigation.findNavController(activity, R.id.my_nav_host_fragment).navigate(R.id.addToLibraryAction, bundle);
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

    private void addFavMovie(MovieItem movieItem) {
        favMovieViewModel.setCollectionToMovieItem(new LibraryItem("Favorite"), movieItem);
        favMovieViewModel.insert(movieItem);
    }

    public MovieItemAdapter getMovieItemAdapter() {
        return movieItemAdapter;
    }

}
