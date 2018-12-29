package com.example.jvmori.moviesapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.popularMovies.PopularItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PopularMovieAdapter extends RecyclerView.Adapter<PopularMovieAdapter.PopularMovieHolder>
{
    private List<PopularItem> popularMovies = new ArrayList<>();

    public class PopularMovieHolder extends RecyclerView.ViewHolder {
        TextView title, year, rating;

        public PopularMovieHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            year = itemView.findViewById(R.id.year);
            rating = itemView.findViewById(R.id.rating);
        }
    }

    @NonNull
    @Override
    public PopularMovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new PopularMovieHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularMovieHolder holder, int position) {
        holder.title.setText(popularMovies.get(position).getTitle());
        holder.year.setText(popularMovies.get(position).getYear());
        holder.rating.setText(popularMovies.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return popularMovies.size();
    }

    public void setPopularMovies(List<PopularItem> movies){
        popularMovies = movies;
        notifyDataSetChanged();
    }

}
