package com.example.movieapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.control.Repository;
import com.example.movieapp.control.rest.Callback;
import com.example.movieapp.model.AllCategory;
import com.example.movieapp.model.Movies;
import com.example.movieapp.util.Const;
import com.example.movieapp.view.activity.InformationActivity;
import com.example.movieapp.view.activity.MovieDetailsActivity;
import com.example.movieapp.view.adapter.CategoryAdapter;
import com.example.movieapp.view.adapter.MainRecyclerAdapter;
import com.example.movieapp.view.adapter.SearchMovieAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment implements CategoryAdapter.clickListener, SearchMovieAdapter.onClickListener{
    private RecyclerView mainRecycler, categoryList;
    private ImageView information;
    private TextView title;

    private MainRecyclerAdapter mainRecyclerAdapter;
    private CategoryAdapter categoryAdapter;
    private SearchMovieAdapter searchMovieAdapter;
    private List<AllCategory> allCategories;
    private List<String> categories;
    private List<Movies> movies, homeCatListItem1, homeCatListItem2, homeCatListItem3, homeCatListItem4;

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
        categoryList = view.findViewById(R.id.category_list);
        title = view.findViewById(R.id.text_title);

        homeCatListItem1 = new ArrayList<>();
        homeCatListItem2 = new ArrayList<>();
        homeCatListItem3 = new ArrayList<>();
        homeCatListItem4 = new ArrayList<>();

        categories = new ArrayList<>();
        movies = new ArrayList<>();

        allCategories = new ArrayList<>();
        allCategories.add(new AllCategory("Only on Zetflix", homeCatListItem1));
        allCategories.add(new AllCategory("Kids and family movies", homeCatListItem2));
        allCategories.add(new AllCategory("Action movies", homeCatListItem3));
        allCategories.add(new AllCategory("Horror movies", homeCatListItem4));

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMainRecycler(allCategories);
            }
        });

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

        String[] strings = getResources().getStringArray(R.array.genres);
        for (int i = 0 ; i < strings.length ;i ++){
            categories.add(strings[i]);
        }

        setCategoryAdapter(categories);
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

    public void setMainRecyclerItem(List<Movies> movies){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mainRecycler.setLayoutManager(layoutManager);
        searchMovieAdapter = new SearchMovieAdapter();
        searchMovieAdapter.setMovies(movies);
        searchMovieAdapter.setOnClickListener(this);
        mainRecycler.setAdapter(searchMovieAdapter);
    }

    public void setCategoryAdapter(List<String> categories){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        categoryList.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(categories);
        categoryAdapter.setClickListener(this);
        categoryList.setAdapter(categoryAdapter);
    }

    @Override
    public void onClick(int position, View view, int selectedItem) {
        String genre = categories.get(position);
        repository.getMovie(new Callback() {
            @Override
            public void getMovie(List<Movies> list) {
                super.getMovie(list);
                movies.clear();
                for (Movies movie : list){
                    if(movie.getGenre().equals(genre)){
                        movies.add(movie);
                    }
                }
            }
        });
        setMainRecyclerItem(movies);
    }

    @Override
    public void onSearchClick(int position, View view) {
        Bundle bundle = new Bundle();
        bundle.putString(Const.Sender.movieName, movies.get(position).getName());
        bundle.putString(Const.Sender.movieImageUrl, movies.get(position).getImage());
        bundle.putString(Const.Sender.movieFile, movies.get(position).getVideo());
        MovieDetailsActivity.starter(getContext(), bundle);
    }
}