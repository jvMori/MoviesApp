package com.example.jvmori.moviesapp.model.network.response;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.jvmori.moviesapp.model.db.entities.LibraryItem;
import com.example.jvmori.moviesapp.util.ListStringTypeConverter;
import com.google.gson.annotations.SerializedName;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "movie_table")
public class MovieItem implements Parcelable
{
    @SerializedName("id")
    @PrimaryKey
    @NonNull
    private String tmdbId = "0";

    @Embedded
    private LibraryItem libraryItem;

    @SerializedName("media_type")
    private String mediaType;

    @SerializedName(value="title", alternate = "name")
    private String title;

    @SerializedName(("poster_path"))
    private String poster;

    @SerializedName(value = "release_date", alternate = "first_air_date")
    private String year;

    @SerializedName(("vote_average"))
    private int rating;

    @SerializedName(("genre_ids"))
    @TypeConverters(ListStringTypeConverter.class)
    private List<String> categories;

    @SerializedName(("vote_count"))
    private String reviews;

    public MovieItem(){

    }

    protected MovieItem(Parcel in) {
        tmdbId = in.readString();
        mediaType = in.readString();
        title = in.readString();
        poster = in.readString();
        year = in.readString();
        rating = in.readInt();
        categories = in.createStringArrayList();
        reviews = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tmdbId);
        dest.writeString(mediaType);
        dest.writeString(title);
        dest.writeString(poster);
        dest.writeString(year);
        dest.writeInt(rating);
        dest.writeStringList(categories);
        dest.writeString(reviews);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MovieItem> CREATOR = new Creator<MovieItem>() {
        @Override
        public MovieItem createFromParcel(Parcel in) {
            return new MovieItem(in);
        }

        @Override
        public MovieItem[] newArray(int size) {
            return new MovieItem[size];
        }
    };

    public String getTmdbId() {
        return tmdbId;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getYear() {
        return year;
    }

    public int getRating() {
        return rating * 10;
    }

    public List<String> getCategories() {
        return categories;
    }

    public String getReviews() {
        return reviews;
    }

    public void setTmdbId(String tmdbId) {
        this.tmdbId = tmdbId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public LibraryItem getLibraryItem() {
        return libraryItem;
    }

    public void setLibraryItem(LibraryItem libraryItem) {
        this.libraryItem = libraryItem;
    }
}
