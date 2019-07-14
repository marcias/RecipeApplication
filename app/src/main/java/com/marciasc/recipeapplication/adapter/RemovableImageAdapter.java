package com.marciasc.recipeapplication.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

public class RemovableImageAdapter extends ImageListAdapter {
    private OnRemoveButtonPressed mListener;

    public RemovableImageAdapter(Context context, OnRemoveButtonPressed listener) {
        super(context);
        mListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        holder.getRemoveIcon().setVisibility(View.VISIBLE);
        holder.getRemoveIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onRemoveButtonPressed(position);
            }
        });
    }

    public interface OnRemoveButtonPressed {
        void onRemoveButtonPressed(int position);
    }
}
