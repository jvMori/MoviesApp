package com.example.jvmori.moviesapp.model.db.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "libraryItems")
public class LibraryItem
{
    @PrimaryKey(autoGenerate = true)
    private int itemId;

    @ColumnInfo(name = "name_of_item")
    private String nameOfCollection;

    public LibraryItem(String nameOfCollection) {
        this.nameOfCollection = nameOfCollection;
    }


    public String getNameOfCollection() {
        return nameOfCollection;
    }

    public void setNameOfCollection(String nameOfCollection) {
        this.nameOfCollection = nameOfCollection;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
