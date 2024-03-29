package com.marciasc.recipeapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.marciasc.recipeapplication.model.Recipe;
import com.marciasc.recipeapplication.model.RecipeRepository;

import java.util.List;

public class RecipeViewModel extends AndroidViewModel {
    private final RecipeRepository mRepository;
    private final LiveData<List<Recipe>> mRecipes;

    public RecipeViewModel(@NonNull Application application) {
        super(application);
        mRepository = new RecipeRepository(application);
        mRecipes = mRepository.getRecipes();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipes;
    }

    public void insert(Recipe recipe) {
        mRepository.insert(recipe);
    }
}
