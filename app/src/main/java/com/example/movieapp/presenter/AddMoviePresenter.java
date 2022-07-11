package com.example.movieapp.presenter;

import android.content.Context;

import com.example.movieapp.control.Repository;
import com.example.movieapp.model.Movies;

public class AddMoviePresenter {
    private Repository repository;
    private AddMovie addMovie;

    public AddMoviePresenter(AddMovie addMovie) {
        this.repository = new Repository();
        this.addMovie = addMovie;
    }

    public void addMovie(Context context, Movies movies){
        repository.addMovie(movies, context);
        addMovie.addMovieSuccess();
    }

    public interface AddMovie{
        void addMovieSuccess();
    }
}
