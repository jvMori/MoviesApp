package com.example.jvmori.moviesapp.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.favMovies.FavMovie;
import com.example.jvmori.moviesapp.model.genre.Genre;
import com.example.jvmori.moviesapp.model.popularMovies.MovieItem;
import com.example.jvmori.moviesapp.util.Consts;
import com.example.jvmori.moviesapp.util.LoadImage;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MovieItemAdapter extends RecyclerView.Adapter<MovieItemAdapter.PopularMovieHolder> {
    private List<MovieItem> movieItems = new ArrayList<>();
    private List<Genre> genres = new ArrayList<>();
    private OnItemClickedListener onItemClickedListener;
    private OnLikeClickedListener onLikeClickedListener;
    private OnAddClickedListener onAddClickedListener;
    private ConstraintLayout layoutItem;
    private RelativeLayout likeView;
    List<FavMovie> favMovies;
    View item;

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
            layoutItem = itemView.findViewById(R.id.itemView);
            addBtn = itemView.findViewById(R.id.addBtn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (onItemClickedListener != null && position != RecyclerView.NO_POSITION)
                        onItemClickedListener.onItemClicked(movieItems.get(position));
                }
            });

            likeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (onLikeClickedListener != null && position != RecyclerView.NO_POSITION) {
                        boolean fav = checkIfIsFav(movieItems.get(position), favMovies);
                        handleLikeBtn(fav, likeBtn);
                        if (!fav)
                            onLikeClickedListener.addCallback(movieItems.get(position));
                        else
                            onLikeClickedListener.removeCallback(movieItems.get(position));
                    }
                }
            });

            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (onAddClickedListener != null && position != RecyclerView.NO_POSITION)
                        onAddClickedListener.callback(movieItems.get(position).getTmdbId());
                }
            });
        }
    }

    public interface OnAddClickedListener {
        void callback(String movieId);
    }

    public void setOnAddClickedListener(OnAddClickedListener onAddClickedListener){this.onAddClickedListener = onAddClickedListener;}

    private boolean checkIfIsFav(MovieItem movieItem, List<FavMovie> favMovies) {
        for (FavMovie fav : favMovies) {
            if (movieItem.getTmdbId().equals(fav.getMovie().getTmdbId()))
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
        float rating = 0;
        if (currentItem.getRating() != null)
            rating = Float.parseFloat(currentItem.getRating()) * 10;
        setStars(rating, holder.starsLayout);
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

    public void setFavMovies(List<FavMovie> favMovies) {
        this.favMovies = favMovies;
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

    public static void setStars(float rating, LinearLayout starsLayout) {
        int maxNumberOfStars = starsLayout.getChildCount();
        float ratingFromPercentage = rating * maxNumberOfStars / 100;
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
//            else {
//                imageView.setImageResource(R.drawable.ic_star_border);
//            }
        }
    }

    private static void setParamsAndAddToView(ImageView imageView, LinearLayout starsLayout) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(70, 70);
        lp.setMarginEnd(3);
        imageView.setLayoutParams(lp);
        starsLayout.addView(imageView);
    }

}
