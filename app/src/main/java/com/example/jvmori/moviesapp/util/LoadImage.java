package com.example.jvmori.moviesapp.util;

import android.widget.ImageView;

import com.example.jvmori.moviesapp.R;
import com.squareup.picasso.Picasso;

public class LoadImage
{
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.get()
                .load(imageUrl)
                .error(R.drawable.gradient)
                .placeholder(R.drawable.gradient)
                .into(view);
    }
}
