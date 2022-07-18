package com.example.movieapp.view.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.databinding.ItemCategoryMoviesBinding;
import com.example.movieapp.model.Movies;
import com.example.movieapp.util.Const;
import com.example.movieapp.view.activity.MovieDetailsActivity;

import java.util.List;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder> {
    private List<Movies> categoryItem;

    public ItemRecyclerAdapter(List<Movies> categoryItemList) {
        this.categoryItem = categoryItemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryMoviesBinding binding = ItemCategoryMoviesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Movies category = categoryItem.get(position);
        Glide.with(holder.itemView.getContext()).load(category.getImage()).into(holder.binding.itemImage);
        holder.binding.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(holder.itemView.getContext(), MovieDetailsActivity.class);
                i.putExtra(Const.Sender.movieName, category.getName());
                i.putExtra(Const.Sender.movieImageUrl, category.getImage());
                i.putExtra(Const.Sender.movieFile, category.getVideo());
                holder.itemView.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryItem.size();
    }

    public static final class ItemViewHolder extends RecyclerView.ViewHolder {
        private ItemCategoryMoviesBinding binding;

        public ItemViewHolder(@NonNull ItemCategoryMoviesBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }
}
