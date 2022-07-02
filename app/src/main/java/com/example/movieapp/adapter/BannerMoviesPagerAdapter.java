package com.example.movieapp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.movieapp.activity.MovieDetailsActivity;
import com.example.movieapp.R;
import com.example.movieapp.model.Movies;
import com.example.movieapp.util.Const;

import java.util.List;

public class BannerMoviesPagerAdapter extends PagerAdapter {
    private List<Movies> movies;

    public BannerMoviesPagerAdapter(List<Movies> movies) {
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_banner_movies, null);

        ImageView bannerImage = view.findViewById(R.id.banner_image);

        Glide.with(container.getContext()).load(movies.get(position).getImage()).into(bannerImage);
        container.addView(view);

        bannerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(container.getContext(), MovieDetailsActivity.class);
                i.putExtra(Const.Sender.movieName, movies.get(position).getName());
                i.putExtra(Const.Sender.movieImageUrl, movies.get(position).getImage());
                i.putExtra(Const.Sender.movieFile, movies.get(position).getVideo());
                container.getContext().startActivity(i);
            }
        });

        return view;
    }
}
