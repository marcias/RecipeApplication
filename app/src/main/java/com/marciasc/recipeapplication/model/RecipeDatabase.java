package com.marciasc.recipeapplication.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.marciasc.recipeapplication.util.Converter;

@Database(entities = {Recipe.class}, version = 1, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class RecipeDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "recipe_database";

    public abstract RecipeDao recipeDao();

    private static RecipeDatabase mInstance;
    private static RecipeDatabase.Callback sCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    public static RecipeDatabase getDatabase(Context context) {
        if (mInstance == null) {
            synchronized (RecipeDatabase.class) {
                if (mInstance == null) {
                    mInstance = Room.databaseBuilder(context.getApplicationContext(), RecipeDatabase.class, DATABASE_NAME).
                            fallbackToDestructiveMigration().
                            addCallback(sCallback).
                            build();
                }
            }
        }
        return mInstance;
    }
}
