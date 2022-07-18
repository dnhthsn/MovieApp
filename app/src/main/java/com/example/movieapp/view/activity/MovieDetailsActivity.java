package com.example.movieapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.movieapp.R;
import com.example.movieapp.databinding.ActivityMovieDetailsBinding;
import com.example.movieapp.util.Const;

public class MovieDetailsActivity extends AppCompatActivity {
    private ActivityMovieDetailsBinding binding;

    public static void starter(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);

        String mName = getIntent().getStringExtra(Const.Sender.movieName);
        String mImage = getIntent().getStringExtra(Const.Sender.movieImageUrl);
        String mFileUrl = getIntent().getStringExtra(Const.Sender.movieFile);

        Glide.with(this).load(mImage).into(binding.movieImage);
        binding.movieName.setText(mName);

        binding.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VideoPlayerActivity.starter(MovieDetailsActivity.this, mFileUrl);
            }
        });

        binding.clickBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}