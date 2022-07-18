package com.example.movieapp.view.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.databinding.ItemCategoriesBinding;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private List<String> categories;
    private clickListener clickListener;
    private int selectedItem;

    public void setClickListener(CategoryAdapter.clickListener clickListener) {
        this.clickListener = clickListener;
        selectedItem = 0;
    }

    public CategoryAdapter(List<String> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoriesBinding binding = ItemCategoriesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.categoryName.setText(categories.get(position));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ItemCategoriesBinding binding;

        public CategoryViewHolder(@NonNull ItemCategoriesBinding b) {
            super(b.getRoot());

            binding = b;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null){
                clickListener.onClick(getAdapterPosition(), view, selectedItem);
            }
        }
    }

    public interface clickListener{
        void onClick(int position, View view, int selectedItem);
    }
}
