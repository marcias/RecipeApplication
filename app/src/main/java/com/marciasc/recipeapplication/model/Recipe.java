package com.marciasc.recipeapplication.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "recipe_table")
public class Recipe {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;
    @ColumnInfo(name = "title")
    private String mTitle;
    @ColumnInfo(name = "description")
    private String mDescription;
    @ColumnInfo(name = "imagesPath")
    private List<String> mImagesPath;

    public Recipe(String title, String description, List<String> imagesPath) {
        mTitle = title;
        mDescription = description;
        mImagesPath = imagesPath;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public List<String> getImagesPath() {
        return mImagesPath;
    }

    public void setImagesPath(ArrayList<String> imagesPath) {
        this.mImagesPath = imagesPath;
    }
}
