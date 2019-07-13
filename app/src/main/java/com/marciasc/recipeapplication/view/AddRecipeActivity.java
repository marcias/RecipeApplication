package com.marciasc.recipeapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.marciasc.recipeapplication.ImageListAdapter;
import com.marciasc.recipeapplication.R;
import com.marciasc.recipeapplication.viewmodel.RecipeViewModel;
import com.marciasc.recipeapplication.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class AddRecipeActivity extends AppCompatActivity implements ImageListAdapter.OnRemoveButtonPressed {
    private final int REQUEST_CODE = 1001;
    private List<String> mListImages = new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private ImageListAdapter mImageListAdapter;
    private RecipeViewModel mRecipeViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        mRecipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        mImageListAdapter = new ImageListAdapter(this, this);
        mRecyclerView = findViewById(R.id.rv_selected_images);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mImageListAdapter);

        ImageView ibAddImage = findViewById(R.id.ib_add_image);
        ibAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImage();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        Log.d("AddRecipeActivity", ">> onOptionsSelected " + item.getItemId());
        saveRecipe();
        return super.onOptionsItemSelected(item);
    }

    private void pickImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_CODE);
    }

    private void removeImage(String imagePath) {
        mListImages.remove(imagePath);
        mImageListAdapter.setList(mListImages);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data.getData() != null) {
            mListImages.add(data.getData().toString());
            mImageListAdapter.setList(mListImages);
        }
    }

    private void saveRecipe() {
        String title = ((TextInputEditText) findViewById(R.id.et_title)).getText().toString();
        String description = ((TextInputEditText) findViewById(R.id.et_description)).getText().toString();
        mRecipeViewModel.insert(new Recipe(title, description, mListImages));
        finish();
    }

    @Override
    public void onRemoveButtonPressed(int position) {
        removeImage(mListImages.get(position));
    }
}
