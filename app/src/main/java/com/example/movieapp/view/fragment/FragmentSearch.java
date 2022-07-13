package com.example.movieapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.databinding.FragmentSearchBinding;
import com.example.movieapp.model.Movies;
import com.example.movieapp.util.Const;
import com.example.movieapp.view.activity.MovieDetailsActivity;
import com.example.movieapp.view.adapter.SearchMovieAdapter;
import com.example.movieapp.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearch extends Fragment implements SearchMovieAdapter.onClickListener {
    private SearchMovieAdapter searchMovieAdapter;
    private List<Movies> movies;
    private List<Movies> filter;

    private FragmentSearchBinding binding;
    private MovieViewModel movieViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieViewModel = new MovieViewModel();

        movies = new ArrayList<>();
        filter = new ArrayList<>();

        movieViewModel.getMovies().observe(getViewLifecycleOwner(), new Observer<List<Movies>>() {
            @Override
            public void onChanged(List<Movies> list) {
                for (Movies movie : list){
                    movies.add(movie);
                }
                searchMovieAdapter.notifyDataSetChanged();
            }
        });

        searchMovieAdapter = new SearchMovieAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        searchMovieAdapter.setOnClickListener(this);
        searchMovieAdapter.setMovies(movies);
        binding.movieList.setLayoutManager(layoutManager);
        binding.movieList.setAdapter(searchMovieAdapter);

        binding.searchMovie.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                movieViewModel.getMovies().observe(getViewLifecycleOwner(), new Observer<List<Movies>>() {
                    @Override
                    public void onChanged(List<Movies> list) {
                        filter.clear();
                        if (list != null) {
                            for (Movies movie : list) {
                                if (movie.getName().toLowerCase().contains(s.toLowerCase())) {
                                    filter.add(movie);
                                }
                                searchMovieAdapter.filterList(filter);
                            }
                        }
                    }
                });
                return true;
            }
        });
    }

    @Override
    public void onSearchClick(int position, View view) {
        if (filter.isEmpty()) {
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
