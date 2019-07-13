package com.marciasc.recipeapplication.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.marciasc.recipeapplication.Converters;

@Database(entities = {Recipe.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class RecipeDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "recipe_database";

    public abstract RecipeDao recipeDao();

    private static RecipeDatabase mInstance;
    private static RecipeDatabase.Callback sCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(mInstance).execute();
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

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private RecipeDao mRecipeDao;

        PopulateDbAsync(RecipeDatabase db) {
            mRecipeDao = db.recipeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            mRecipeDao.deleteAll();
//            for (int i = 0; i <= words.length - 1; i++) {
//                Recipe recipe = new Recipe();
//                mRecipeDao.insert(recipe);
//            }
            return null;
        }
    }
}
