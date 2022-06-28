package com.example.movieapp.model;

import java.io.Serializable;
import java.util.List;

public class AllCategory implements Serializable {
    private String categoryTitle;

    private List<Movies> categoryItem = null;

    public AllCategory(String categoryTitle, List<Movies> categoryItemList) {
        this.categoryTitle = categoryTitle;
        this.categoryItem = categoryItemList;
    }

    public List<Movies> getCategoryItem() {
        return categoryItem;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }
}
