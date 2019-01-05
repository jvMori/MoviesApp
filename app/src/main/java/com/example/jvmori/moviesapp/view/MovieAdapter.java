package com.example.jvmori.moviesapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.favMovies.FavMovie;

import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private List<FavMovie> favMovies = new ArrayList<>();

    class MovieHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private ImageView icon;

        public MovieHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            icon = itemView.findViewById(R.id.icon);
        }
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new MovieHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        holder.title.setText(favMovies.get(position).getTitle());
        holder.icon.setClipToOutline(true);
    }

    @Override
    public int getItemCount() {
        return favMovies.size();
    }

    public void setFavMovies(List<FavMovie> favMovies){
        this.favMovies = favMovies;
        notifyDataSetChanged();
    }
}
