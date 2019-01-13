package com.example.jvmori.moviesapp.view.adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.genre.Genre;
import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.util.Consts;
import com.example.jvmori.moviesapp.util.LoadImage;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PopularMovieAdapter extends RecyclerView.Adapter<PopularMovieAdapter.PopularMovieHolder>
{
    private List<MovieItem> movieItems = new ArrayList<>();
    private List<Genre> genres = new ArrayList<>();
    private OnItemClickedListener onItemClickedListener;
    View item;

    public class PopularMovieHolder extends RecyclerView.ViewHolder {
        TextView title, year, rating, reviews, categories;
        ImageView poster;
        LinearLayout starsLayout;

        public PopularMovieHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            year = itemView.findViewById(R.id.year);
            rating = itemView.findViewById(R.id.rating);
            reviews = itemView.findViewById(R.id.review);
            categories = itemView.findViewById(R.id.category);
            poster = itemView.findViewById(R.id.icon);
            starsLayout = itemView.findViewById(R.id.layoutStars);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (onItemClickedListener != null && position != RecyclerView.NO_POSITION)
                        onItemClickedListener.onItemClicked(movieItems.get(position));
                }
            });
        }

    }

    public interface OnItemClickedListener{
        void onItemClicked(MovieItem movieItem);
    }

    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener){
        this.onItemClickedListener = onItemClickedListener;
    }


    @NonNull
    @Override
    public PopularMovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        item = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new PopularMovieHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularMovieHolder holder, int position) {
        MovieItem currentItem = movieItems.get(position);
        String reviews = "Reviews: " + currentItem.getReviews();
        StringBuilder categories = new StringBuilder();
        categoryTxtSetup(currentItem, categories);

        holder.title.setText(currentItem.getTitle());
        holder.year.setText(currentItem.getYear());
        holder.rating.setText(currentItem.getRating());
        holder.reviews.setText(reviews);
        holder.categories.setText(categories.toString());
        holder.poster.setClipToOutline(true);
        LoadImage.loadImage(holder.poster, Consts.base_poster_url + currentItem.getPoster());
        setupStars(holder, currentItem);
    }

    @Override
    public int getItemCount() {
        return movieItems.size();
    }

    public void setGenres(List<Genre> genres){
        this.genres = genres;
    }

    public void setMovieItems(List<MovieItem> movies){
        movieItems = movies;
        notifyDataSetChanged();
    }

    private void categoryTxtSetup(MovieItem currentItem, StringBuilder categories){
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

    private void setupStars(PopularMovieHolder holder, MovieItem currentItem) {
        float rating = Float.parseFloat(currentItem.getRating()) / 2;
        double starsCount = Math.floor(rating);
        double halfStar = rating - starsCount;
        if (holder.starsLayout.getChildCount() <= 0 ){
            for (int i = 0; i < starsCount; i++) {
                ImageView imageView = new ImageView(item.getContext());
                imageView.setImageResource(R.drawable.ic_star);
                setParamsAndAddToView(imageView, holder);
            }
            if (halfStar >= 0.5){
                ImageView imageView = new ImageView(item.getContext());
                imageView.setImageResource(R.drawable.ic_star_half);
                setParamsAndAddToView(imageView, holder);
                starsCount++;
            }
            if (starsCount < Consts.number_of_stars){
                double diff = Consts.number_of_stars - starsCount;
                for (int i = 0; i < diff; i++) {
                    ImageView imageView = new ImageView(item.getContext());
                    imageView.setImageResource(R.drawable.ic_star_border);
                    setParamsAndAddToView(imageView, holder);
                }
            }
        }
    }

    private void setParamsAndAddToView(ImageView imageView, PopularMovieHolder holder){
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(70, 70);
        lp.setMarginEnd(3);
        imageView.setLayoutParams(lp);
        holder.starsLayout.addView(imageView);
    }

}
