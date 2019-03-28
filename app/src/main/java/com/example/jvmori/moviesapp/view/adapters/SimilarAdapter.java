package com.example.jvmori.moviesapp.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.db.entities.MovieItem;
import com.example.jvmori.moviesapp.util.Consts;
import com.example.jvmori.moviesapp.util.LoadImage;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SimilarAdapter extends RecyclerView.Adapter<SimilarAdapter.SimilarHolder>
{
    private List<MovieItem> similarItems = new ArrayList<>();
    private OnSimilarItemClicked onSimilarItemClicked;

    @NonNull
    @Override
    public SimilarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new SimilarHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarHolder holder, int position) {
        String url = similarItems.get(position).getPoster();
        holder.poster.setClipToOutline(true);
        LoadImage.loadImage(holder.poster, Consts.base_poster_url + url);
        holder.title.setText(similarItems.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return similarItems.size();
    }

    public class SimilarHolder extends RecyclerView.ViewHolder{

        ImageView poster;
        TextView title;
        public SimilarHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (onSimilarItemClicked != null && position != RecyclerView.NO_POSITION)
                        onSimilarItemClicked.onClick(similarItems.get(position));
                }
            });
        }
    }

    public void setSimilarItems(List<MovieItem> items){
        similarItems = items;
        notifyDataSetChanged();
    }

    public interface OnSimilarItemClicked{
        void onClick(MovieItem item);
    }

    public void setOnSimilarItemClicked(OnSimilarItemClicked onSimilarItemClicked){
        this.onSimilarItemClicked = onSimilarItemClicked;
    }
}
