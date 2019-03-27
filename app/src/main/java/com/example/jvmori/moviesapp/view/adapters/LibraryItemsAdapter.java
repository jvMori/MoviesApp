package com.example.jvmori.moviesapp.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jvmori.moviesapp.R;
import com.example.jvmori.moviesapp.model.db.entities.LibraryItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LibraryItemsAdapter extends RecyclerView.Adapter<LibraryItemsAdapter.LibraryViewHolder> {
    private List<LibraryItem> libraryItems = new ArrayList<>();
    private IOnClickListener iOnClickListener;
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
        holder.bind(currentItem);
    }

    @Override
    public int getItemCount() {
        return libraryItems.size();
    }

    public void setiOnClickListener(IOnClickListener iOnClickListener) {
        this.iOnClickListener = iOnClickListener;
    }

    public class LibraryViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        LibraryViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.itemName);
        }
        void bind(LibraryItem currentItem){
            name.setText(currentItem.getNameOfCollection());
            itemView.setOnClickListener(v -> {
                if (iOnClickListener != null)
                    iOnClickListener.onLibraryItemClicked(currentItem);
            });
        }
    }

    public void setLibraryItems(List<LibraryItem> libraryItems) {
        this.libraryItems = libraryItems;
        notifyDataSetChanged();
    }


    public interface IOnClickListener {
        void onLibraryItemClicked(LibraryItem libraryItem);
    }
}
