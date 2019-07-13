package com.marciasc.recipeapplication;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.marciasc.recipeapplication.model.Recipe;
import com.marciasc.recipeapplication.model.RecipeRepository;

import java.util.List;

public class RecipeViewModel extends AndroidViewModel {
    private RecipeRepository mRepository;
    private LiveData<List<Recipe>> mRecipes;

    public RecipeViewModel(@NonNull Application application) {
        super(application);
        mRepository = new RecipeRepository(application);
        mRecipes = mRepository.getRecipes();
    }

    LiveData<List<Recipe>> getRecipes() {
        return mRecipes;
    }

    public void insert(Recipe recipe) {
        Log.d("RecipeViewModel", ">> saving recipe " + recipe.getTitle());

        mRepository.insert(recipe);
    }
}
