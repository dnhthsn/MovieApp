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
import com.example.movieapp.activity.MovieDetailsActivity;
import com.example.movieapp.adapter.SearchMovieAdapter;
import com.example.movieapp.control.rest.Callback;
import com.example.movieapp.control.Repository;
import com.example.movieapp.model.Movies;
import com.example.movieapp.util.Const;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearch extends Fragment implements SearchMovieAdapter.onClickListener{
    private SearchView searchMovie;
    private RecyclerView movieLists;

    private Repository repository;
    private SearchMovieAdapter searchMovieAdapter;
    private List<Movies> movies;
    private List<Movies> filter;

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
        filter = new ArrayList<>();

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
        searchMovieAdapter = new SearchMovieAdapter(movies);
        searchMovieAdapter.setOnClickListener(this);
        movieLists.setLayoutManager(layoutManager);
        movieLists.setAdapter(searchMovieAdapter);

        searchMovie.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filter.clear();
                repository.getMovie(new Callback() {
                    @Override
                    public void getMovie(List<Movies> list) {
                        super.getMovie(list);
                        for (Movies movie : list){
                            if(movie.getName().toLowerCase().contains(s.toLowerCase())){
                                filter.add(movie);
                            }
                            searchMovieAdapter.filterList(filter);
                        }
                    }
                });
                return true;
            }
        });
    }

    @Override
    public void onClick(int position, View view) {
        if (filter.isEmpty()){
            Bundle bundle = new Bundle();
            bundle.putString(Const.Sender.movieName, movies.get(position).getName());
            bundle.putString(Const.Sender.movieImageUrl, movies.get(position).getImage());
            bundle.putString(Const.Sender.movieFile, movies.get(position).getVideo());
            MovieDetailsActivity.starter(getContext(), bundle);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString(Const.Sender.movieName, filter.get(position).getName());
            bundle.putString(Const.Sender.movieImageUrl, filter.get(position).getImage());
            bundle.putString(Const.Sender.movieFile, filter.get(position).getVideo());
            MovieDetailsActivity.starter(getContext(), bundle);
        }
    }
}
