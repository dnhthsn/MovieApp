package com.example.movieapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.movieapp.R;
import com.example.movieapp.util.Const;

public class MovieDetails extends AppCompatActivity {
    private ImageView movieImage;
    private TextView movieName;
    private Button playVideo;

    private String mName, mImage, mFileUrl;

    public static void starter(Context context) {
        Intent intent = new Intent(context, MovieDetails.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        movieImage = findViewById(R.id.movie_image);
        movieName = findViewById(R.id.movie_name);
        playVideo = findViewById(R.id.play_button);

        mName = getIntent().getStringExtra(Const.Sender.movieName);
        mImage = getIntent().getStringExtra(Const.Sender.movieImageUrl);
        mFileUrl = getIntent().getStringExtra(Const.Sender.movieFile);

        Glide.with(this).load(mImage).into(movieImage);
        movieName.setText(mName);

        playVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VideoPlayerActivity.starter(MovieDetails.this, mFileUrl);
            }
        });

    }
}