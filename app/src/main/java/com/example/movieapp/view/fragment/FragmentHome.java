package com.example.movieapp.view.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.databinding.FragmentHomeBinding;
import com.example.movieapp.model.AllCategory;
import com.example.movieapp.model.Movies;
import com.example.movieapp.util.Const;
import com.example.movieapp.view.activity.InformationActivity;
import com.example.movieapp.view.activity.MovieDetailsActivity;
import com.example.movieapp.view.adapter.CategoryAdapter;
import com.example.movieapp.view.adapter.MainRecyclerAdapter;
import com.example.movieapp.view.adapter.SearchMovieAdapter;
import com.example.movieapp.viewmodel.MovieViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FragmentHome extends Fragment implements CategoryAdapter.clickListener, SearchMovieAdapter.onClickListener {
    private MainRecyclerAdapter mainRecyclerAdapter;
    private CategoryAdapter categoryAdapter;
    private SearchMovieAdapter searchMovieAdapter;
    private List<AllCategory> allCategories;
    private List<String> categories;
    private List<Movies> movies, homeCatListItem1, homeCatListItem2, homeCatListItem3, homeCatListItem4;

    private FragmentHomeBinding binding;
    private MovieViewModel movieViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieViewModel = new MovieViewModel();

        String url = "https://images.clipartlogo.com/files/istock/previews/1020/102071463-flat-design-avatar-male-character-icon-vector.jpg";
        new GetImage().execute(url);

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

        binding.textTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMainRecycler(allCategories);
            }
        });


        movieViewModel.getMovies().observe((LifecycleOwner) getContext(), new Observer<List<Movies>>() {
            @Override
            public void onChanged(List<Movies> movies) {
                for (Movies movie : movies) {
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
        for (int i = 0; i < strings.length; i++) {
            categories.add(strings[i]);
        }

        setCategoryAdapter(categories);
        setMainRecycler(allCategories);

        binding.information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InformationActivity.starter(getContext());
            }
        });
    }

    public void setMainRecycler(List<AllCategory> allCategoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.mainRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(allCategoryList);
        binding.mainRecycler.setAdapter(mainRecyclerAdapter);
    }

    public void setMainRecyclerItem(List<Movies> movies) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.mainRecycler.setLayoutManager(layoutManager);
        searchMovieAdapter = new SearchMovieAdapter();
        searchMovieAdapter.setMovies(movies);
        searchMovieAdapter.setOnClickListener(this);
        binding.mainRecycler.setAdapter(searchMovieAdapter);
    }

    public void setCategoryAdapter(List<String> categories) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        binding.categoryList.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(categories);
        categoryAdapter.setClickListener(this);
        binding.categoryList.setAdapter(categoryAdapter);
    }

    @Override
    public void onClick(int position, View view, int selectedItem) {
        String genre = categories.get(position);
        movieViewModel.getMovies().observe((LifecycleOwner) getContext(), new Observer<List<Movies>>() {
            @Override
            public void onChanged(List<Movies> list) {
                movies.clear();
                for (Movies movie : list) {
                    if (movie.getGenre().equals(genre)) {
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

    class GetImage extends AsyncTask<String, Void, byte[]> {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        @Override
        protected byte[] doInBackground(String... strings) {
            Request.Builder builder = new Request.Builder();
            builder.url(strings[0]);

            Request request = builder.build();
            try {
                Response response = okHttpClient.newCall(request).execute();
                return response.body().bytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(byte[] bytes) {
            if (bytes.length > 0) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                binding.information.setImageBitmap(bitmap);
            }
            super.onPostExecute(bytes);
        }
    }
}