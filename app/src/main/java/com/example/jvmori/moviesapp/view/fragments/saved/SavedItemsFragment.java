package com.example.jvmori.moviesapp.view.fragments.saved;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.view.adapters.MovieItemAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedItemsFragment extends Fragment {

    protected MovieItemAdapter movieItemAdapter;
    private View view;

    public SavedItemsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_saved_items, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setMovieItemAdapter();
    }

    protected void setMovieItemAdapter(){
        RecyclerView recyclerView = view.findViewById(R.id.savedRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        movieItemAdapter = new MovieItemAdapter();
        recyclerView.setAdapter(movieItemAdapter);
    }

}
