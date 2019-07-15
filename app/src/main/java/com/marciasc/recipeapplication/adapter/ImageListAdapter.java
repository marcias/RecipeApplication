package com.marciasc.recipeapplication.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.marciasc.recipeapplication.R;

import java.util.List;

public class ImageListAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<String> mImageList;

    public ImageListAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.image_item_list, parent, false);
        return new ImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        if (mImageList != null) {
            Uri imageUri = Uri.parse(mImageList.get(position));
            Glide.with(mContext)
                    .load(imageUri)
                    .override(400,400)
                    .into(holder.getImageView());
        }
    }

    @Override
    public int getItemCount() {
        return mImageList != null ? mImageList.size() : 0;
    }

    public void setList(List<String> imageList) {
        mImageList = imageList;
        notifyDataSetChanged();
    }

}
