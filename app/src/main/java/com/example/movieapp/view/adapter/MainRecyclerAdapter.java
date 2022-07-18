package com.example.movieapp.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.databinding.ItemMainRecyclerBinding;
import com.example.movieapp.model.AllCategory;
import com.example.movieapp.model.Movies;

import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder> {
    private List<AllCategory> allCategory;

    public void setAllCategory(List<AllCategory> allCategory) {
        this.allCategory = allCategory;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMainRecyclerBinding binding = ItemMainRecyclerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MainViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.binding.itemCategory.setText(allCategory.get(position).getCategoryTitle());
        setItemRecycler(holder.binding.itemRecycler, allCategory.get(position).getCategoryItem());
    }

    @Override
    public int getItemCount() {
        return allCategory.size();
    }

    public static final class MainViewHolder extends RecyclerView.ViewHolder {
        private ItemMainRecyclerBinding binding;

        public MainViewHolder(@NonNull ItemMainRecyclerBinding b) {
            super(b.getRoot());

            binding = b;
        }
    }

    private void setItemRecycler(RecyclerView recyclerView, List<Movies> categoryItemList) {
        ItemRecyclerAdapter itemRecyclerAdapter = new ItemRecyclerAdapter(categoryItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(itemRecyclerAdapter);
    }

}
