package com.example.jvmori.moviesapp.view.fragments.saved;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.library.collection.favMovie.FavMovie;
import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.view.adapters.MovieItemAdapter;
import com.example.jvmori.moviesapp.view.adapters.SetupMovieItemsAdapter;
import com.example.jvmori.moviesapp.viewModel.FavMovieViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class SavedItemsFragment extends Fragment {

    private String collectionName;
    private List<MovieItem> movieItems;
    private MovieItemAdapter movieItemAdapter;
    private View view;
    private RecyclerView recyclerView;
    private RelativeLayout loadingScreen;


    public String getCollectionName() {
        return collectionName;
    }

    public SavedItemsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_popular_item, container, false);
        recyclerView = view.findViewById(R.id.movieRecyclerView);
        loadingScreen = view.findViewById(R.id.loadingPanel);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SetupMovieItemsAdapter setupMovieItemsAdapter = new SetupMovieItemsAdapter(this.getActivity(), this, this);
        setupMovieItemsAdapter.setMovieItemAdapter(recyclerView, null, new SetupMovieItemsAdapter.SetViewCallback() {
            @Override
            public void callback() {
                setFavMovieViewModel();
            }
        });

        movieItemAdapter = setupMovieItemsAdapter.getMovieItemAdapter();

    }

    private void setFavMovieViewModel(){
        FavMovieViewModel favMovieViewModel = ViewModelProviders.of(this).get(FavMovieViewModel.class);
        favMovieViewModel.getMovieFromCollection(collectionName).observe(this, new Observer<List<FavMovie>>() {
            @Override
            public void onChanged(List<FavMovie> favMovies) {
                setMovieItems(favMovies);
                movieItemAdapter.setMovieItems(movieItems);
                loadingScreen.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });

        favMovieViewModel.getAllItemsOfType("tv").observe(this, new Observer<List<FavMovie>>() {
            @Override
            public void onChanged(List<FavMovie> favMovies) {
                List<FavMovie> tvShows = favMovies;
            }
        });
    }

    private void setMovieItems(List<FavMovie> favMovies){
        movieItems = new ArrayList<>();
        for (FavMovie fav: favMovies) {
            movieItems.add(fav.getMovie());
        }
    }

    public void setCollectionName(String collectionName){this.collectionName = collectionName;}

}
