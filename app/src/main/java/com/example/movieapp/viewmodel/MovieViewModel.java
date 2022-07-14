package com.example.movieapp.viewmodel;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapp.control.Repository;
import com.example.movieapp.control.rest.Callback;
import com.example.movieapp.model.Movies;

import java.util.List;

public class MovieViewModel extends ViewModel {
    private Repository repository;
    private MutableLiveData<List<Movies>> movies = new MutableLiveData<>();

    public MovieViewModel() {
        this.repository = new Repository();
    }

    public MutableLiveData<List<Movies>> getMovies() {
        repository.getMovie(new Callback() {
            @Override
            public void getMovie(List<Movies> list) {
                super.getMovie(list);
                movies.setValue(list);
            }
        });
        return movies;
    }

    public void addMovie(Movies movies, View view){
        repository.addMovie(movies, view);
    }
}
