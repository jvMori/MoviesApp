package com.example.jvmori.moviesapp.view.fragments.popular;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.view.adapters.MovieItemAdapter;
import com.example.jvmori.moviesapp.viewModel.PopularItemsViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment {

    protected View view;
    protected MovieItemAdapter movieItemAdapter;
    private RelativeLayout loadingScreen;
    RecyclerView recyclerView;

    public PopularFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_popular_item, container, false);
        loadingScreen = view.findViewById(R.id.loadingPanel);
        recyclerView = view.findViewById(R.id.movieRecyclerView);
        return view;
    }


    protected void setPopularMovieViewModel(String type){
        PopularItemsViewModel movieViewModel = ViewModelProviders.of(this).get(PopularItemsViewModel.class);
        movieViewModel.getAllPopularItems(type).observe(this, new Observer<List<MovieItem>>() {
            @Override
            public void onChanged(List<MovieItem> movies) {
                if (movies == null)
                    return;
                movieItemAdapter.setMovieItems(movies);
                loadingScreen.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }

}
