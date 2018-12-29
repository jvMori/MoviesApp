package com.example.jvmori.moviesapp.view;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.genre.Genre;
import com.example.jvmori.moviesapp.model.popularMovies.PopularItem;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PopularMovieAdapter extends RecyclerView.Adapter<PopularMovieAdapter.PopularMovieHolder>
{
    private List<PopularItem> popularMovies = new ArrayList<>();
    private List<Genre> genres = new ArrayList<>();


    public class PopularMovieHolder extends RecyclerView.ViewHolder {
        TextView title, year, rating, reviews, categories;

        public PopularMovieHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            year = itemView.findViewById(R.id.year);
            rating = itemView.findViewById(R.id.rating);
            reviews = itemView.findViewById(R.id.review);
            categories = itemView.findViewById(R.id.category);
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
        PopularItem currentItem = popularMovies.get(position);
        String reviews = "Reviews: " + currentItem.getReviews();
        StringBuilder categories = new StringBuilder();
        categoryTxtSetup(currentItem, categories);

        holder.title.setText(currentItem.getTitle());
        holder.year.setText(currentItem.getYear());
        holder.rating.setText(currentItem.getRating());
        holder.reviews.setText(reviews);
        holder.categories.setText(categories.toString());

    }

    @Override
    public int getItemCount() {
        return popularMovies.size();
    }

    public void setGenres(List<Genre> genres){
        this.genres = genres;
    }

    public void setPopularMovies(List<PopularItem> movies){
        popularMovies = movies;
        notifyDataSetChanged();
    }

    private void categoryTxtSetup(PopularItem currentItem, StringBuilder categories){
        List<String> txtGenres = new ArrayList<>();
        for (Genre genre: genres) {
            for (int i = 0; i < currentItem.getCategories().size(); i++) {
                if (currentItem.getCategories().get(i).equals(genre.getId()))
                    txtGenres.add(genre.getName());
            }
        }
        for (String txtGenre: txtGenres) {
            categories.append(txtGenre);
            if (!txtGenre.equals(txtGenres.get(txtGenres.size()-1)))
                categories.append(" | ");
        }
    }

}
