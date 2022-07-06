package com.example.movieapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.movieapp.view.fragment.FragmentHome;
import com.example.movieapp.view.fragment.FragmentSearch;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int pageNum;
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.pageNum = 2;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1:
                return new FragmentSearch();
            default:
                return new FragmentHome();
        }
    }

    @Override
    public int getCount() {
        return pageNum;
    }
}
