package com.example.movieapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
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
        return new MainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.categoryName.setText(allCategory.get(position).getCategoryTitle());
        setItemRecycler(holder.itemRecycler, allCategory.get(position).getCategoryItem());
    }

    @Override
    public int getItemCount() {
        return allCategory.size();
    }

    public static final class MainViewHolder extends RecyclerView.ViewHolder {
        private TextView categoryName;
        private RecyclerView itemRecycler;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryName = itemView.findViewById(R.id.item_category);
            itemRecycler = itemView.findViewById(R.id.item_recycler);
        }
    }

    private void setItemRecycler(RecyclerView recyclerView, List<Movies> categoryItemList) {
        ItemRecyclerAdapter itemRecyclerAdapter = new ItemRecyclerAdapter(categoryItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(itemRecyclerAdapter);
    }

}
