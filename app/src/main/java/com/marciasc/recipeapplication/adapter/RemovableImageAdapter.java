package com.marciasc.recipeapplication.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.marciasc.recipeapplication.R;

import java.util.List;

import static com.marciasc.recipeapplication.R.id.ib_add_image;

public class RemovableImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int HEADER_VIEW = 0;
    private final int ITEM_VIEW = 1;
    private OnRemoveButtonPressed mRemovedButtonListener;
    private OnPickImageButtonPressed mPickImageButtonListener;
    protected LayoutInflater mLayoutInflater;
    protected List<String> mImageList;
    private Context mContext;

    public RemovableImageAdapter(Context context, OnRemoveButtonPressed removeListener, OnPickImageButtonPressed pickListener) {
        mContext = context;
        mRemovedButtonListener = removeListener;
        mPickImageButtonListener = pickListener;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == HEADER_VIEW) {
            return new HeaderViewHolder(mLayoutInflater.inflate(R.layout.header_item_list, parent, false));
        } else {
            return new ImageViewHolder(mLayoutInflater.inflate(R.layout.image_item_list, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (position > 0) {
            Uri imageUri = Uri.parse(mImageList.get(getPosition(position)));
            Glide.with(mContext)
                    .load(imageUri)
                    .into(((ImageViewHolder) holder).getImageView());
            ((ImageViewHolder) holder).getRemoveIcon().setVisibility(View.VISIBLE);
            ((ImageViewHolder) holder).getRemoveIcon().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mRemovedButtonListener.onRemoveButtonPressed(getPosition(position));
                }
            });
        } else {
            ((HeaderViewHolder) holder).addImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPickImageButtonListener.onPickImageButtonPressed();

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mImageList != null ? mImageList.size() + 1 : 1;
    }

    private int getPosition(int position) {
        return position - 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER_VIEW;
        }
        return ITEM_VIEW;
    }

    public void setList(List<String> imageList) {
        mImageList = imageList;
        notifyDataSetChanged();
    }

    public interface OnRemoveButtonPressed {
        void onRemoveButtonPressed(int position);
    }

    public interface OnPickImageButtonPressed {
        void onPickImageButtonPressed();
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        private ImageButton addImageButton;

        private HeaderViewHolder(View itemView) {
            super(itemView);
            addImageButton = itemView.findViewById(ib_add_image);

        }

    }
}
