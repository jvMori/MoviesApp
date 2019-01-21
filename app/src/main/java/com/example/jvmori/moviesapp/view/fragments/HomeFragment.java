package com.example.jvmori.moviesapp.view.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.genre.Genre;
import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.util.Consts;
import com.example.jvmori.moviesapp.view.activities.DetailsActivity;
import com.example.jvmori.moviesapp.view.adapters.PopularItemsAdapter;
import com.example.jvmori.moviesapp.view.adapters.PopularMovieAdapter;
import com.example.jvmori.moviesapp.viewModel.GenreViewModel;
import com.example.jvmori.moviesapp.viewModel.SearchResultsViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private View view;
    private PopularItemsAdapter popularItemsAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private SearchView searchView;
    private PopularMovieAdapter popularMovieAdapter;
    private RecyclerView recyclerView;
    private LinearLayout popularItemsLayout;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = view.findViewById(R.id.popularItemViewPager);
        tabLayout = view.findViewById(R.id.tabLayout);
        searchView = view.findViewById(R.id.searchView);
        recyclerView = view.findViewById(R.id.searchRecyclerView);
        popularItemsLayout = view.findViewById(R.id.popularLayout);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        popularItemsAdapter = new PopularItemsAdapter(this.getChildFragmentManager());
        viewPager.setAdapter(popularItemsAdapter);
        tabLayout.setupWithViewPager(viewPager);
        setGenreViewModel();
        setPopularMovieAdapter();

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    //Toast.makeText(getContext(), "Focused", Toast.LENGTH_SHORT).show();
                    popularItemsLayout.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null && query.length() > 1){
                    searchTitles(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText!= null && newText.length() > 1){
                    searchTitles(newText);
                }
                return true;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                popularItemsLayout.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                return false;
            }
        });

    }

    private void searchTitles(String query){
        if (query != null)
            createSearchViewModel(query);
    }

    private void createSearchViewModel(String query){
        SearchResultsViewModel viewModel = ViewModelProviders.of(this).get(SearchResultsViewModel.class);
        viewModel.getResults(query).observe(this, new Observer<List<MovieItem>>() {
            @Override
            public void onChanged(List<MovieItem> movieItems) {
                popularMovieAdapter.setMovieItems(movieItems);
            }
        });
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

    private void setPopularMovieAdapter(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        popularMovieAdapter = new PopularMovieAdapter();
        recyclerView.setAdapter(popularMovieAdapter);
        popularMovieAdapter.setOnItemClickedListener(new PopularMovieAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(MovieItem movieItem) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra(Consts.id_parameter, movieItem.getTmdbId());
                intent.putExtra(Consts.type_parameter, Consts.movie);
                startActivity(intent);
            }
        });
    }
}
