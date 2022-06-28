package com.example.movieapp.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.activity.MovieDetails;
import com.example.movieapp.R;
import com.example.movieapp.model.Movies;
import com.example.movieapp.util.Const;

import java.util.List;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder> {
    private List<Movies> categoryItem;

    public ItemRecyclerAdapter(List<Movies> categoryItemList) {
        this.categoryItem = categoryItemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_movies, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        Glide.with(holder.itemView.getContext()).load(categoryItem.get(position).getImage()).into(holder.itemImage);
        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(holder.itemView.getContext(), MovieDetails.class);
                //i.putExtra(Const.Sender.movieID, categoryItem.get(position).getId());
                i.putExtra(Const.Sender.movieName, categoryItem.get(position).getName());
                i.putExtra(Const.Sender.movieImageUrl, categoryItem.get(position).getImage());
                i.putExtra(Const.Sender.movieFile, categoryItem.get(position).getVideo());
                holder.itemView.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryItem.size();
    }

    public static final class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemImage;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
        }
    }
}
