package com.example.jvmori.moviesapp.model.library.collection.item;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "libraryItems")
public class LibraryItem
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name_of_item")
    private String nameOfCollection;

    public LibraryItem(String nameOfCollection) {
        this.nameOfCollection = nameOfCollection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfCollection() {
        return nameOfCollection;
    }

    public void setNameOfCollection(String nameOfCollection) {
        this.nameOfCollection = nameOfCollection;
    }
}
