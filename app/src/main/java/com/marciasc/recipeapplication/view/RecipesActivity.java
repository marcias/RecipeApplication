package com.marciasc.recipeapplication.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.marciasc.recipeapplication.R;
import com.marciasc.recipeapplication.adapter.RecipeListAdapter;
import com.marciasc.recipeapplication.viewmodel.RecipeViewModel;
import com.marciasc.recipeapplication.model.Recipe;

import java.util.List;

public class RecipesActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private TextView mTvNoRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        initUi();
        final RecipeListAdapter recipeListAdapter = new RecipeListAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(recipeListAdapter);

        RecipeViewModel recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        recipeViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                mTvNoRecipes.setVisibility(recipes != null && recipes.size() > 0 ? View.GONE : View.VISIBLE);
                recipeListAdapter.setList(recipes);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_recipe) {
            startActivity(new Intent(this, AddRecipeActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initUi() {
        mTvNoRecipes = findViewById(R.id.tv_no_recipes);
        mRecyclerView = findViewById(R.id.rv_recipes);
    }
}
