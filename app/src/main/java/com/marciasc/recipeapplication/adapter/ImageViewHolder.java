package com.marciasc.recipeapplication.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.marciasc.recipeapplication.R;

public class ImageViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private ImageView removePhoto;

    protected ImageViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.iv_image);
        removePhoto = itemView.findViewById(R.id.iv_remove_image);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public ImageView getRemoveIcon() {
        return removePhoto;
    }

}
