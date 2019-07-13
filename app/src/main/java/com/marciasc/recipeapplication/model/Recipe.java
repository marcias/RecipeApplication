package com.marciasc.recipeapplication.model;

import android.net.Uri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.util.List;

@Entity(tableName = "recipe_table")
public class Recipe {
    @ColumnInfo(name = "title")
    private String mTitle;
    @ColumnInfo(name = "description")
    private String mDescription;
    @ColumnInfo(name = "images")
    private List<Uri> mImagesPath;

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public List<Uri> getmImagesPath() {
        return mImagesPath;
    }

    public void setmImagesPath(List<Uri> imagesPath) {
        this.mImagesPath = imagesPath;
    }
}
