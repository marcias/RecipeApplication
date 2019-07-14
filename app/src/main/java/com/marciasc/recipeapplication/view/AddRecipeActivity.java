package com.marciasc.recipeapplication.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
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
import com.marciasc.recipeapplication.adapter.RemovableImageAdapter;
import com.marciasc.recipeapplication.R;
import com.marciasc.recipeapplication.viewmodel.RecipeViewModel;
import com.marciasc.recipeapplication.model.Recipe;

import java.util.ArrayList;

public class AddRecipeActivity extends AppCompatActivity implements RemovableImageAdapter.OnRemoveButtonPressed {
    private final int REQUEST_CODE = 1001;
    private final String LIST_STATE_KEY = "list_state_key";
    private ArrayList<String> mListImages = new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private RemovableImageAdapter mImageListAdapter;
    private RecipeViewModel mRecipeViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        if (savedInstanceState != null) {
            mListImages = savedInstanceState.getStringArrayList(LIST_STATE_KEY);
        }

        mRecipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mImageListAdapter = new RemovableImageAdapter(this, this);
        mImageListAdapter.setList(mListImages);
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
        saveRecipe();
        return super.onOptionsItemSelected(item);
    }

    private void pickImage() {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        } else {
            intent.setAction(Intent.ACTION_GET_CONTENT);
        }
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE);
    }

    private void removeImage(String imagePath) {
        mListImages.remove(imagePath);
        mImageListAdapter.setList(mListImages);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE && data.getData() != null) {
            Uri uri = data.getData();
            mListImages.add(uri.toString());
            mImageListAdapter.setList(mListImages);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                final int takeFlags = data.getFlags()
                        & (Intent.FLAG_GRANT_READ_URI_PERMISSION
                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                getContentResolver().takePersistableUriPermission(uri, takeFlags);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putStringArrayList(LIST_STATE_KEY, mListImages);
        super.onSaveInstanceState(outState);
    }

    private void saveRecipe() {
        String title = ((TextInputEditText) findViewById(R.id.et_title)).getText().toString();
        String description = ((TextInputEditText) findViewById(R.id.et_description)).getText().toString();
        if (!title.equals(null) && !title.isEmpty() && !description.equals(null) && !description.isEmpty()) {
            mRecipeViewModel.insert(new Recipe(title, description, mListImages));
            finish();
        }
    }

    @Override
    public void onRemoveButtonPressed(int position) {
        removeImage(mListImages.get(position));
    }
}
