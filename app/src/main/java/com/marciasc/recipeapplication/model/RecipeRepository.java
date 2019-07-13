package com.marciasc.recipeapplication.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RecipeRepository {
    private RecipeDao mDao;
    private LiveData<List<Recipe>> mRecipes;

    public RecipeRepository(Application application) {
        RecipeDatabase db = RecipeDatabase.getDatabase(application.getApplicationContext());
        mDao = db.recipeDao();
        mRecipes = mDao.getAllRecipes();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipes;
    }

    public void insert(Recipe recipe) {
        new insertAsyncTask(mDao).execute(recipe);
    }

    private static class insertAsyncTask extends AsyncTask<Recipe, Void, Void> {
        private RecipeDao mAsyncTaskDao;

        insertAsyncTask(RecipeDao wordDao) {
            mAsyncTaskDao = wordDao;
        }

        @Override
        protected Void doInBackground(Recipe... recipes) {
            mAsyncTaskDao.insert(recipes[0]);
            return null;
        }
    }
}
