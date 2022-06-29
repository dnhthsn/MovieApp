package com.example.movieapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.adapter.SearchMovieAdapter;
import com.example.movieapp.model.Movies;
import com.example.movieapp.rest.Callback;
import com.example.movieapp.rest.Repository;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearch extends Fragment {
    private SearchView searchMovie;
    private RecyclerView movieLists;

    private Repository repository;
    private SearchMovieAdapter searchMovieAdapter;
    private List<Movies> movies;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchMovie = view.findViewById(R.id.search_movie);
        movieLists = view.findViewById(R.id.movie_list);

        movies = new ArrayList<>();
        searchMovieAdapter = new SearchMovieAdapter(movies);

        repository = new Repository();
        repository.getMovie(new Callback() {
            @Override
            public void getMovie(List<Movies> list) {
                super.getMovie(list);
                for (Movies movie : list){
                    movies.add(movie);
                }
                searchMovieAdapter.notifyDataSetChanged();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        movieLists.setLayoutManager(layoutManager);
        movieLists.setAdapter(searchMovieAdapter);
    }
}
