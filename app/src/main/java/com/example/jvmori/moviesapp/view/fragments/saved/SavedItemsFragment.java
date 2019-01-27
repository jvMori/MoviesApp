package com.example.jvmori.moviesapp.view.fragments.saved;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.genre.Genre;
import com.example.jvmori.moviesapp.view.adapters.MovieItemAdapter;
import com.example.jvmori.moviesapp.viewModel.GenreViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedItemsFragment extends Fragment {

    protected MovieItemAdapter movieItemAdapter;
    private View view;
    protected RecyclerView recyclerView;
    protected RelativeLayout loadingScreen;

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

}
