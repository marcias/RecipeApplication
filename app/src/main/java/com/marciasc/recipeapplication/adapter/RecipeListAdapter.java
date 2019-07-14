package com.marciasc.recipeapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.marciasc.recipeapplication.R;
import com.marciasc.recipeapplication.model.Recipe;

import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<Recipe> mRecipeList;
    private Context mContext;

    public RecipeListAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.recipe_item_list, parent, false);
        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, final int position) {
        holder.title.setText(mRecipeList.get(position).getTitle());
        holder.description.setText(mRecipeList.get(position).getDescription());
        LinearLayoutManager childLinearLayout = new LinearLayoutManager(holder.recyclerView.getContext(), LinearLayout.HORIZONTAL, false);
        ImageListAdapter childAdapter = new ImageListAdapter(mContext);
        childAdapter.setList(mRecipeList.get(position).getImagesPath());
        holder.recyclerView.setLayoutManager(childLinearLayout);
        holder.recyclerView.setAdapter(childAdapter);
    }

    @Override
    public int getItemCount() {
        return mRecipeList != null ? mRecipeList.size() : 0;
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private RecyclerView recyclerView;
        private TextView description;

        private RecipeViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            description = itemView.findViewById(R.id.tv_description);
            recyclerView = itemView.findViewById(R.id.rv_images);
        }
    }

    public void setList(List<Recipe> recipeList) {
        mRecipeList = recipeList;
        notifyDataSetChanged();
    }
}
