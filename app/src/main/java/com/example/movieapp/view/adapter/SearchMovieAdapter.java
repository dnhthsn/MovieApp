package com.example.movieapp.view.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.databinding.ItemSearchMovieBinding;
import com.example.movieapp.model.Movies;
import com.example.movieapp.util.Const;
import com.example.movieapp.view.activity.VideoPlayerActivity;

import java.util.List;

public class SearchMovieAdapter extends RecyclerView.Adapter<SearchMovieAdapter.SearchViewHolder> {
    private List<Movies> movies;
    private onClickListener onClickListener;

    public void setMovies(List<Movies> movies){
        this.movies = movies;
        notifyDataSetChanged();
    }

    public void setOnClickListener(onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public SearchMovieAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSearchMovieBinding binding = ItemSearchMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SearchViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchMovieAdapter.SearchViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Movies movie = movies.get(position);
        Glide.with(holder.itemView.getContext()).load(movie.getImage()).into(holder.binding.itemImage);
        holder.binding.itemName.setText(movie.getName());
        holder.binding.playVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(holder.itemView.getContext(), VideoPlayerActivity.class);
                i.putExtra(Const.Sender.url, movie.getVideo());
                holder.itemView.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void filterList(List<Movies> filterList){
        movies = filterList;
        notifyDataSetChanged();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ItemSearchMovieBinding binding;

        public SearchViewHolder(@NonNull ItemSearchMovieBinding b) {
            super(b.getRoot());

            binding = b;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onClickListener != null){
                onClickListener.onSearchClick(getAdapterPosition(), view);
            }
        }
    }
    public interface onClickListener{
        void onSearchClick(int position, View view);
    }
}
