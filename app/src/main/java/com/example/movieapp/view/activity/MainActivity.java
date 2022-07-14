package com.example.movieapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.example.movieapp.R;
import com.example.movieapp.databinding.ActivityMainBinding;
import com.example.movieapp.view.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private final int FRAGMENT_HOME_INDEX = 0;
    private final int FRAGMENT_SEARCH_INDEX = 1;

    private ActivityMainBinding binding;

    public static void starter(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 3);
        binding.viewPager.setAdapter(viewPagerAdapter);
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case FRAGMENT_HOME_INDEX: binding.bottomNavigationView.getMenu().findItem(R.id.home_movie).setCheckable(true);
                        break;
                    case FRAGMENT_SEARCH_INDEX: binding.bottomNavigationView.getMenu().findItem(R.id.search_movie).setCheckable(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home_movie:
                        binding.viewPager.setCurrentItem(FRAGMENT_HOME_INDEX);
                        break;
                    case R.id.search_movie:
                        binding.viewPager.setCurrentItem(FRAGMENT_SEARCH_INDEX);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}