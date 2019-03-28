package com.example.jvmori.moviesapp.view.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.db.entities.LibraryItem;
import com.example.jvmori.moviesapp.model.db.entities.MovieItem;
import com.example.jvmori.moviesapp.view.adapters.LibraryItemsAdapter;
import com.example.jvmori.moviesapp.viewModel.FavMovieViewModel;
import com.example.jvmori.moviesapp.viewModel.LibraryViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddToLibraryFragment extends Fragment implements LibraryItemsAdapter.IOnClickListener {

    private RecyclerView recyclerView;
    private LibraryItemsAdapter adapter;
    private View view;
    private MovieItem movieItem;
    private LibraryItemsAdapter.IOnClickListener iOnClickListener;
    private FavMovieViewModel favMovieViewModel;
    private Context context;

    public AddToLibraryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_to_library, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = view.getContext();
        setRecyclerView();
        createView();
        iOnClickListener = this;
        favMovieViewModel = ViewModelProviders.of(this).get(FavMovieViewModel.class);

        assert getArguments() != null;
        movieItem = getArguments().getParcelable("movieItem");
    }

    private void createView(){
        LibraryViewModel libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);
        libraryViewModel.getAllItems().observe(this, new Observer<List<LibraryItem>>() {
            @Override
            public void onChanged(List<LibraryItem> libraryItems) {
                adapter.setLibraryItems(libraryItems);
                adapter.setiOnClickListener(iOnClickListener);
            }
        });
    }

    private void setRecyclerView(){
        recyclerView = view.findViewById(R.id.libraryRecyclerView);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 2, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new LibraryItemsAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLibraryItemClicked(LibraryItem libraryItem) {
        favMovieViewModel.setCollectionToMovieItem(libraryItem, movieItem);
        favMovieViewModel.insert(movieItem);
        Toast.makeText(context, "Added to collection", Toast.LENGTH_SHORT).show();
    }
}
