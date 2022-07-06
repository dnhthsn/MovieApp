package com.example.movieapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.view.activity.InformationActivity;
import com.example.movieapp.adapter.BannerMoviesPagerAdapter;
import com.example.movieapp.adapter.MainRecyclerAdapter;
import com.example.movieapp.control.rest.Callback;
import com.example.movieapp.control.Repository;
import com.example.movieapp.model.AllCategory;
import com.example.movieapp.model.Movies;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {
    private RecyclerView mainRecycler;
    private ImageView information;

    private MainRecyclerAdapter mainRecyclerAdapter;
    private List<AllCategory> allCategories;

    private Repository repository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainRecycler = view.findViewById(R.id.main_recycler);
        information = view.findViewById(R.id.information);

        List<Movies> homeCatListItem1 = new ArrayList<>();
        List<Movies> homeCatListItem2 = new ArrayList<>();
        List<Movies> homeCatListItem3 = new ArrayList<>();
        List<Movies> homeCatListItem4 = new ArrayList<>();

        allCategories = new ArrayList<>();
        allCategories.add(new AllCategory("Only on Zetflix", homeCatListItem1));
        allCategories.add(new AllCategory("Kids and family movies", homeCatListItem2));
        allCategories.add(new AllCategory("Action movies", homeCatListItem3));
        allCategories.add(new AllCategory("Horror movies", homeCatListItem4));

        repository = new Repository();
        repository.getMovie(new Callback() {
            @Override
            public void getMovie(List<Movies> list) {
                super.getMovie(list);
                for (Movies movie : list) {
                    switch (movie.getGenre()) {
                        case "Horror":
                            homeCatListItem4.add(movie);
                            break;
                        case "TV shows":
                            homeCatListItem1.add(movie);
                            break;
                        case "Action":
                            homeCatListItem3.add(movie);
                            break;
                        case "Kids":
                            homeCatListItem2.add(movie);
                            break;
                    }
                }
                mainRecyclerAdapter.notifyDataSetChanged();
            }
        });

        setMainRecycler(allCategories);

        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InformationActivity.starter(getContext());
            }
        });
    }

    public void setMainRecycler(List<AllCategory> allCategoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mainRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(allCategoryList);
        mainRecycler.setAdapter(mainRecyclerAdapter);
    }
}