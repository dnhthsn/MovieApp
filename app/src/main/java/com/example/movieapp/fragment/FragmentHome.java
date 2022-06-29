package com.example.movieapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.movieapp.R;
import com.example.movieapp.activity.MainActivity;
import com.example.movieapp.adapter.BannerMoviesPagerAdapter;
import com.example.movieapp.adapter.MainRecyclerAdapter;
import com.example.movieapp.model.AllCategory;
import com.example.movieapp.model.Movies;
import com.example.movieapp.rest.Callback;
import com.example.movieapp.rest.Repository;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class FragmentHome extends Fragment {
    private TabLayout indicatorTab, categoryTab;
    private ViewPager bannerMoviesViewPager;
    private RecyclerView mainRecycler;

    private MainRecyclerAdapter mainRecyclerAdapter;
    private BannerMoviesPagerAdapter bannerMoviesPagerAdapter;
    private List<AllCategory> allCategories;
    private List<Movies> homeBanners;
    private List<Movies> tvShowBanners;
    private List<Movies> movieBanners;
    private List<Movies> kidsBanners;

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

        indicatorTab = view.findViewById(R.id.tab_indicator);
        categoryTab = view.findViewById(R.id.tablayout);
        bannerMoviesViewPager = view.findViewById(R.id.banner_ViewPager);
        mainRecycler = view.findViewById(R.id.main_recycler);

        homeBanners = new ArrayList<>();
        tvShowBanners = new ArrayList<>();
        movieBanners = new ArrayList<>();
        kidsBanners = new ArrayList<>();

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
                    homeCatListItem1.add(movie);

                    switch (movie.getGenre()) {
                        case "Horror":
                            homeBanners.add(movie);
                            homeCatListItem4.add(movie);
                            break;
                        case "TV shows":
                            tvShowBanners.add(movie);
                            //homeCatListItem1.add(movie);
                            break;
                        case "Movie":
                            movieBanners.add(movie);
                            homeCatListItem3.add(movie);
                            break;
                        case "Kids":
                            kidsBanners.add(movie);
                            homeCatListItem2.add(movie);
                            break;
                    }
                }
                bannerMoviesPagerAdapter.notifyDataSetChanged();
                mainRecyclerAdapter.notifyDataSetChanged();
            }
        });

        setBannerMoviesPagerAdapter(homeBanners);

        categoryTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 1:
                        setBannerMoviesPagerAdapter(tvShowBanners);
                        return;
                    case 2:
                        setBannerMoviesPagerAdapter(movieBanners);
                        return;
                    case 3:
                        setBannerMoviesPagerAdapter(kidsBanners);
                        return;
                    default:
                        setBannerMoviesPagerAdapter(homeBanners);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        setMainRecycler(allCategories);
    }

    private void setBannerMoviesPagerAdapter(List<Movies> movies) {
        bannerMoviesPagerAdapter = new BannerMoviesPagerAdapter(movies);
        bannerMoviesViewPager.setAdapter(bannerMoviesPagerAdapter);
        indicatorTab.setupWithViewPager(bannerMoviesViewPager);

        Timer sliderTimer = new Timer();
        sliderTimer.scheduleAtFixedRate(new AutoSlider(), 5000, 7000);
        indicatorTab.setupWithViewPager(bannerMoviesViewPager, true);
    }

    class AutoSlider extends TimerTask {
        @Override
        public void run() {
            getActivity().runOnUiThread(() -> {
                if (bannerMoviesViewPager.getCurrentItem() < homeBanners.size() - 1) {
                    bannerMoviesViewPager.setCurrentItem(bannerMoviesViewPager.getCurrentItem() + 1);
                } else {
                    bannerMoviesViewPager.setCurrentItem(0);
                }
            });
        }


    }

    public void setMainRecycler(List<AllCategory> allCategoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mainRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(allCategoryList);
        mainRecycler.setAdapter(mainRecyclerAdapter);
    }
}
