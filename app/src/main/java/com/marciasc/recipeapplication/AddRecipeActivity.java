package com.marciasc.recipeapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AddRecipeActivity extends AppCompatActivity implements ImageListAdapter.OnRemoveButtonPressed {
    private final int REQUEST_CODE = 1001;
    private List<Uri> mListImages = new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private ImageListAdapter mImageListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

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
        int id = item.getItemId();
        if (id == R.id.action_save_recipe) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void pickImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_CODE);
    }

    private void removeImage(Uri uri) {
        mListImages.remove(uri);
        mImageListAdapter.setList(mListImages);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data.getData() != null) {
            mListImages.add(data.getData());
            mImageListAdapter.setList(mListImages);
        }
    }

    @Override
    public void onRemoveButtonPressed(int position) {
        removeImage(mListImages.get(position));
    }
}
