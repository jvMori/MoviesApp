package com.example.jvmori.moviesapp.view.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.network.response.Genre;
import com.example.jvmori.moviesapp.model.db.entities.MovieItem;
import com.example.jvmori.moviesapp.util.Consts;
import com.example.jvmori.moviesapp.util.LoadImage;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieItemAdapter extends RecyclerView.Adapter<MovieItemAdapter.PopularMovieHolder> {
    private List<MovieItem> movieItems = new ArrayList<>();
    private List<Genre> genres = new ArrayList<>();
    private OnItemClickedListener onItemClickedListener;
    private OnLikeClickedListener onLikeClickedListener;
    private OnAddClickedListener onAddClickedListener;
    private List<MovieItem> favMovies;
    private View item;

    public class PopularMovieHolder extends RecyclerView.ViewHolder {
        TextView title, year, rating, reviews, categories;
        ImageView poster, likeBtn, addBtn;
        LinearLayout starsLayout;

        public PopularMovieHolder(@NonNull final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            year = itemView.findViewById(R.id.year);
            rating = itemView.findViewById(R.id.rating);
            reviews = itemView.findViewById(R.id.review);
            categories = itemView.findViewById(R.id.category);
            poster = itemView.findViewById(R.id.icon);
            starsLayout = itemView.findViewById(R.id.layoutStars);
            likeBtn = itemView.findViewById(R.id.heart);
            addBtn = itemView.findViewById(R.id.addBtn);

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (onItemClickedListener != null && position != RecyclerView.NO_POSITION)
                    onItemClickedListener.onItemClicked(movieItems.get(position));
            });

            likeBtn.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (onLikeClickedListener != null && position != RecyclerView.NO_POSITION) {
                    boolean fav = checkIfIsFav(movieItems.get(position), favMovies);
                    handleLikeBtn(fav, likeBtn);
                    if (!fav)
                        onLikeClickedListener.addCallback(movieItems.get(position));
                    else
                        onLikeClickedListener.removeCallback(movieItems.get(position));
                }
            });

            addBtn.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (onAddClickedListener != null && position != RecyclerView.NO_POSITION)
                    onAddClickedListener.callback(movieItems.get(position));
            });
        }
    }

    @NonNull
    @Override
    public PopularMovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        item = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new PopularMovieHolder(item);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PopularMovieHolder holder, int position) {
        MovieItem currentItem = movieItems.get(position);
        String reviews = "Reviews: " + currentItem.getReviews();
        StringBuilder categories = new StringBuilder();
        categoryTxtSetup(currentItem, categories);

        holder.title.setText(currentItem.getTitle());
        holder.year.setText(currentItem.getYear());
        holder.rating.setText(Double.toString(currentItem.getRating()));
        holder.reviews.setText(reviews);
        holder.categories.setText(categories.toString());
        holder.poster.setClipToOutline(true);
        LoadImage.loadImage(holder.poster, Consts.base_poster_url + currentItem.getPoster());
        setStars(currentItem.getRating(), holder.starsLayout);
        boolean fav = checkIfIsFav(movieItems.get(position), favMovies);
        setLikeImage(fav, holder.likeBtn);
    }

    @Override
    public int getItemCount() {
        return movieItems.size();
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setMovieItems(List<MovieItem> movies) {
        movieItems = movies;
        notifyDataSetChanged();
    }

    public void setFavMovies(List<MovieItem> favMovies) {
        this.favMovies = favMovies;
    }

    private boolean checkIfIsFav(MovieItem movieItem, List<MovieItem> favMovies) {
        for (MovieItem fav : favMovies) {
            if (movieItem.getTmdbId().equals(fav.getTmdbId()) &&
                    fav.getLibraryItem().getNameOfCollection().equals(Consts.libraryItems.get(0)))
                return true;
        }
        return false;
    }

    private void handleLikeBtn(boolean liked, ImageView likeBtn) {
        liked = !liked;
        setLikeImage(liked, likeBtn);
    }

    private void setLikeImage(boolean liked, ImageView likeBtn) {
        if (liked)
            likeBtn.setImageResource(R.drawable.ic_favorite_full);
        else
            likeBtn.setImageResource(R.drawable.ic_favorite_empty);
    }

    private void categoryTxtSetup(MovieItem currentItem, StringBuilder categories) {
        List<String> txtGenres = new ArrayList<>();
        for (Genre genre : genres) {
            if (currentItem.getCategories() != null) {
                for (int i = 0; i < currentItem.getCategories().size(); i++) {
                    if (currentItem.getCategories().get(i).equals(genre.getId()))
                        txtGenres.add(genre.getName());
                }
            }
        }
        for (String txtGenre : txtGenres) {
            categories.append(txtGenre);
            if (!txtGenre.equals(txtGenres.get(txtGenres.size() - 1)))
                categories.append(" | ");
        }
    }

    public static void setStars(double rating, LinearLayout starsLayout) {
        int maxNumberOfStars = starsLayout.getChildCount();
        double ratingFromPercentage = rating * maxNumberOfStars / 100;
        double starsCount = Math.floor(ratingFromPercentage);
        double halfStar = Math.round(ratingFromPercentage);

        for (int i = 0; i < starsLayout.getChildCount(); i++) {
            ImageView imageView = (ImageView) starsLayout.getChildAt(i);
            if (i < starsCount) {
                imageView.setImageResource(R.drawable.ic_star);
            }
            if (halfStar != starsCount && i == halfStar - 1) {
                imageView.setImageResource(R.drawable.ic_star_half);
            }
        }
    }

    public interface OnAddClickedListener {
        void callback(MovieItem movieItem);
    }

    public void setOnAddClickedListener(OnAddClickedListener onAddClickedListener) {
        this.onAddClickedListener = onAddClickedListener;
    }

    public interface OnLikeClickedListener {
        void addCallback(MovieItem movieItem);

        void removeCallback(MovieItem movieItem);
    }

    public void setOnLikeClickedListener(OnLikeClickedListener onLikeClickedListener) {
        this.onLikeClickedListener = onLikeClickedListener;
    }

    public interface OnItemClickedListener {
        void onItemClicked(MovieItem movieItem);
    }

    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }
}
