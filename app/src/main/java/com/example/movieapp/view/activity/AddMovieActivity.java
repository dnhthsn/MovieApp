package com.example.movieapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapp.R;
import com.example.movieapp.model.Movies;
import com.example.movieapp.presenter.AddMoviePresenter;

public class AddMovieActivity extends AppCompatActivity implements AddMoviePresenter.AddMovie{
    private EditText inputMovieName, inputImageUrl, inputVideoUrl;
    private Button addMovie, logOut;
    private Spinner movieGenre;

    private AddMoviePresenter addMoviePresenter;

    public static void starter(Context context) {
        Intent intent = new Intent(context, AddMovieActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        inputMovieName = findViewById(R.id.input_movie_name);
        inputImageUrl = findViewById(R.id.input_movie_image);
        inputVideoUrl = findViewById(R.id.input_movie_video);
        addMovie = findViewById(R.id.add_movie);
        logOut = findViewById(R.id.log_out);
        movieGenre = findViewById(R.id.movie_genre);

        addMoviePresenter = new AddMoviePresenter(this);
        addMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = inputMovieName.getText().toString();
                String image = inputImageUrl.getText().toString();
                String video = inputVideoUrl.getText().toString();
                String genre = movieGenre.getSelectedItem().toString();
                Movies movies = new Movies(name, image, video, genre);
                addMoviePresenter.addMovie(AddMovieActivity.this, movies);
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void addMovieSuccess() {
        inputMovieName.setText("");
        inputImageUrl.setText("");
        inputVideoUrl.setText("");
        movieGenre.setSelection(0);
    }
}