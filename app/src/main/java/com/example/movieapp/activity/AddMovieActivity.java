package com.example.movieapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.movieapp.R;
import com.example.movieapp.model.Movies;
import com.example.movieapp.rest.Repository;

public class AddMovieActivity extends AppCompatActivity {
    private EditText inputMovieName, inputImageUrl, inputVideoUrl;
    private Button addMovie, logOut;

    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        inputMovieName = findViewById(R.id.input_movie_name);
        inputImageUrl = findViewById(R.id.input_movie_image);
        inputVideoUrl = findViewById(R.id.input_movie_video);
        addMovie = findViewById(R.id.add_movie);
        logOut = findViewById(R.id.log_out);

        repository = new Repository();
        addMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = inputMovieName.getText().toString();
                String image = inputImageUrl.getText().toString();
                String video = inputVideoUrl.getText().toString();
                Movies movies = new Movies(name, image, video);
                repository.addMovie(movies, AddMovieActivity.this);

                inputMovieName.setText("");
                inputImageUrl.setText("");
                inputVideoUrl.setText("");
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}