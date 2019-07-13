package com.marciasc.recipeapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImageViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<String> mImageList;
    private Context mContext;
    private OnRemoveButtonPressed mListener;

    public ImageListAdapter(Context context, OnRemoveButtonPressed listener) {
        mContext = context;
        mListener = listener;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.image_item_list, parent, false);
        return new ImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, final int position) {
        if (mImageList != null) {
            Uri imageUri = Uri.parse(mImageList.get(position));
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            holder.imageView.setImageBitmap(bitmap);
        }

        holder.removePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onRemoveButtonPressed(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageList != null ? mImageList.size() : 0;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private ImageView removePhoto;

        private ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_image);
            removePhoto = itemView.findViewById(R.id.iv_remove_image);
        }
    }

    public void setList(List<String> imageList) {
        mImageList = imageList;
        notifyDataSetChanged();
    }

    public interface OnRemoveButtonPressed {
        void onRemoveButtonPressed(int position);
    }
}
