package com.example.movieapp.view.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.R;
import com.example.movieapp.view.activity.VideoPlayerActivity;
import com.example.movieapp.model.Movies;
import com.example.movieapp.util.Const;

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
        return new SearchViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchMovieAdapter.SearchViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Movies movie = movies.get(position);
        Glide.with(holder.itemView.getContext()).load(movie.getImage()).into(holder.movieImage);
        holder.movieName.setText(movie.getName());
        holder.playVideo.setOnClickListener(new View.OnClickListener() {
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
        private ImageView movieImage, playVideo;
        private TextView movieName;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            movieImage = itemView.findViewById(R.id.item_image);
            movieName = itemView.findViewById(R.id.item_name);
            playVideo = itemView.findViewById(R.id.play_video);
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
