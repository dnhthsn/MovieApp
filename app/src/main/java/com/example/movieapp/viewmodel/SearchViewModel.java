package com.example.movieapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapp.control.Repository;
import com.example.movieapp.control.rest.Callback;
import com.example.movieapp.model.Movies;

import java.util.ArrayList;
import java.util.List;

public class SearchViewModel extends ViewModel {
    private MutableLiveData<List<Movies>> movies;
    private List<Movies> moviesList;
    private Repository repository;

    public SearchViewModel() {
        this.movies = new MutableLiveData<>();
        moviesList = new ArrayList<>();
        movies.setValue(moviesList);
        this.repository = new Repository();
    }

    public MutableLiveData<List<Movies>> getMovies() {
        repository.getMovie(new Callback() {
            @Override
            public void getMovie(List<Movies> list) {
                super.getMovie(list);
                if(list != null){
                    for (Movies movie : list){
                        moviesList.add(movie);
                    }
                }
            }
        });
        movies.postValue(moviesList);
        return movies;
    }
}
