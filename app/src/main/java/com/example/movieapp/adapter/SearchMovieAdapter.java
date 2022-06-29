package com.example.movieapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.R;
import com.example.movieapp.model.Movies;

import java.util.List;

public class SearchMovieAdapter extends RecyclerView.Adapter<SearchMovieAdapter.SearchViewHolder> {
    private List<Movies> movies;

    public SearchMovieAdapter(List<Movies> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public SearchMovieAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchMovieAdapter.SearchViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load(movies.get(position).getImage()).into(holder.movieImage);
        holder.movieName.setText(movies.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        private ImageView movieImage;
        private TextView movieName;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            movieImage = itemView.findViewById(R.id.item_image);
            movieName = itemView.findViewById(R.id.item_name);
        }
    }
}
