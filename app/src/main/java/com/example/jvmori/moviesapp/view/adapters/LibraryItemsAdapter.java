package com.example.jvmori.moviesapp.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.library.collection.item.LibraryItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LibraryItemsAdapter extends RecyclerView.Adapter<LibraryItemsAdapter.LibraryViewHolder>
{
    private List<LibraryItem> libraryItems =  new ArrayList<>();
    View view;

    @NonNull
    @Override
    public LibraryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new LibraryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibraryViewHolder holder, int position) {
        LibraryItem currentItem = libraryItems.get(position);
        holder.name.setText(currentItem.getNameOfCollection());
    }

    @Override
    public int getItemCount() {
        return libraryItems.size();
    }

    public class LibraryViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public LibraryViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.itemName);
        }
    }

    public void setLibraryItems(List<LibraryItem> libraryItems){
        this.libraryItems = libraryItems;
        notifyDataSetChanged();
    }

}
