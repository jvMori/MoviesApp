package com.example.jvmori.moviesapp.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.network.movieDetails.Cast;
import com.example.jvmori.moviesapp.util.Consts;
import com.example.jvmori.moviesapp.util.LoadImage;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {
    private List<Cast> castList = new ArrayList<>();

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new CastViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {
        Cast currentCastItem = castList.get(position);
        holder.avatar.setClipToOutline(true);
        LoadImage.loadImage(holder.avatar, Consts.base_poster_url+ currentCastItem.getPosterUrl());
        holder.name.setText(currentCastItem.getName());
    }

    @Override
    public int getItemCount() {
        return castList.size() >= Consts.cast_recyclerView_size
                ? Consts.cast_recyclerView_size
                : castList.size();
    }

    public class CastViewHolder extends RecyclerView.ViewHolder{
        ImageView avatar;
        TextView name;

        public CastViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.icon);
            name = itemView.findViewById(R.id.title);
        }
    }

    public void setCastItems(List<Cast> casts){
        castList = casts;
        notifyDataSetChanged();
    }

}
