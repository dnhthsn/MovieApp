package com.example.movieapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.movieapp.R;
import com.example.movieapp.databinding.ActivityAddMovieBinding;
import com.example.movieapp.viewmodel.AddMovieViewModel;

public class AddMovieActivity extends AppCompatActivity{
    private ActivityAddMovieBinding binding;
    private AddMovieViewModel addMovieViewModel;

    public static void starter(Context context) {
        Intent intent = new Intent(context, AddMovieActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_movie);
        addMovieViewModel = new AddMovieViewModel(this);
        binding.setAddMovie(addMovieViewModel);

        addMovieViewModel.setGenre(binding.movieGenre.getSelectedItem().toString());
        binding.inputMovieName.setText(addMovieViewModel.getName());
        binding.addMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.inputMovieName.setText("");
                binding.inputMovieImage.setText("");
                binding.inputMovieVideo.setText("");
                binding.movieGenre.setSelection(0);
            }
        });

        binding.logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}